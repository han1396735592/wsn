package cn.qqhxj.websockerdome.wsn.parse;

import cn.qqhxj.websockerdome.wsn.sensor.Sensor;

/**
 * @author han xinjian
 * @date 2018-12-08 19:09
 **/
@FunctionalInterface
public interface ISensorDataParse {
    /**
     *  传感器数据解析
     * @param bytes
     * @return
     */
    Sensor parse(byte[] bytes);
}
