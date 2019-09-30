# ios

- install

        xcode 9.4.1 for mac os 10.13.6 high sierra
        download xxx.xip from https://developer.apple.com/download/more/
        double click to extract and move to application
        xcode-select --install
        sudo -i
        npm install --unsafe-perm=true -g ios-deploy        
        
- deploy to device
        
        preference -> accounts -> xxx@126.com
        project -> general
                -> identity -> change bundle identifier from io.cordova.hellocordova to xxx@126.com
                -> signing -> team -> select the personal team
        usb connect to device
        HelloCordova -> your device name
        run
        error -> This iPhone 6 is running iOS 12.3.1 (16F203), which may not be supported by this version of Xcode. (google to solve it)

        - legacy (2015-12-05)
        xocde ->  preference -> account -> apple id

- swift

        - swift resources - Presentations, Documentation, and Sample Code
        https://developer.apple.com/swift/resources/

        - swift lanuage
        https://developer.apple.com/library/prerelease/ios/documentation/Swift/Conceptual/Swift_Programming_Language/index.html#//apple_ref/doc/uid/TP40014097-CH3-ID0

        - get started
        https://developer.apple.com/library/prerelease/ios/referencelibrary/GettingStarted/DevelopiOSAppsSwift/index.html
