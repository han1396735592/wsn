package cn.qqhxj.wsn.modules.wsn.processor;

import cn.qqhxj.common.rxtx.processor.SerialDataProcessor;
import cn.qqhxj.common.wsn.sensor.AirPressureAltitudeSensor;
import cn.qqhxj.wsn.WsnApplication;
import cn.qqhxj.wsn.modules.wsn.service.AirPressureAltitudeSensorService;
import cn.qqhxj.wsn.modules.wsn.socket.AirPressureAltitudeSensorWebSocketHandler;
import cn.qqhxj.wsn.modules.wsn.vo.AirPressureAltitudeSensorVo;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

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
        AirPressureAltitudeSensorVo sensorVo = new AirPressureAltitudeSensorVo(airPressureAltitudeSensor);
        if( airPressureAltitudeSensorService.save(sensorVo)){
            log.debug("数据库保存了数据:{}", JSONObject.toJSONString(sensorVo));
        }
        try {
            AirPressureAltitudeSensorWebSocketHandler.sendDataToAll(sensorVo);
            WsnApplication.MAC_CMD.put(sensorVo.getIeeeAddress(),sensorVo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
