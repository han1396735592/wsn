package cn.qqhxj.wsn.modules.wsn.processor;

import cn.qqhxj.common.rxtx.processor.SerialDataProcessor;
import cn.qqhxj.common.wsn.sensor.FlameSensor;
import cn.qqhxj.wsn.WsnApplication;
import cn.qqhxj.wsn.modules.wsn.service.FlameSensorVoService;
import cn.qqhxj.wsn.modules.wsn.vo.FlameSensorVo;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author han xinjian
 * @date 2018-12-20 16:35
 **/
@Slf4j
@Component
public class FlameSensorProcessor implements SerialDataProcessor<FlameSensor> {
    @Autowired
    private FlameSensorVoService flameSensorVoService;

    @Override
    public void processor(FlameSensor flameSensor) {
        FlameSensorVo sensorVo = new FlameSensorVo(flameSensor);
        if (flameSensorVoService.save(sensorVo)) {
            log.debug("数据库保存了数据:{}", JSONObject.toJSONString(sensorVo));
        }
        WsnApplication.MAC_CMD.put(sensorVo.getIeeeAddress(), sensorVo);
    }
}
