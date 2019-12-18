# react-native-infalter-api

## Getting started

`$ npm install react-native-infalter-api --save`

### Mostly automatic installation

`$ react-native link react-native-infalter-api`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-infalter-api` and add `InfalterApi.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libInfalterApi.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainApplication.java`
  - Add `import com.reactlibrary.InfalterApiPackage;` to the imports at the top of the file
  - Add `new InfalterApiPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-infalter-api'
  	project(':react-native-infalter-api').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-infalter-api/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-infalter-api')
  	```


## Usage
```javascript
import InfalterApi from 'react-native-infalter-api';

// TODO: What to do with the module?
InfalterApi;
```
