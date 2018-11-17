package cn.qqhxj.websockerdome.socker;

import cn.qqhxj.websockerdome.rxtx.SerialUtils;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Date;

/**
 * @author han xinjian
 * @date 2018-11-13 16:54
 **/
public class TestSockerHandler extends TextWebSocketHandler {

    private boolean read = false;
    private  SerialPort serialPort;

    @Override
    public synchronized void afterConnectionEstablished(WebSocketSession session) throws Exception {
        if(serialPort==null){
            serialPort = SerialUtils.connect(SerialUtils.getCommNames().get(0), 115200);
            serialPort.addEventListener( new SerialPortEventListener(){
                @Override
                public  synchronized void serialEvent(SerialPortEvent serialPortEvent) {
                    switch (serialPortEvent.getEventType()) {
                        case SerialPortEvent.BI:
                        case SerialPortEvent.OE:
                        case SerialPortEvent.FE:
                        case SerialPortEvent.PE:
                        case SerialPortEvent.CD:
                        case SerialPortEvent.CTS:
                        case SerialPortEvent.DSR:
                        case SerialPortEvent.RI:
                        case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                            System.out.println("err");
                            break;
                        case SerialPortEvent.DATA_AVAILABLE:
                            System.out.println(new String(SerialUtils.readData(serialPort)));
                            // read =true;
                            break;

                        default:
                            break;
                    }
                }
            });
            serialPort.notifyOnDataAvailable(true);
        }
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        long start = System.currentTimeMillis();
        String data = message.getPayload();
        char[] chars = data.toCharArray();
        for (char ch : chars){
            boolean sendData = SerialUtils.sendData(serialPort, Character.toString(ch).getBytes());
            if (sendData){
                System.out.println("数据发送成功:"+ch);
                while (!read){
                }
                read = false;
                byte[] bytes = SerialUtils.readData(serialPort);
                TextMessage textMessage = new TextMessage("返回的数据为："+new String(bytes));
                session.sendMessage(textMessage);
                System.out.println(textMessage);
            }else{
                TextMessage textMessage = new TextMessage(new Date() + "数据发送失败");
                session.sendMessage(textMessage);
            }
        }

        System.out.println(System.currentTimeMillis()-start);


    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }
}
