package cn.qqhxj.websockerdome.controller;


import cn.qqhxj.common.rxtx.SerialContext;
import cn.qqhxj.common.rxtx.SerialUtils;
import cn.qqhxj.websockerdome.socker.SerialDataReadAndSendByWebSocker;
import cn.qqhxj.websockerdome.socker.SerialDataSendSockerHander;
import gnu.io.SerialPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author han xinjian
 * @date 2018-11-14 23:23
 **/
@RequestMapping("serial")
@Controller
@Slf4j
public class SerialController {


    @Autowired
    private SerialDataSendSockerHander serialDataSendSockerHander;

    @ResponseBody
    @GetMapping("open")
    public boolean open(@RequestParam(required = true) String portName,
                        @RequestParam(required = true,defaultValue = "57600") int  baudRgot ,
                        @RequestParam(required = false,defaultValue = "8") int dataBits,
                        @RequestParam(required = false,defaultValue = "1") int stopBits,
                        @RequestParam(required = false,defaultValue = "0") int parity ){
        try {
            if(SerialContext.getSerialPort()!=null){
                this.close();
            }
            SerialPort serialPort = SerialUtils.connect(portName, baudRgot,dataBits,stopBits,parity);
            SerialContext.setSerialPort(serialPort);
            serialPort.addEventListener(new SerialDataReadAndSendByWebSocker(serialDataSendSockerHander));
            serialPort.notifyOnDataAvailable(true);
            log.info("打开了端口：{}",serialPort);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("串口连接失败");
            return false;
        }
        return true;
    }

    @ResponseBody
    @GetMapping("getList")
    public List<String> getSerialList(){
        return SerialUtils.getCommNames();
    }

    @ResponseBody
    @GetMapping("close")
    public  boolean close(){
        SerialPort serialPort = SerialContext.getSerialPort();
        log.info("关闭了串口：{}"+serialPort);
        serialPort.close();
        SerialContext.setSerialPort(null);
        return true;
    }

    @ResponseBody
    @GetMapping("status")
    public Map getStatus(){
        HashMap<String, Object> map = new HashMap<>();
        SerialPort serialPort = SerialContext.getSerialPort();
        if (serialPort!=null){
            map.put("baudRgot",serialPort.getBaudRate());
            map.put("portName",serialPort.getName());
            map.put("stopBits",serialPort.getStopBits());
            map.put("dataBits",serialPort.getDataBits());
            map.put("parity",serialPort.getParity());
        }else {
            map.put("err","serial is close");
        }
        return map;
    }




}
