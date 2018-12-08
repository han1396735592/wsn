package cn.qqhxj.websockerdome.rxtx;

/**
 * @author han xinjian
 * @date 2018-12-03 13:01
 **/
public interface SerialReader {
    /**
     * 读取一个数据
     * @return
     */
    String readString();

    /**
     *  读取byte数组
     * @return
     */
    byte[] readBytes();
}
