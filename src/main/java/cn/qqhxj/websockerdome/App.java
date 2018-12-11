package cn.qqhxj.websockerdome;

import cn.qqhxj.websockerdome.rxtx.SerialContext;
import cn.qqhxj.websockerdome.rxtx.SerialUtils;
import cn.qqhxj.websockerdome.rxtx.core.DefaultSerialDataListener;
import cn.qqhxj.websockerdome.rxtx.core.StringSerialDataParser;
import cn.qqhxj.websockerdome.rxtx.core.StringSerialDataProcessor;
import gnu.io.SerialPort;

import java.util.TooManyListenersException;

/**
 * @author han xinjian
 * @date 2018-12-11 13:56
 **/
public class App {
    public static void main(String[] args) {
        SerialPort serialPort = null;
        try {
            serialPort = SerialUtils.connect(SerialUtils.getCommNames().get(0), 115200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SerialContext.setSerialPort(serialPort);
        SerialContext.getSerialDataParserSet().add(new StringSerialDataParser());
        SerialContext.getSerialDataProcessorSet().add(new StringSerialDataProcessor());
        try {
            serialPort.addEventListener(new DefaultSerialDataListener());
        } catch (TooManyListenersException e) {
            e.printStackTrace();
        }
        serialPort.notifyOnDataAvailable(true);
        while (true){

        }
    }
}
