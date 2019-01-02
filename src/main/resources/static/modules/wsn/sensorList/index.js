layui.table.render({
    elem: '#table'
    , height: 500
    , url: '/wsn/sensorVo/getAll' //数据接口
    , page: true //开启分页
    , cols: [[ //表头
        {
            field: 'id', title: 'ID', width: 60, sort: true, fixed: 'left'
        },
        {
            field: 'lastReceiveDataTime', title: '最后更新数据时间', width: 200,
        }, {
            field: 'address', title: '地址', width: 80
        },
        {
            field: 'parentAddress', title: '父地址', width: 80
        }
        , {
            field: 'sensorType', title: '类型', width: 180
        }
        , {
            field: 'ieeeAddress', title: 'ieeeAddress', width: 180
        }, {
            field: 'type', title: '状态', width: 180, templet: function (sensor) {
                var lastReceiveDataTime = new Date(sensor.lastReceiveDataTime);
                var new_date = new Date();
                var max_interval = 5 * 60 * 60
                var status = "在线"
                if (new_date - lastReceiveDataTime > max_interval) {
                    var code = "FD 0F 3D 01 00 00 00 3D 01 A1 01 01 07 " + sensor.ieeeAddress;
                    $.get("http://localhost:8080/cmdByMac?mac=" + code + "&address=3D%2001&timeout=3000", function (res) {
                        console.log(res.code)
                        if (res.code != "200") {
                            status = "不在线";
                        }
                    })
                }
                return status
            }
        }
    ]], request: {
        pageName: 'current' //页码的参数名称，默认：page
        , limitName: 'size' //每页数据量的参数名，默认：limit
    },
    parseData: function (res) { //res 即为原始返回的数据
        console.log(res);
        return {
            "code": res.code, //解析接口状态
            "msg": res.msg, //解析提示文本
            "count": res.data.length, //解析数据长度
            "data": res.data //解析数据列表
        };
    }, response: {
        statusName: 'code' //规定数据状态的字段名称，默认：code
        , statusCode: 200 //规定成功的状态码，默认：0
        , msgName: 'msg' //规定状态信息的字段名称，默认：msg
        , countName: 'count' //规定数据总数的字段名称，默认：count
        , dataName: 'data' //规定数据列表的字段名称，默认：data
    }

});


