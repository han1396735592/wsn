package cn.qqhxj.websockerdome.wsn.parse;

import cn.qqhxj.websockerdome.wsn.sensor.Sensor;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @author han xinjian
 * @date 2018-12-10 21:28
 **/
public class SensorDataParseImplTest {

    @Test
    public void paser() {

        byte[] bytes = new byte[]{-3, 14, 0, 0, 60, -5, 69, 31, 40, 15, 45, -56, 109, 16, -47, 3, 0, 75, 18, 0};


        SensorDataParseImpl parse = new SensorDataParseImpl();
        Sensor sensor = parse.parse(bytes);
        System.out.println(JSONObject.toJSONString(sensor));


    }
}