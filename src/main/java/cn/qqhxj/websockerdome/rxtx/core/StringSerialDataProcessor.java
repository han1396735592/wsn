package cn.qqhxj.websockerdome.rxtx.core;

/**
 * @author han xinjian
 * @date 2018-12-11 13:55
 **/
public class StringSerialDataProcessor implements SerialDataProcessor<String> {
    @Override
    public void processor(String string) {
        System.out.println("StringSerialDataProcessor");
        System.out.println(string);
    }
}
