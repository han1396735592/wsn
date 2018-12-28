package cn.qqhxj.wsn;

import cn.qqhxj.common.rxtx.SerialContext;
import cn.qqhxj.common.web.WebResult;
import cn.qqhxj.common.wsn.sensor.Sensor;
import io.swagger.annotations.ApiParam;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequestMapping
@MapperScan("cn.qqhxj.wsn.modules.*.dao")
@SpringBootApplication
public class WsnApplication {

    public static Map<String, Sensor> MAC_CMD = new ConcurrentHashMap<>();

    @ResponseBody
    @GetMapping("cmdByMac")
    public WebResult cmdByMac(@RequestParam(defaultValue = "6D 10 D1 03 00 4B 12 00")
                              @ApiParam(value = "mac 地址", example = "6D 10 D1 03 00 4B 12 00", required = true)
                                      String mac, @RequestParam(defaultValue = "3D 01", required = true)
                              @ApiParam(value = "控制节点的短地址", example = "3D 01", required = true) String address,
                              @RequestParam( required = false, defaultValue = "3000")
                              @ApiParam(value = "超时时间", example = "3000", required = false)
                                      Long timeout) {
        StringBuffer stringBuffer = new StringBuffer("");
        String cmd = stringBuffer.append("FD 0F").append(address)
                .append("00 00 00 ").append(address)
                .append(" A1 01 01 07").append(mac).toString();
        return cmd(cmd, timeout);
    }


    @ResponseBody
    @GetMapping("cmd")
    public WebResult cmd(
            @RequestParam(required = true)
            @ApiParam(value = "16 进制数据命令", example = "FD 0F 3D 01 00 00 00 3D 01 A1 01 01 07 6D 10 D1 03 00 4B 12 00", required = true)
                    String cmd,
            @RequestParam( required = false, defaultValue = "3000")
            @ApiParam(value = "超时时间", example = "3000", required = false)
                    Long timeout) {

        cmd = cmd.replaceAll(" ", "");
        String mac = cmd.substring(cmd.length() - 16, cmd.length());
        System.out.println("mac = " + mac);
        MAC_CMD.remove(mac);
        System.out.println("发送了命令 : " + cmd);
        byte[] bytes = new byte[0];
        try {
            bytes = Hex.decodeHex(cmd.toString());
            SerialContext.sendData(bytes);
        } catch (DecoderException e) {
            e.printStackTrace();
        }

        long startTime = System.currentTimeMillis();
        Sensor sensor = null;
        while (sensor == null) {
            sensor = MAC_CMD.get(mac);
            long cu = System.currentTimeMillis();
            long l = cu - startTime - timeout;
            if (l > 0) {
                return WebResult.err("发送命令超时");
            }
        }
        System.out.println(sensor);
        MAC_CMD.remove(mac);
        return WebResult.ok(sensor, "数据采集成功");
    }

    @ResponseBody
    @GetMapping("cmdByMacAll")
    public WebResult cmdByMac(@RequestParam(defaultValue = "6D 10 D1 03 00 4B 12 00")
                              @ApiParam(value = "mac 地址", example = "6D 10 D1 03 00 4B 12 00", required = true)
                                      String mac,
                              @RequestParam(defaultValue = "FD", required = false)
                              @ApiParam(value = "标头", example = "FD", required = false)
                                      String header,
                              @RequestParam(defaultValue = "0F", required = false)
                              @ApiParam(value = "长度", example = "0F", required = false)
                                      String len,
                              @RequestParam(defaultValue = "3D 01",  required = true)
                              @ApiParam(value = "控制节点的短地址", example = "3D 01", required = true) String address,
                              @RequestParam(defaultValue = "00 00",  required = false)
                              @ApiParam(value = "主机节点地址", example = "00 00", required = false) String host_address,
                              @RequestParam(defaultValue = "07",  required = false)
                              @ApiParam(value = "校验和", example = "07", required = false) String verify,
                              @RequestParam(defaultValue = "00 3D 01 A1 01 01",  required = false)
                              @ApiParam(value = "数据", example = "00 3D 01 A1 01 01", required = false) String data,
                              @RequestParam(value = "超时时间", required = false, defaultValue = "3000")
                              @ApiParam(value = "超时时间", example = "3000", required = false)
                                      Long timeout
    ) {
        StringBuffer sb = new StringBuffer();
        sb.append(header.trim())
                .append(len.trim())
                .append(address.trim())
                .append(host_address.trim())
                .append(data.trim())
                .append(verify.trim())
                .append(mac.trim());
        return cmd(sb.toString(), timeout);
    }


    public static void main(String[] args) {
        SpringApplication.run(WsnApplication.class, args);
    }

}
