package cn.qqhxj.wsn.modules.wsn.vo;

import cn.qqhxj.common.wsn.sensor.AirPressureAltitudeSensor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author han xinjian
 * @date 2018-12-25 12:47
 **/
@Data
@NoArgsConstructor
@TableName("AirPressureAltitudeSensorVo")
public class AirPressureAltitudeSensorVo extends AirPressureAltitudeSensor{

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Date generateTime;



    public AirPressureAltitudeSensorVo(byte[] bytes) {
        super(bytes);
        generateTime = new Date();
    }

    public AirPressureAltitudeSensorVo(AirPressureAltitudeSensor airPressureAltitudeSensor) {
        BeanUtils.copyProperties(airPressureAltitudeSensor, this);
        this.generateTime = new Date();
    }
}
