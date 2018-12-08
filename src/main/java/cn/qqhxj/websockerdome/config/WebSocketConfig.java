package cn.qqhxj.websockerdome.config;

import cn.qqhxj.websockerdome.socker.SerialDataSendSockerHander;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author han xinjian
 * @date 2018-11-13 16:56
 **/
@Slf4j
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private SerialDataSendSockerHander serialDataSendSockerHander;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(serialDataSendSockerHander,"socket");
        log.info("添加了串口数据监听发送器",serialDataSendSockerHander);
    }

}