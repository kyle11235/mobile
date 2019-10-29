/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
var app = {
    // Application Constructor
    initialize: function() {
        document.addEventListener('deviceready', this.onDeviceReady.bind(this), false);
    },
    // deviceready Event Handler
    //
    // Bind any cordova events here. Common events are:
    // 'pause', 'resume', etc.
    onDeviceReady: function() {

        // device ready
        console.log("device is ready");
        app.log("device is ready");

        // add more events
        document.addEventListener("pause", this.onPause, false);
        document.addEventListener("resume", this.onResume, false);
        document.addEventListener("menubutton", this.onMenuKeyDown, false);

        // get device info
        console.log("device.platform=" + device.platform); // device.platform=iOS

        // do not use inline function in html
        document.getElementById("click").addEventListener("click", this.click);
        document.getElementById("init").addEventListener("click", this.init);
        document.getElementById("scan").addEventListener("click", this.scan);
        document.getElementById("connect").addEventListener("click", this.connect);
        document.getElementById("discover").addEventListener("click", this.discover);
        document.getElementById("read").addEventListener("click", this.read);
        document.getElementById("subscribe").addEventListener("click", this.subscribe);
        document.getElementById("write").addEventListener("click", this.write);
        document.getElementById("clear").addEventListener("click", this.clear);

    },
    onPause: function() {
        console.log('onPause');
    },
    onResume: function() {
        console.log('onResume');
    },
    onMenuKeyDown: function() {
        console.log('onMenuKeyDown');
    },
    log: function(message, object) {
        $('#log').append('<div>' + message + '</div>')
    },
    click: function() {
        console.log('click');
        app.log('click');
    },
    init: function() {
        console.log('init');

        let params = {
            "request": true,
            "statusReceiver": false,
            "restoreKey" : "bluetoothleplugin"
        }
        bluetoothle.initialize(result => {
            console.log("init status=" + result.status);
            app.log("init status=" + result.status);
        }, params);

    },
    scan: function() {
        console.log('scan');

        let params = {
            "services": null,
            "allowDuplicates": true,
            "scanMode": bluetoothle.SCAN_MODE_LOW_LATENCY,
            "matchMode": bluetoothle.MATCH_MODE_AGGRESSIVE,
            "matchNum": bluetoothle.MATCH_NUM_MAX_ADVERTISEMENT,
            "callbackType": bluetoothle.CALLBACK_TYPE_ALL_MATCHES,
        }
          
        bluetoothle.startScan(success => {
            console.log("scan success=" + JSON.stringify(success));
            app.log('scan success');
            // scan success={"status":"scanResult","advertisement":{"serviceUuids":["180D","FEE7","180A","FEEA"],"manufacturerData":"7/DrQmHa3qs=","overflowServiceUuids":[],"isConnectable":1,"solicitedServiceUuids":["7905F431-B5CE-4E99-A40F-4B1E122D00D0"],"serviceData":{"180F":"PA=="},"localName":"P70-PRO"},"rssi":-87,"name":"P70-PRO","address":"2F44977F-5BFE-CC96-F19D-4FC4DE699825"}
        }, error => {
            console.log("scan error=" + JSON.stringify(error));
            app.log("scan error=" + JSON.stringify(error));
        }, params);

        // stop expensive scan aftert 5 seconds
        window.setTimeout(e => {
            bluetoothle.stopScan(success => {
                console.log("stop scan success=" + JSON.stringify(success));
            }, error => {
                console.log("stop error=" + JSON.stringify(error));
            });
        }, 5000);
    },
    connect: function() {
        // TODO - set a timeout for connect
        // TODO - if disconnected, again close, connect, discover, subscribe

        console.log('connect');

        // from scan
        let params = {
            "address": $('#address').val()
        }

        // close first for connect again
        bluetoothle.close(success => {
            console.log("close success=" + JSON.stringify(success));
            app.log("close success=" + JSON.stringify(success));
        }, error => {
            console.log("close error=" + JSON.stringify(error));
            app.log("close error=" + JSON.stringify(error));
        }, params);

        // first time will ask to pair
        bluetoothle.connect(success => {
            console.log("connect success=" + JSON.stringify(success));
            app.log("connect success=" + JSON.stringify(success));
            // success={"name":"P70-PRO","address":"2F44977F-5BFE-CC96-F19D-4FC4DE699825","status":"connected"}
            // success={"name":"P70-PRO","address":"2F44977F-5BFE-CC96-F19D-4FC4DE699825","status":"disconnected"}
        }, error => {
            console.log("connect error=" + JSON.stringify(error));
            app.log("connect error=" + JSON.stringify(error));
        }, params);
    },
    discover: function() {
        // a single discover is enough except for ios8
        console.log('discover');

        // from scan
        let params = {
            "address": $('#address').val(),
            "clearCache": true
        }

        bluetoothle.discover(success => {
            console.log("discover success=" + JSON.stringify(success));
            app.log('discover success');
        }, error => {
            console.log("discover error=" + JSON.stringify(error));
            app.log("discover error=" + JSON.stringify(error));
        }, params);
    },
    read: function() {
        console.log('read');

        let params = {
            "address": $('#address').val(),
            "service": $('#service').val(), // https://www.bluetooth.com/specifications/gatt/services/
            "characteristic": $('#characteristic').val() // https://www.bluetooth.com/specifications/gatt/characteristics/
        }

        bluetoothle.read(success => {
            console.log("read success=" + JSON.stringify(success));
            app.log('read value=' + bluetoothle.encodedStringToBytes(success.value));
        }, error => {
            console.log("read error=" + JSON.stringify(error));
            app.log("read error=" + JSON.stringify(error));
        }, params);
    },
    subscribe: function() {
        console.log('subscribe');

        let params = {
            "address": $('#address').val(),
            "service": $('#service').val(), // https://www.bluetooth.com/specifications/gatt/services/
            "characteristic": $('#characteristic').val() // https://www.bluetooth.com/specifications/gatt/characteristics/
        }

        bluetoothle.subscribe(success => {
            console.log("subscribe success=" + JSON.stringify(success));
            // success={"status":"subscribedResult","value":"FlEABA==","characteristic":"2A37","name":"P70-PRO","service":"180D","address":"2F44977F-5BFE-CC96-F19D-4FC4DE699825"}
            
            if(success.status === 'subscribed'){
                app.log('subscribed');
            } else if(success.status === 'subscribedResult') {
                app.log('subscribe value=' + bluetoothle.encodedStringToBytes(success.value));
            }
        }, error => {
            console.log("subscribe error=" + JSON.stringify(error));
            app.log("subscribe error=" + JSON.stringify(error));
        }, params);
    },
    write: function() {
        console.log('write');

        let string = "Write Hello World";
        let bytes = bluetoothle.stringToBytes(string);
        let encodedString = bluetoothle.bytesToEncodedString(bytes);

        //Note, this example doesn't actually work since it's read only characteristic - 2A19
        let params = {
            "value": encodedString,
            "address": $('#address').val(),
            "service": $('#service').val(), 
            "characteristic": $('#characteristic').val() ,
            "type":"noResponse"
        }

        bluetoothle.write(success => {
            console.log("write success=" + JSON.stringify(success));            
            app.log("write success");
        }, error => {
            console.log("write error=" + JSON.stringify(error));
            app.log("write error=" + JSON.stringify(error));
        }, params);
    },
    clear: function() {
        console.log('clear');
        $('#log').html('');
    },
};

app.initialize();