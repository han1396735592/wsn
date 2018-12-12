package cn.qqhxj.websockerdome;


import cn.qqhxj.common.rxtx.ReaderAndWrite.SerialReader;
import cn.qqhxj.common.rxtx.ReaderAndWrite.VariableLengthSerialReader;
import cn.qqhxj.common.rxtx.SerialContext;
import cn.qqhxj.common.rxtx.SerialUtils;
import cn.qqhxj.common.rxtx.core.DefaultSerialDataListener;
import cn.qqhxj.common.rxtx.core.StringSerialDataParser;
import cn.qqhxj.common.rxtx.core.StringSerialDataProcessor;
import gnu.io.SerialPort;

import java.io.IOException;
import java.util.TooManyListenersException;

/**
 * @author han xinjian
 * @date 2018-12-11 13:56
 **/
public class App extends Thread {

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

    @Override
    public void run() {
        SerialPort serialPort = null;
        try {
            serialPort = SerialUtils.connect(SerialUtils.getCommNames().get(0), 115200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SerialContext.setSerialPort(serialPort);
        try {
            SerialReader serialReader = new VariableLengthSerialReader(serialPort.getInputStream(),'{','}');
            SerialContext.setSerialReader(serialReader);
        } catch (IOException e) {

            e.printStackTrace();
        }
        SerialContext.getSerialDataParserSet().add(new StringSerialDataParser());
        SerialContext.getSerialDataProcessorSet().add(new StringSerialDataProcessor());
        try {
            serialPort.addEventListener(new DefaultSerialDataListener());
        } catch (TooManyListenersException e) {
            e.printStackTrace();
        }
        serialPort.notifyOnDataAvailable(true);
        while (true);
    }
}
