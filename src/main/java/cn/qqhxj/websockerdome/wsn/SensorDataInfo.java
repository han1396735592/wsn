package cn.qqhxj.websockerdome.wsn;

/**
 * @author han xinjian
 * @date 2018-12-08 19:14
 **/
public final class SensorDataInfo {

    public static int FLAG_INDEX = 6;

    public static byte[] header = new byte[]{(byte) 0xFA, (byte) 0xFD};

    public static int DADA_LENGTH_INDEX = 7;

    public static int PARENT_ADDRESS_START_INDEX = 2;

    public static int PARENT_ADDRESS_LENGTH = 2;

    public static int ADDRESS_START_INDEX = 4;

    public static int ADDRESS_LENGTH= 2;

    public static int IEEE_ADDRESS_LENGTH = 8;

    public static int BEGIN_DATA_LENGTH = 7;

}
