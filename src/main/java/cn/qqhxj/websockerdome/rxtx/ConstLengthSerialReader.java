package cn.qqhxj.websockerdome.rxtx;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author han xinjian
 * @date 2018-12-03 12:58
 **/
public class ConstLengthSerialReader implements  SerialReader {

    private InputStream inputStream;

    private int length;

    private int index = 0;

    private byte[] bytes;

    private boolean raed = true;


    @Override
    public String readString()   {
        for ( ;index<length;index++){
            try {
                int read = inputStream.read();
                if (read==-1){
                    break;
                }else{
                    bytes[index] = (byte) read;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (index==length){
            index=0;
            return new String(bytes);
        }

        return null;
    }

    public  ConstLengthSerialReader(InputStream inputStream){
        this.inputStream = inputStream;
        length = 24;
        bytes = new byte[length];
    }

    ConstLengthSerialReader(InputStream inputStream,int length){
        this.inputStream = inputStream;
        this.length = length;
        bytes = new byte[length];
    }




    public static void main(String[] args) {

        String str = "{as}{sa{asfasf}as{f}}saa{}s{f}";

        SerialReader serialReader =
        new VariableLengthSerialReader(new ByteArrayInputStream(str.getBytes()),'{','}');

        String string = serialReader.readString();

        while (string!=null){
            System.out.println(string);
            string = serialReader.readString();
        }




    }
}
