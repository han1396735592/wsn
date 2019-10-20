$(function () {
    var webSocket = new WebSocket("ws://localhost:8080/consoleSocket");

    webSocket.onopen = function () {
        console.log("打开了控制台，对数据进行监听")
    }

    webSocket.onmessage = function (ws) {

        var data = $.parseJSON(ws.data);

        console.log(data)
        var d = new Date().toLocaleString() + ": " + data;
        console.log(d)
        if (vm.$data.receive_data.length >= 10)
            vm.$data.receive_data.shift();
        vm.$data.receive_data.push(d)

    }

    webSocket.onclose = function (ev) {
        console.log("退出了控制台");
    }


    $.get("/serial/getList", function (res) {
        vm.$data.portNameList = res;
    });
    $.get("/serial/status", function (res) {
        if (res.mes = "ok") {
            vm.$data.INFO = res.data;
            vm.$data.isOpen = res.data.portName != null
        }

    });

    var vm = new Vue({
        el: "#console",
        data: {
            isOpen: false,
            receive_data: [],
            portNameList: ["COM1", "COM2", "COM3", "COM4"],
            baudRgotList: [38400, 57200, 115200, 9600, 4800],
            dataBitsList: [8, 7, 6, 5, 4, 3, 2, 1,],
            stopBitsList: [1, 0],
            parityList: [0, 1],
            command: "",
            msg: "",
            portInfo: {
                portName: "COM3",
                baudRgot: 38400,
                dataBits: 8,
                stopBits: 1,
                parity: 0
            }
        },
        filters: {},

        methods: {
            sendCommand() {
                $.get("/cmd?cmd=" + vm.$data.command, function (res) {
                        console.log(res.data)
                    }
                )
            },
            openOrClose: function () {
                console.log("switch")
                console.log(JSON.stringify(this.portInfo))

                if (!this.isOpen) {
                    $.get("/serial/open", this.portInfo, function (res) {
                        console.log(res);
                        vm.$data.isOpen = true;
                        console.log("change isOpen")
                    });
                } else {
                    $.get("/serial/close", function (res) {
                        console.log(res);
                        vm.$data.isOpen = false;
                        console.log("change isOpen")
                    });
                }


            }

        },
        watch: {
            isOpen: function (newValue, oldValue) {
                $("#status-led").css("background-color", newValue ? "red" : "green");
                this.isOpen = newValue;
            }
        },
        mounted: function () {


        }


    })


});
