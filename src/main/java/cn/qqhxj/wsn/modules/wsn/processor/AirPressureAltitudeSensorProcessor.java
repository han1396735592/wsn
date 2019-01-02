package cn.qqhxj.wsn.modules.wsn.processor;

import cn.qqhxj.common.rxtx.processor.SerialDataProcessor;
import cn.qqhxj.common.wsn.sensor.AirPressureAltitudeSensor;
import cn.qqhxj.wsn.modules.wsn.service.AirPressureAltitudeSensorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author han xinjian
 * @date 2018-12-20 16:35
 **/
@Slf4j
@Component
public class AirPressureAltitudeSensorProcessor implements SerialDataProcessor<AirPressureAltitudeSensor> {

    @Autowired
    private AirPressureAltitudeSensorService airPressureAltitudeSensorService;

    @Override
    public void processor(AirPressureAltitudeSensor airPressureAltitudeSensor) {
//        if( airPressureAltitudeSensorService.save(sensorVo)){
//            log.debug("数据库保存了数据:{}", JSONObject.toJSONString(sensorVo));
//        }
//        try {
//            AirPressureAltitudeSensorWebSocketHandler.sendDataToAll(sensorVo);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
