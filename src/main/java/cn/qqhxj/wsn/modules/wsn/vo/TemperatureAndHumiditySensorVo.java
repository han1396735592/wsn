package cn.qqhxj.wsn.modules.wsn.vo;

import cn.qqhxj.common.wsn.sensor.TemperatureAndHumiditySensor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author han xinjian
 * @date 2018-12-24 9:26
 **/
@Data
@NoArgsConstructor
@TableName("TemperatureAndHumiditySensorVo")
public class TemperatureAndHumiditySensorVo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Date generateTime;
    protected Float temperatureValue;
    protected Float humidityValue;
    private Integer sensorVoId;

    public TemperatureAndHumiditySensorVo(TemperatureAndHumiditySensor temperatureAndHumiditySensor){
        BeanUtils.copyProperties(temperatureAndHumiditySensor,this);
    }
}
