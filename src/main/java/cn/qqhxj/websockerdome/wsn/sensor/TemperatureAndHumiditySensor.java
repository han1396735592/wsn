package cn.qqhxj.websockerdome.wsn.sensor;

import cn.qqhxj.websockerdome.wsn.SensorDataInfo;
import lombok.Data;

/**
 * @author han xinjian
 * @date 2018-12-08 19:03
 **/
@Data
public class TemperatureAndHumiditySensor extends Sensor  {

    public TemperatureAndHumiditySensor(byte[] bytes) {
        super(bytes);
        humidityValue = Float.parseFloat(
                ((int) bytes[SensorDataInfo.DADA_LENGTH_INDEX])+"."+ ((int) bytes[SensorDataInfo.DADA_LENGTH_INDEX + 1]));
        temperatureValue = Float.parseFloat(
                ((int) bytes[SensorDataInfo.DADA_LENGTH_INDEX + 2])+"."+ ((int) bytes[SensorDataInfo.DADA_LENGTH_INDEX + 3]));
    }

    private float temperatureValue;

    private float humidityValue;

}
