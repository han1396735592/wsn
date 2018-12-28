package cn.qqhxj.wsn.modules.wsn.processor;

import cn.qqhxj.common.rxtx.processor.SerialDataProcessor;
import cn.qqhxj.common.wsn.sensor.RFIDSensor;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author han xinjian
 * @date 2018-11-20 17:27
 **/
@Slf4j
@Component
public class RFIDSensorProcessor implements SerialDataProcessor<RFIDSensor> {


    @Override
    public void processor(RFIDSensor rfidSensor) {
        System.out.println(JSONObject.toJSONString(rfidSensor));
        log.debug("接收到了RFID数据，卡号为：{}", rfidSensor.getCardNumber());



    }
}

