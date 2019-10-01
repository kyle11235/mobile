
# android

## insntall

        - official website
        - http://tools.android-studio.org

- mac bundle

        - download bundle from http://tools.android-studio.org
        - open eclipse, popup dialogue shows the link to download jdk for mac from apple website
        - follow below instruction to install jdk

        sudo vi /etc/profile
        export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home
        source /etc/profile

        configure eclipse -> installed jre -> choose jdk path
        cd sdk/tools && ./android sdk (open sdk manager)

- windows manual

        - insntall ADT in eclipse, ADT = sdk manager + emulator
        - if online not work

                - follow http://developer.android.com/sdk/installing/installing-adt.html
                - uncheck contact all　... sites
                - NDK error -> http://tools.android.com/recent/usingthendkplugin

        - restart eclipse, check to download one of the sdk
        - or manually download sdk, put into sdk manager dirtory

- windows bundle

        - ADT Bundle = Eclipse + ADT plugin + sdk tools
        - get the adt bundle http://tools.android-studio.org/index.php/adt-bundle-plugin
        - for more SDK, tools, system images, try below proxies, reload package && install them

                - open SDK manager, set http://android-mirror.bugly.qq.com:8080/android/repository/addon.xml as 'User Defined Sites', set android-mirror.bugly.qq.com    8080 as proxy setting , quick in the morning
                - mirrors.neusoft.edu.cn 80, select force checkbox...

        - if manually install any sdk, get a android-21(API level 21/Android 5) from http://tools.android-studio.org/index.php/sdk, put it into adt-bundle-windows-x86-20140702\sdk\platforms, and refresh Android manager in eclipse

## basic

- create AVD

        - firstly need to install SDK System images
        set proxy in sdk manager, there are many options availible(might need to clear cache, reload... ).
        required system image, for CPU/ABI
        download and extract, move to sdk/system-images (create it if not exist)
        tools -> option -> Android SDK Manager Setting
        clear cache, restart Eclipse / Android Studio and SDK Manager
        download it from http://tools.android-studio.org/, I downloaded the system image for Android 5(JDK1.7 required).

        - click AVD manager in eclipse, create, select HVGA as device, then you can select system image now.
        see 1 create AVD.PNG

- helloworld

        - guide https://developer.android.com/training/basics/firstapp/
        - create android project
        - start a AVD
        - debug project as android application, log view will be open
        - then you can see main activity in the AVD, and Hello icon is listed on the screen of AVD
        - you can export the project as apk and install it to real device
        - if change the view, no need to restart AVD, just stop project and restart it.

- components

        Activity, lifecycle
        Service
        BrocastReceiver
        ContentProvider
        Intent

- fragment -> cheeck the 3 activities

        why use fragment(e.g. headline + article)
        /*
         * When designing your application to support a wide range of screen
         * sizes, you can reuse your fragments in different layout
         * configurations to optimize the user experience based on the available
         * screen space.
         */

        - MainActivity interacts with DisplayMessageActivity
        - MyFragmentActivity.java , this is static usage, the fragments are defined in news_article.xml in layout-large folder,
                you can also define normal layout in layout folder
        - MyDynamicFragmentActivity, this one shows how to set layout based on screen size, if it's normal size, only create headline fragment.
                if it's large size, use defined layout(headline+article) in layout-large folder, the activity interacts with headline fragment by OnHeadlineSelectedListener,
        // to dynamically add and remove fragments ,must sue fragment manager

- abd is debug tool

        configure adb, /Users/Alice/Kyle/android/adt-bundle-mac-x86_64-20140702/sdk/platform-tools
        vi /etc/profile
        export PATH=${PATH}:/Users/Alice/Kyle/android/adt-bundle-mac-x86_64-20140702/sdk/platform-tools
        abd shell
        cd data/data
        ls -l
        check 14-SQLite
        adb uninstall com.example.test

- issues

        - issue about android.support.v7 / missing style, this's created when you create the first prohect, for other projects
        - if new SDK changes API21 style to API23 style, change project declaration to API23
        - dev ui issue, view cannot be displayed becasue api is greater than plug-in

                - option1,works,downgrade the API level in preview view
                - option2,set host 203.208.48.134 dl-ssl.google.com
                help -> install new software。In the Work with field, input https://dl-ssl.google.com/android/eclipse/
                check Developer Tools / Android Development Tools -> next to install
                - option3, edit project.properties -> target=android-18

        - image size exceeds jvm

                - scale down to 1/10
                        ImageView iv = new ImageView(this);
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = false;
                        options.inSampleSize = 10;//缩小倍数
                        iv.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.ballon,options));
                        this.setContentView(iv);
                - async scale to allowed level
                        BitmapUtil.loadBitmap(getResources(), R.drawable.ballon, iv, 576, 384, null);
                - use cache if issue with multiple images
                        Bitmap bitmap = bitmapCache.getBitmap(this, R.drawable.ballon, 576, 384);

- android version / api level

        https://developer.android.com/guide/topics/manifest/uses-sdk-element
        https://cordova.apache.org/docs/en/latest/guide/platforms/android/index.html#requirements-and-support
