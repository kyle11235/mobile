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

        - simulator test
        ./ios.sh directly without opening xcode

        - open in xcode
        xcode open -> platforms/ios/HelloWorld.xcworkspace (do not modify code in xcode)
        select hello from left panel
        HelloCordova -> iPhone8 plus (to use older ios version, preferences -> components)
        run

        - deploy to device
        ...

- android

        - env setup guide
        https://cordova.apache.org/docs/en/latest/guide/platforms/android/index.html#requirements-and-support

        - install gradle
        brew install gradle

        - install android studio

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
