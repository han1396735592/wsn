package cn.qqhxj.wsn.modules.wsn.vo;

import cn.qqhxj.common.wsn.sensor.FlameSensor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author han xinjian
 * @date 2018-12-25 15:40
 **/
@Data
@NoArgsConstructor
@TableName("FlameSensorVo")
public class FlameSensorVo extends FlameSensor {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Date generateTime;


    public FlameSensorVo(FlameSensor flameSensor) {
        BeanUtils.copyProperties(flameSensor, this);
        this.generateTime = new Date();
    }


}
