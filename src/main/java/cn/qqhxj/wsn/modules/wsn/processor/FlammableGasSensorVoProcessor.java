package cn.qqhxj.wsn.modules.wsn.processor;

import cn.qqhxj.common.rxtx.processor.SerialDataProcessor;
import cn.qqhxj.common.wsn.sensor.FlammableGasSensor;
import cn.qqhxj.wsn.WsnApplication;
import cn.qqhxj.wsn.modules.wsn.service.FlammableGasSensorVoService;
import cn.qqhxj.wsn.modules.wsn.socket.TemperatureAndHumiditySensorSocketHandler;
import cn.qqhxj.wsn.modules.wsn.vo.FlammableGasSensorVo;
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
public class FlammableGasSensorVoProcessor implements SerialDataProcessor<FlammableGasSensor> {
    @Autowired
    private FlammableGasSensorVoService flammableGasSensorVoService;

    @Override
    public void processor(FlammableGasSensor flammableGasSensor) {
        FlammableGasSensorVo sensorVo = new FlammableGasSensorVo(flammableGasSensor);
        if( flammableGasSensorVoService.save(sensorVo)){
            log.debug("数据库保存了数据:{}", JSONObject.toJSONString(sensorVo));
        }
//            TemperatureAndHumiditySensorSocketHandler.sendDataToAll(sensorVo);
            WsnApplication.MAC_CMD.put(sensorVo.getIeeeAddress(),sensorVo);
    }
}
