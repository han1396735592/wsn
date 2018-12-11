package cn.qqhxj.websockerdome.rxtx;

import cn.qqhxj.websockerdome.rxtx.ReaderAndWrite.SerialReader;
import cn.qqhxj.websockerdome.rxtx.ReaderAndWrite.VariableLengthSerialReader;
import cn.qqhxj.websockerdome.rxtx.core.SerialDataParser;
import cn.qqhxj.websockerdome.rxtx.core.SerialDataProcessor;
import gnu.io.SerialPort;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author han xinjian
 * @date 2018-11-18 23:11
 **/
public class SerialContext {

    private  static SerialPort serialPort;

    private static SerialReader serialReader;

    private static Set<SerialDataParser> serialDataParserSet = new ConcurrentSkipListSet<SerialDataParser>();;

    public static Set<SerialDataProcessor> serialDataProcessorSet = new ConcurrentSkipListSet<SerialDataProcessor>();

    public static Set<SerialDataProcessor> getSerialDataProcessorSet() {
        return serialDataProcessorSet;
    }

    public static Set<SerialDataParser> getSerialDataParserSet() {
        return serialDataParserSet;
    }

    public static void setSerialReader(SerialReader serialReader) {
        SerialContext.serialReader = serialReader;
    }

    public static void setSerialPort(SerialPort serialPort) {
        SerialContext.serialPort = serialPort;
        try {
            if (serialPort!=null){
                //serialReader = new LiveControlSerialReader(serialPort.getInputStream(), SensorDataInfo.FLAG_INDEX,SensorDataInfo.DADA_LENGTH_INDEX, SensorDataInfo.header);
                serialReader = new VariableLengthSerialReader(serialPort.getInputStream(),'{','}');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] readBytes(){

        return serialReader.readBytes();
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
