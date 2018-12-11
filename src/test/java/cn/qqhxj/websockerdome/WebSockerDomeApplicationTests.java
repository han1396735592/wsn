package cn.qqhxj.websockerdome;

import cn.qqhxj.websockerdome.wsn.processor.AA;
import cn.qqhxj.websockerdome.wsn.sensor.TemperatureAndHumiditySensor;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class WebSockerDomeApplicationTests {

    @Test
    public void contextLoads() {


        Type[] types = AA.class.getGenericInterfaces();
        //       parameterizedType.getActualTypeArguments();
        Type[] typeArguments = ((ParameterizedType) types[0]).getActualTypeArguments();
        try {
            System.out.println(Class.forName(typeArguments[0].getTypeName())== TemperatureAndHumiditySensor.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
