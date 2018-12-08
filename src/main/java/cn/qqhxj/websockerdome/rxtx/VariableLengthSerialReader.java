package cn.qqhxj.websockerdome.rxtx;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author han xinjian
 * @date 2018-12-03 13:37
 **/
public class VariableLengthSerialReader implements SerialReader {

    private InputStream inputStream ;

    ByteBuffer byteBuffer =  ByteBuffer.allocate(1024);

    private char startChar ='{';

    private char endChar  = '}';

    private boolean haveNext = false;


    public VariableLengthSerialReader(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public VariableLengthSerialReader(InputStream inputStream,char startChar,char endChar){
        this(inputStream);
        this.startChar = startChar;
        this.endChar = endChar;
    }


    @Override
    public String readString() {
        byte[] bytes = readBytes();
        if (bytes!=null){
            if (bytes.length>0){
                return   new String(readBytes());
            }
        }
       return null;
    }

    @Override
    public byte[] readBytes() {
        int ch = 0;
        while (ch!=-1){
            try {
                ch = inputStream.read();
                if(ch==startChar){
                    byteBuffer.put((byte) ch);
                    haveNext = true;
                    continue;
                }
                if(ch==endChar){
                    byteBuffer.put((byte) ch);
                    haveNext=false;
                    break;
                }
                if (haveNext){
                    byteBuffer.put((byte) ch);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (byteBuffer.position()!=0){
            byte[] array = Arrays.copyOf(byteBuffer.array(),byteBuffer.position());
            byteBuffer = ByteBuffer.allocate(1024);
            return array;
        }

        return null;
    }
}
