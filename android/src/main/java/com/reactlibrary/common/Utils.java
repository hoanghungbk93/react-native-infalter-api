package com.reactlibrary.common;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.reactlibrary.json.RNHelper;

import org.apache.commons.io.output.ByteArrayOutputStream;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import okhttp3.Response;

public class Utils {
    private RNHelper mHelper;
    private WritableMap mapWrite;
    private String mZips[] = new String[] {"deflate", "gzip"};

    public void Utils() {
        this.mHelper = new RNHelper();
    }

    public WritableMap getDataInflate(Response res) {
        try {
            String validJson;

            if (isZipped(res)) {
                final InputStream in = res.body().byteStream();

                Inflater deCompressor = new Inflater(true);
                InflaterInputStream input = new InflaterInputStream(in, deCompressor);
                ByteArrayOutputStream out = new ByteArrayOutputStream();

                byte[] buffer = new byte[8192];
                int length;
                BufferedInputStream b = new BufferedInputStream(input);

                while ((length = b.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }

                validJson = new String(out.toByteArray(), "UTF-8");
                // validJson = formatString(response);
            } else {
                validJson = res.body().string();
            }

            mapWrite = mHelper.jsonToReact(validJson);
            mapWrite.putInt("statusCode", res.code());
            return mapWrite;

        } catch (Exception e) {
            e.printStackTrace();

            mapWrite = Arguments.createMap();
            mapWrite.putString("message", e.getMessage());
        }

        return mapWrite;
    }

    private String formatString(String text) {
        text = text.replace("\\n", "");
        text = text.replace("\\", "");
        text = text.replace(": ", ":");

        text = text.replace(":\"\",", ":[dot_mama]");
        text = text.replace(":\"\"]", ":[dot_mama1]");
        text = text.replace(":\"\"}", ":[dot_mama2]");
        text = text.replace("\"\"", "\"");
        text = text.replace(":[dot_mama]", ":\"\",");
        text = text.replace(":[dot_mama1]", ":\"\"]");
        text = text.replace(":[dot_mama2]", ":\"\"}");
        text = text.replace("\"[", "[");
        text = text.replace("\"{", "{");
        text = text.replace("}\"", "}");
        text = text.replace("]\"", "]");
        text = text.replace("\"\"", "null");

        return text;
    }

    private boolean isZipped(Response response) {
        String header = response.header("Content-Encoding");

        for (int i = 0; i < mZips.length; i++) {
            boolean flag = mZips[i].equalsIgnoreCase(header);

            if (flag) {
                return true;
            }
        }
        return false;
    }
}
