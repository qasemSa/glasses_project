## Everysight's Glasses SDK

### This folder contains SDK (libraries and Samples) for the Everysight's Glasses application development. 

Since the glasses are based on Android operating system, you must be familiar with Android development and you have to establish the Android SDK environment on your computer (Windows, Mac or Linux)

### How to start your Raptor application development?

1. Install Android Studio
  - Version 2.2 and above
  - Verify build tools and platform tools version 25 (or higher) are installed
2. For new developers, follow the [android studio developr guide](https://developer.android.com/training/index.html)
3. Clone the SDK repository to your local computer
4. Open the 'samples' project using Android Studio
5. Learn the samples, install them on your glasses (you may modify the samples)

And you are ready to go!

### Folders structure

- [libs](https://github.com/everysight/SDK/tree/master/glasses/libs): Contains the Glasses SDK libraries
- [samples](https://github.com/everysight/SDK/tree/master/glasses/samples): Contains glasses sample applications

### Where are my applications?

After you install your applcations on the glasses they will be located in the glasses main menu under the **"App & Games"** submenu

### UI Guidlines

- Everything in **Black** will look transparent on the glasses
  - Remember: it is a see-through device. Don't overload your application UI
- Gestures are discrete and only few of them exist - use them wisely
  - Forward, backward, down, tap
  - Other gestures are saved for Everysight OS (i.e. long press)
- Application performance is a key for success. Make your app efficient
- Layout design
  - Horizonal only
  - Design for 640wX480h
  - We will set you set view to the right location and size according to your adjustements
  - Best to use for your main layout:
  ```java
  <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="640px"
    android:layout_height="480px"
    android:orientation="vertical" >
 ```
