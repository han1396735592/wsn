package cn.qqhxj.websockerdome.socker;


import cn.qqhxj.common.rxtx.SerialContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashSet;
import java.util.Set;

/**
 * @author han xinjian
 * @date 2018-11-20 17:29
 **/
@Component
@Slf4j
public class SerialDataSendSockerHander extends TextWebSocketHandler {

    private  Set<WebSocketSession> sessionSet = new HashSet<WebSocketSession>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("add id= "+session.getId());
        sessionSet.add(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String payload = message.getPayload().toString();
        if(!StringUtils.isEmpty(payload))
        {
            boolean sendDate = SerialContext.sendData(payload.getBytes());

            if(sendDate){
                log.info("{} 发送了数据到串口data={}",session,payload);
            }else{
                log.error("{} 发送数据失败：data ={}", session,payload);
            }
        }else{
            log.info("{} 发送的数据没有意义，拒绝发送到串口");
        }
    }

    public Set<WebSocketSession> getSessionSet() {
        return sessionSet;
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionSet.remove(session);
    }
}
