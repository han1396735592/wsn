package cn.qqhxj.websockerdome.rxtx;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author han xinjian
 * @date 2018-12-08 17:20
 **/
public class LiveControlSerialReader implements SerialReader {

    private byte[] startChat;

    private int flagIndex;

    private int dataLengthIndex;

    private int allLength = 0;

    private InputStream inputStream;

    private int length;

    private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    private boolean notOver = false;

    public LiveControlSerialReader(InputStream inputStream,  int flagIndex, int dataLengthIndex,byte... startChat ) {
        this.inputStream = inputStream;
        this.startChat = startChat;
        this.flagIndex = flagIndex;
        this.dataLengthIndex = dataLengthIndex;
    }

    @Override
    public String readString() {
        byte[] bytes = readBytes();
        if (bytes != null) {
            if (bytes.length > 0) {
                return new String(readBytes());
            }
        }
        return null;
    }

    @Override
    public byte[] readBytes() {
        if (notOver) {
            try {
                int bt = inputStream.read();

                if (allLength == dataLengthIndex) {
                    length = bt;
                }
                if (allLength > flagIndex) {
                    length -= 1;
                }

                if (length == 0) {
                    notOver = false;
                    byte[] array = Arrays.copyOf(byteBuffer.array(), byteBuffer.position());
                    byteBuffer = ByteBuffer.allocate(1024);
                    return array;
                }
                allLength += 1;
                byteBuffer.put((byte) bt);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                byte read = ((byte) inputStream.read());
                int index = Arrays.binarySearch(startChat, read);
                if (index!=-1) {
                    byteBuffer.put((byte) read);
                    allLength += 1;
                    notOver = true;
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}