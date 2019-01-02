package cn.qqhxj.wsn.modules.wsn.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author han xinjian
 * @date 2018-12-25 12:47
 **/
@Data
@NoArgsConstructor
@TableName("AirPressureAltitudeSensorVo")
public class AirPressureAltitudeSensorVo {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Date generateTime;

    private Integer sensorVoId;

    protected Float temperatureValue;

    protected Float airPressureValue;



}
