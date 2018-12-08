package cn.qqhxj.websockerdome.wsn.sensor;

import cn.qqhxj.websockerdome.wsn.SensorInfo;
import cn.qqhxj.websockerdome.wsn.SensorType;
import lombok.Data;

/**
 * @author han xinjian
 * @date 2018-12-08 19:03
 **/
@Data
public class TemperatureAndHumiditySensor extends Sensor implements SensorInfo {

    private SensorType type;


    private float temperatureValue;

    private float humidityValue;

    @Override
    public SensorType getType() {
        return null;
    }
}
