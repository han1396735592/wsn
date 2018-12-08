package cn.qqhxj.websockerdome;

import cn.qqhxj.websockerdome.rxtx.SerialReader;
import cn.qqhxj.websockerdome.rxtx.SerialUtils;
import cn.qqhxj.websockerdome.rxtx.VariableLengthSerialReader;
import gnu.io.SerialPort;

import java.io.IOException;

/**
 * @author han xinjian
 * @date 2018-11-18 23:11
 **/
public class SerialContext {

    private  static SerialPort serialPort;

    private static SerialReader serialReader;

    public static void setSerialReader(SerialReader serialReader) {
        SerialContext.serialReader = serialReader;
    }

    public static void setSerialPort(SerialPort serialPort) {
        SerialContext.serialPort = serialPort;
        try {
            if (serialPort!=null){
                serialReader = new VariableLengthSerialReader(serialPort.getInputStream(),'{','}');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readData(){
        return serialReader.readString();
    }
    public static  boolean sendDate(String data){

        return SerialUtils.sendData(serialPort,data.getBytes());
    }

    public static SerialPort getSerialPort() {
        return serialPort;
    }
}
