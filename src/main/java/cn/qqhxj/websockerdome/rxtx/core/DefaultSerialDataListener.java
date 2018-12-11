package cn.qqhxj.websockerdome.rxtx.core;

import cn.qqhxj.websockerdome.rxtx.SerialContext;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * @author han xinjian
 * @date 2018-12-11 12:02
 **/
public class DefaultSerialDataListener implements SerialPortEventListener {
    @Override
    public void serialEvent(SerialPortEvent ev) {
        if (ev.getEventType()==SerialPortEvent.DATA_AVAILABLE){
            Set<SerialDataParser> parserSet = SerialContext.getSerialDataParserSet();
            byte[] bytes = SerialContext.readBytes();
            Object parse = null;
            for (SerialDataParser serialDataParser:parserSet){
                 parse = serialDataParser.parse(bytes);
                if (parse!=null){
                    break;
                }
            }
            if (parse!=null){
                Set<SerialDataProcessor> dataProcessors = SerialContext.getSerialDataProcessorSet();
                for (SerialDataProcessor serialDataProcessor:dataProcessors){
                    Type[] types = serialDataProcessor.getClass().getGenericInterfaces();
                    String typeName = ((ParameterizedType) types[0]).getActualTypeArguments()[0].getTypeName();
                    try {
                        Class<?> forName = Class.forName(typeName);
                        if(forName==parse.getClass()){
                            serialDataProcessor.processor(parse);
                            break;
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else{
            System.out.println("data err");
        }
    }
}
