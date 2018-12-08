package cn.qqhxj.websockerdome;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class WebSockerDomeApplicationTests {

    @Test
    public void contextLoads() {


        String str = "{asfsafafsdaf}";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(str.getBytes());

        int read = inputStream.read();
        System.out.println((char) read);


    }

}
