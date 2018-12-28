$(function(){



    var webSocket = new WebSocket("ws://localhost:8080/wsd");

    webSocket.onopen=function(){
		console.log("socket ok ")
	}

	webSocket.onmessage=function(ws){
    	console.log(ws)
        var data = $.parseJSON( ws.data );
        console.log(data)
        Hoption.series[0].data.shift();

        Hoption.series[0].data.push(data.humidityValue)

        Toption.series[0].data.shift();
        HmyChart.setOption(Hoption, true);
        Toption.series[0].data.push(data.temperatureValue)

        TmyChart.setOption(Toption, true);
	}

	webSocket.onclose=function (ev) {
    	console.log("close socket");
	}



    var dom = document.getElementById("Humidity");
    var HmyChart = echarts.init(dom);
    var Tdom = document.getElementById("Temperature");
    var TmyChart = echarts.init(Tdom);

    var Hoption = {
        title:{
            text:"湿度变化曲线"
        },
        xAxis: {
            type: 'category',
            data: ['周一','周二','周三','周四','周五','周六','周日']
        },
        yAxis: {
            type: 'value',

        },
        series: [{
            data: [18, 23, 23, 24, 34, 12, 11,],
            type: 'line'
        }]
    };

    HmyChart.setOption(Hoption, true);

    var Toption = {
        title:{
            text:"温度变化曲线"
        },
        xAxis: {
            type: 'category',
            data: ['周一','周二','周三','周四','周五','周六','周日']
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: [18, 23, 23, 24, 34, 12, 11],
            type: 'line'
        }]
    };

    TmyChart.setOption(Toption, true);

});
