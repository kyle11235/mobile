# cordova

- cordova version / api lavel

        https://cordova.apache.org/docs/en/latest/guide/platforms/android/index.html#requirements-and-support

- install

        https://cordova.apache.org/
        sudo npm install -g cordova
      
        for error, Unhandled rejection Error: EACCES: permission denied
        sudo chown -R $USER:$GROUP ~/.npm
        sudo chown -R $USER:$GROUP ~/.config

- create

        cordova create hello
        cd hello

        cordova platform add browser        
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

        - guide
        https://cordova.apache.org/docs/en/latest/guide/platforms/ios/index.html#requirements-and-support

        - install xcode
        open it to accept terms
        
        - install xcode-select
        xcode-select --install
        
        - install ios-deploy (deploy from command line)
        sudo npm install -g ios-deploy --unsafe-perm=true
        
        for error ...but active developer directory '/Library/Developer/CommandLineTools' is a command line tools instance
        sudo xcode-select -s /Applications/Xcode.app/Contents/Developer
        
        - add platform
        cordova platform add ios

        - simulator test
        cordova run ios -d (directly without opening xcode)

        - run in xcode
        import platforms/ios (do not modify code in xcode)
        HelloCordova -> iPhone8 plus (to use older ios version, preferences -> components)
        run it (cordova build after any change)
        check log from console.log() in xcode output window

        - run in real device
        configure xcode dev account / team
        ...

- android

        - guide
        https://cordova.apache.org/docs/en/latest/guide/platforms/android/index.html#requirements-and-support

        - install gradle
        brew install gradle

        - install android studio

        - add platform
        cordova platform add android

        - simulator test
        cordova requirements (no target installed -> Failed to find target with hash string 'android-28' -> reinstall api level 28)
        start AVD in avd manager (ok to close studio)
        cordova run android (build apk and install to avd, no need to restart avd)

        - run in android studio
        import platforms/android
        run it (cordova build after any change)

        - run in real device
        cordova build (after any change)
        ...

- plugin

        A plugin exposes a Javascript API for native SDK functionality
        https://cordova.apache.org/plugins/
        
        - bluethooth
        cordova plugin add cordova-plugin-bluetoothle
        
        update www/js/index.js
        bluetoothle.initialize(initializeResult, params);

        add bluetooth background mode in ios plist file
        update hello/config.xml

        add android permission
        ...

        - device
        cordova plugin add cordova-plugin-device

- config

        - guide
        https://cordova.apache.org/docs/en/latest/config_ref/index.html#config-file

- dev

        git clone xxx
        ./addPlatform.sh (node_modules / platforms / plugins)

        webpack
        onsenui
        
        