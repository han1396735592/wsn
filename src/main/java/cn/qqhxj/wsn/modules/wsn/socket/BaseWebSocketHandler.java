package cn.qqhxj.wsn.modules.wsn.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author han xinjian
 * @date 2018-12-26 13:06
 **/
@Slf4j
@Component
public class BaseWebSocketHandler extends TextWebSocketHandler {


    private static CopyOnWriteArraySet<WebSocketSession> socketSessions = new CopyOnWriteArraySet<>();


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        socketSessions.add(session);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        socketSessions.remove(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println(message.getPayload());
    }
}
