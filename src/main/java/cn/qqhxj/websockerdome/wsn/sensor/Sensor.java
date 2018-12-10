package cn.qqhxj.websockerdome.wsn.sensor;

import cn.qqhxj.websockerdome.wsn.SensorDataInfo;
import cn.qqhxj.websockerdome.wsn.SensorType;
import lombok.Data;

/**
 * @author han xinjian
 * @date 2018-12-08 19:08
 **/
@Data
public abstract  class  Sensor {

    private SensorType sensorType;

    private String ieeeAddress="";

    private String parentAddress="";

    private String address="";

    public Sensor(byte[] bytes){
        char type = (char) bytes[SensorDataInfo.FLAG_INDEX];
        sensorType = SensorType.valueOf(type+"");
        for (int i = 0;i<SensorDataInfo.PARENT_ADDRESS_LENGTH;i++){
            parentAddress += ((int) bytes[i + SensorDataInfo.PARENT_ADDRESS_START_INDEX]);
        }
        for (int i = 0;i<SensorDataInfo.ADDRESS_LENGTH;i++){
            address += ((int) bytes[i + SensorDataInfo.ADDRESS_START_INDEX]);
        }

        for (int i = bytes.length-SensorDataInfo.IEEE_ADDRESS_LENGTH;i<bytes.length;i++){
            ieeeAddress += ((int) bytes[i]);
        }
    }
}
