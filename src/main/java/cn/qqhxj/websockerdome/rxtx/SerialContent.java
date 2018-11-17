package cn.qqhxj.websockerdome.rxtx;

import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * @author han xinjian
 * @date 2018-11-14 23:28
 **/
public class SerialContent{

    private String portName ;

    private InputStream inputStream;

    private OutputStream outputStream;

    private SerialPort serialPort;

    private int portStatus;

    private int sendLength;

    private int readLength;

    public boolean sendCharData(char ch){
      return sendBytesData(Character.toString(ch).getBytes());
    }

    public boolean sendStringData(String str){
        return sendBytesData(str.getBytes());
    }

    public boolean sendBytesData(byte[] bytes){
        try {
            outputStream.write(bytes);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public   byte[] readData() {
        byte[] rbuff = new byte[1024];
        int hasRead = 0;
        StringBuffer stringBuffer = new StringBuffer("");
        try {
            while((inputStream.read(rbuff)) !=-1) {
                stringBuffer.append(new String(rbuff, 0, hasRead));
                break;
            }
            return stringBuffer.toString().getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
