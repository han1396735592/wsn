package cn.qqhxj.websockerdome.rxtx.core;

/**
 * @author han xinjian
 * @date 2018-12-11 13:52
 **/

public class StringSerialDataParser implements SerialDataParser<String> {
    @Override
    public String parse(byte[] bytes) {
        return new String(bytes);
    }
}

