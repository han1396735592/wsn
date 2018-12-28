package cn.qqhxj.wsn.config;

import cn.qqhxj.common.rxtx.parse.SerialDataParser;
import cn.qqhxj.common.rxtx.reader.LiveControlSerialReader;
import cn.qqhxj.common.rxtx.reader.SerialReader;
import cn.qqhxj.common.wsn.SensorDataInfo;
import cn.qqhxj.common.wsn.parse.SensorDataParseImpl;
import cn.qqhxj.wsn.modules.wsn.processor.ConsoleByteDataProcesser;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import gnu.io.SerialPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.Properties;

/**
 * @author han xinjian
 * @date 2018-11-13 16:56
 **/
@Slf4j
@Configuration
@EnableWebSocket
public class WsnConfig {
    //    private CorsConfiguration buildConfig() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedOrigin("*"); // 允许任何域名使用
//        corsConfiguration.addAllowedHeader("*"); // 允许任何头
//        corsConfiguration.addAllowedMethod("*"); // 允许任何方法（post、get等）
//        return corsConfiguration;
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", buildConfig()); // 对接口配置跨域设置
//        return new CorsFilter(source);
//    }
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }


    @Bean
    public SerialReader serialReader() {
        return new LiveControlSerialReader(
                SensorDataInfo.FLAG_INDEX,
                SensorDataInfo.DADA_LENGTH_INDEX,
                SensorDataInfo.header);
    }


    @Bean
    ConsoleByteDataProcesser swnSerialByteDataProcesser() {
        return new ConsoleByteDataProcesser();
    }

    @Bean
    SerialDataParser serialDataParser() {
        return new SensorDataParseImpl();
    }

    //    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        //格式化sql语句
        Properties properties = new Properties();
        properties.setProperty("format", "true");
        performanceInterceptor.setProperties(properties);
        return performanceInterceptor;
    }

    //    @Bean
//    @Primary
    public SerialPort serialPort() {
        return null;
    }

}