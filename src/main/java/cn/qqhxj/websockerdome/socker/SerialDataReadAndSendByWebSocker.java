package cn.qqhxj.websockerdome.socker;

import cn.qqhxj.websockerdome.SerialContext;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Set;

/**
 * @author han xinjian
 * @date 2018-11-20 17:27
 **/
@Slf4j
public class SerialDataReadAndSendByWebSocker implements SerialPortEventListener {

    private Set<WebSocketSession> sessionSet;

    public SerialDataReadAndSendByWebSocker(SerialDataSendSockerHander serialDataSendSockerHander){
        this.sessionSet = serialDataSendSockerHander.getSessionSet();
    }

    @Override
    public void serialEvent(SerialPortEvent ev) {
        if(ev.getEventType()==SerialPortEvent.DATA_AVAILABLE){


            String data = SerialContext.readData();

            while (data!=null){
                log.info("接收到了串口发送的数据:={}=",data);
                if(!sessionSet.isEmpty()){
                    for (WebSocketSession session: sessionSet){
                        WebSocketMessage<String> message = new TextMessage(data);
                        try {
                            session.sendMessage(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    log.info("发送串口接收到的数据给所有人");

                }else{
                    log.info("没有人在线，没有人接收到数据");
                }
                data = SerialContext.readData();
            }

        }else{
            log.error("数据接收有误");
        }

    }
}
