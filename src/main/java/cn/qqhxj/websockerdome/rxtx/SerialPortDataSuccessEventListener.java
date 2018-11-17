package cn.qqhxj.websockerdome.rxtx;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

/**
 * @author han xinjian
 * @date 2018-11-14 23:36
 **/
public interface SerialPortDataSuccessEventListener extends SerialPortEventListener {


//    boolean

    @Override
    void serialEvent(SerialPortEvent serialPortEvent);
}
