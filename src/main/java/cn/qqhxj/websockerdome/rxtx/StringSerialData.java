package cn.qqhxj.websockerdome.rxtx;

/**
 * @author han xinjian
 * @date 2018-11-14 23:48
 **/
public class StringSerialData implements SerialData<String> {

    private String data;
    @Override
    public int length() {
        return data.toCharArray().length;
    }
}
