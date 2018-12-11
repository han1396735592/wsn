package cn.qqhxj.websockerdome.wsn.parse;

import cn.qqhxj.websockerdome.rxtx.core.SerialDataParser;
import cn.qqhxj.websockerdome.wsn.sensor.Sensor;

/**
 * @author han xinjian
 * @date 2018-12-08 19:09
 **/
@FunctionalInterface
public interface ISensorDataParse extends SerialDataParser<Sensor> {

}
