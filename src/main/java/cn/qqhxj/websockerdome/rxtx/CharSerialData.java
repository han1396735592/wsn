package cn.qqhxj.websockerdome.rxtx;

/**
 * @author han xinjian
 * @date 2018-11-14 23:46
 **/
public class CharSerialData implements SerialData<Character> {
    private Character data;
    @Override
    public int length() {
        return 1;
    }
}
