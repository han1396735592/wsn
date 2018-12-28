package cn.qqhxj.wsn.modules.wsn.processor;

import cn.qqhxj.common.rxtx.processor.SerialDataProcessor;
import cn.qqhxj.common.wsn.sensor.TemperatureAndHumiditySensor;
import cn.qqhxj.wsn.WsnApplication;
import cn.qqhxj.wsn.modules.wsn.service.TemperatureAndHumiditySensorService;
import cn.qqhxj.wsn.modules.wsn.socket.TemperatureAndHumiditySensorSocketHandler;
import cn.qqhxj.wsn.modules.wsn.vo.TemperatureAndHumiditySensorVo;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author han xinjian
 * @date 2018-11-20 17:27
 **/
@Slf4j
@Component
public class TemperatureAndHumiditySensorProcessor implements SerialDataProcessor<TemperatureAndHumiditySensor> {

    @Autowired
    private TemperatureAndHumiditySensorService temperatureAndHumiditySensorService;

    @Override
    public void processor(TemperatureAndHumiditySensor temperatureAndHumiditySensor) {
        log.info("接收到了串口发送的数据:{}", JSONObject.toJSONString(temperatureAndHumiditySensor));
        TemperatureAndHumiditySensorVo temperatureAndHumiditySensorVo = new TemperatureAndHumiditySensorVo (temperatureAndHumiditySensor);
        if( temperatureAndHumiditySensorService.save(temperatureAndHumiditySensorVo)){
            log.debug("数据库保存了数据:{}",JSONObject.toJSONString(temperatureAndHumiditySensor));
        }

        try {
            TemperatureAndHumiditySensorSocketHandler.sendDataToAll(temperatureAndHumiditySensorVo);
            WsnApplication.MAC_CMD.put(temperatureAndHumiditySensorVo.getIeeeAddress(),temperatureAndHumiditySensorVo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
