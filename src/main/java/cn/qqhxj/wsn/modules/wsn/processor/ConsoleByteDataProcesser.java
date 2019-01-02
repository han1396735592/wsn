package cn.qqhxj.wsn.modules.wsn.processor;

import cn.qqhxj.common.rxtx.processor.SerialByteDataProcesser;
import cn.qqhxj.wsn.modules.wsn.socket.ConsoleWebSocketHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author han xinjian
 * @date 2018-12-24 9:36
 **/
@Slf4j
public class ConsoleByteDataProcesser implements SerialByteDataProcesser {
    public ConsoleByteDataProcesser(){
        log.debug("配置了默认的串口数据处理器={}",this);
    }

    @Override
    public void processer(byte[] bytes) {
        String result = bytesToHexString(bytes);
        log.debug("接收到:{}",result);
        try {
            ConsoleWebSocketHandler.sendMessageToAll(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String bytesToHexString(byte[] bArr) {
        StringBuffer sb = new StringBuffer(bArr.length);
        String sTmp;
        for (int i = 0; i < bArr.length; i++) {
            sTmp = Integer.toHexString(0xFF & bArr[i]);
            if (sTmp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTmp.toUpperCase() + " ");
        }
        return sb.toString();
    }
}
