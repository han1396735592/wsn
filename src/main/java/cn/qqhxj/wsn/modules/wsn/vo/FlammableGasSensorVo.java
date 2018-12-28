package cn.qqhxj.wsn.modules.wsn.vo;

import cn.qqhxj.common.wsn.sensor.FlammableGasSensor;
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
@TableName("FlammableGasSensorVo")
public class FlammableGasSensorVo extends FlammableGasSensor {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Date generateTime;


    public FlammableGasSensorVo(FlammableGasSensor flammableGasSensor) {
        BeanUtils.copyProperties(flammableGasSensor, this);
        this.generateTime = new Date();
    }


}
