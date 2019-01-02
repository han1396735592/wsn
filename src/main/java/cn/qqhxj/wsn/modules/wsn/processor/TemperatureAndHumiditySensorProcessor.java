package cn.qqhxj.wsn.modules.wsn.processor;

import cn.qqhxj.common.rxtx.processor.SerialDataProcessor;
import cn.qqhxj.common.wsn.sensor.TemperatureAndHumiditySensor;
import cn.qqhxj.wsn.modules.wsn.service.SensorVoService;
import cn.qqhxj.wsn.modules.wsn.service.TemperatureAndHumiditySensorService;
import cn.qqhxj.wsn.modules.wsn.vo.SensorVo;
import cn.qqhxj.wsn.modules.wsn.vo.TemperatureAndHumiditySensorVo;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author han xinjian
 * @date 2018-11-20 17:27
 **/
@Slf4j
@Component
public class TemperatureAndHumiditySensorProcessor implements SerialDataProcessor<TemperatureAndHumiditySensor> {

    private SensorVoService sensorVoService;

    @Autowired
    private TemperatureAndHumiditySensorService temperatureAndHumiditySensorService;

    @Override
    public void processor(TemperatureAndHumiditySensor temperatureAndHumiditySensor) {
        SensorVo sensorVo = sensorVoService.getByIeeeAddress(temperatureAndHumiditySensor.getIeeeAddress());
        if (sensorVo == null) {
            BeanUtils.copyProperties(temperatureAndHumiditySensor, sensorVo);
            sensorVo.setLastReceiveDataTime(new Date());
            sensorVoService.save(sensorVo);
        } else {
            sensorVo.setLastReceiveDataTime(new Date());
            sensorVoService.updateById(sensorVo);
        }
        log.info("接收到了串口发送的数据:{}", JSONObject.toJSONString(temperatureAndHumiditySensor));
        TemperatureAndHumiditySensorVo temperatureAndHumiditySensorVo = new TemperatureAndHumiditySensorVo(temperatureAndHumiditySensor);
        temperatureAndHumiditySensorVo.setSensorVoId(sensorVo.getId());
        if (temperatureAndHumiditySensorService.save(temperatureAndHumiditySensorVo)) {
            log.debug("数据库保存了数据:{}", JSONObject.toJSONString(temperatureAndHumiditySensor));
        }

//        try {
////            TemperatureAndHumiditySensorSocketHandler.sendDataToAll(temperatureAndHumiditySensorVo);
////            WsnApplication.MAC_CMD.put(temperatureAndHumiditySensorVo.getIeeeAddress(),temperatureAndHumiditySensorVo);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
