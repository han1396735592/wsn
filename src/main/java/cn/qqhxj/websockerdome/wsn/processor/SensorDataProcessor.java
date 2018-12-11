package cn.qqhxj.websockerdome.wsn.processor;

import cn.qqhxj.websockerdome.rxtx.core.SerialDataProcessor;
import cn.qqhxj.websockerdome.wsn.sensor.Sensor;

/**
 * @author han xinjian
 * @date 2018-12-11 11:44
 **/
@FunctionalInterface
public interface SensorDataProcessor<T extends Sensor> extends SerialDataProcessor<T> {

}
