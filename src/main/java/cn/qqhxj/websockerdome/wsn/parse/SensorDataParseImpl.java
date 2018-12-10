package cn.qqhxj.websockerdome.wsn.parse;

import cn.qqhxj.websockerdome.wsn.SensorDataInfo;
import cn.qqhxj.websockerdome.wsn.SensorType;
import cn.qqhxj.websockerdome.wsn.sensor.Sensor;
import cn.qqhxj.websockerdome.wsn.sensor.TemperatureAndHumiditySensor;

import static cn.qqhxj.websockerdome.wsn.SensorType.*;

/**
 * @author han xinjian
 * @date 2018-12-08 19:13
 **/
public class SensorDataParseImpl implements ISensorDataParse {
    @Override
    public Sensor parse(byte[] bytes) {
        final char type = ((char) bytes[SensorDataInfo.FLAG_INDEX]);
        SensorType sensorType = valueOf(type+"");
        Sensor sensor = null;
        switch (sensorType){
            case A:
                break;
            case B:
                break;
            case E: sensor = new TemperatureAndHumiditySensor(bytes);
                break;
        }
        return sensor;
    }
}
