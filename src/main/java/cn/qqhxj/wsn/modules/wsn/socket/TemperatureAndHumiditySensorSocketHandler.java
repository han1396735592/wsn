package cn.qqhxj.wsn.modules.wsn.socket;


import cn.qqhxj.common.wsn.sensor.TemperatureAndHumiditySensor;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author han xinjian
 * @date 2018-11-20 17:29
 **/
@Component
@Slf4j
@ServerEndpoint(value = "/wsd")
public class TemperatureAndHumiditySensorSocketHandler {
    private static CopyOnWriteArraySet<TemperatureAndHumiditySensorSocketHandler> webSocketSet
            = new CopyOnWriteArraySet<TemperatureAndHumiditySensorSocketHandler>();

    private Session session;


    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
    }


    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        log.info("欢迎id={}你的加入成功" , session.getId());
    }

    public void sendMessage(String string) throws IOException {
        this.session.getBasicRemote().sendText(JSONObject.toJSONString(string));
        //this.session.getAsyncRemote().sendText(message);
    }

    public void senData(TemperatureAndHumiditySensor t) throws IOException {
        this.session.getBasicRemote().sendText(JSONObject.toJSONString(t));
        //this.session.getAsyncRemote().sendText(message);
    }

    public static <T> void sendMessageToAll(String str) throws IOException {
        for (TemperatureAndHumiditySensorSocketHandler item : webSocketSet) {
            try {
                item.sendMessage(str);
            } catch (IOException e) {
                continue;
            }
        }
    }


    /**
     * 群发自定义消息
     */
    public static void sendDataToAll(TemperatureAndHumiditySensor t) throws IOException {
        log.info("群发自定义消息");
        for (TemperatureAndHumiditySensorSocketHandler item : webSocketSet) {
            try {
                item.senData(t);
            } catch (IOException e) {
                continue;
            }
        }
    }


}
