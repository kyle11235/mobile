# cordova

- install

        https://cordova.apache.org/
        sudo -i
        install -g cordova


- create

        cordova create hello
        cd hello

        cordova platform add browser
        cordova platform add ios
        cordova platform add android
        cordova platform ls

        cordova run browser
        if use CLI, do not modify platforms

- check for build

        cordova requirements

        Requirements check results for android:
        Java JDK: installed 1.8.0
        Android SDK: not installed
        Failed to find 'ANDROID_HOME' environment variable. Try setting it manually.
        Failed to find 'android' command in your 'PATH'. Try update your 'PATH' to include path to valid SDK directory.
        Android target: not installed
        android: Command failed with exit code ENOENT
        Gradle: not installed
        Could not find gradle wrapper within Android SDK. Could not find Android SDK directory.
        Might need to install Android SDK or set up 'ANDROID_HOME' env variable.

        Requirements check results for browser:

        Requirements check results for ios:
        Apple macOS: installed darwin
        Xcode: not installed
        xcode-select: error: tool 'xcodebuild' requires Xcode, but active developer directory '/Library/Developer/CommandLineTools' is a command line tools instance

        ios-deploy: not installed
        ios-deploy was not found. Please download, build and install version 1.9.2 or greater from https://github.com/ios-control/ios-deploy into your path, or do 'npm install -g ios-deploy'
        CocoaPods: not installed
        CocoaPods was not found. Please install version 1.0.1 or greater from https://cocoapods.org/
        Some of requirements check failed

- ios

        - env setup guide
        https://cordova.apache.org/docs/en/latest/guide/platforms/ios/index.html#requirements-and-support

        - install xcode
        xcode 9.4.1 for mac os 10.13.6 high sierra
        download xxx.xip from https://developer.apple.com/download/more/
        double click to extract and move to application
        xcode-select --install
        sudo -i
        npm install --unsafe-perm=true -g ios-deploy

        - simulator test
        ./ios.sh directly without opening xcode

        - open in xcode
        xcode open -> platforms/ios/HelloWorld.xcworkspace (do not modify code in xcode)
        select hello from left panel
        HelloCordova -> iPhone8 plus (to use older ios version, preferences -> components)
        run

        - deploy to device
        preference -> accounts -> xxx@126.com
        project -> general
                -> identity -> change bundle identifier from io.cordova.hellocordova to xxx@126.com
                -> signing -> team -> select the personal team
        usb connect to device
        HelloCordova -> your device name
        run
        error -> This iPhone 6 is running iOS 12.3.1 (16F203), which may not be supported by this version of Xcode. (google to solve it)

- android

        - env setup guide
        https://cordova.apache.org/docs/en/latest/guide/platforms/android/index.html#requirements-and-support

        - install gradle
        brew install gradle

        - install android studio
        open it, follow guide to install android sdk

        Setup Type: Standard
        SDK Folder: /Users/kyle/Library/Android/sdk
        Total Download Size: 548 MB
        SDK Components to Download:
        Android Emulator
        288 MB
        Android SDK Build-Tools 29.0.2
        39 MB
        Android SDK Platform 29
        74.6 MB
        Android SDK Platform-Tools
        8.28 MB
        Android SDK Tools
        98.2 MB
        Intel x86 Emulator Accelerator (HAXM installer)
        619 KB
        SDK Patch Applier v4
        1.74 MB
        Sources for Android 29
        37.6 MB

        - install android sdk package
        android studio star page / preference -> sdk manager
                -> sdk platforms
                install sdk api level 28, for cordova 8.x supports up to level 28
                do not remove level 29, for studio find sdk by it
                -> sdk tools
                install sdk tools for level 28, be sure to check show package details

        - set path
        sudo vi /etc/profile
        export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home
        export ANDROID_SDK_ROOT=/Users/kyle/Library/Android/sdk
        export PATH=$PATH:$ANDROID_SDK_ROOT/tools:$ANDROID_SDK_ROOT/tools/bin:$ANDROID_SDK_ROOT/platform-tools
        source /etc/profile

        - install AVD (virtual device)
        android studio start page -> avd manager -> next
        download / select system image -> api level 28 -> next / finish
        remove unused large system image in /Users/kyle/Library/Android/sdk/system-images

        - simulator test
        cordova requirements (no target installed -> Failed to find target with hash string 'android-28' -> reinstall api level 28)
        start AVD in avd manager (ok to close studio)
        ./android.sh (build apk and install to avd, no need to restart avd)

        - open in android studio
        import platforms/android

        - deploy to device
        ...

- plug

        A plugin exposes a Javascript API for native SDK functionality
        https://cordova.apache.org/plugins/

