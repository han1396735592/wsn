package cn.qqhxj.wsn.modules.wsn.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author han xinjian
 * @date 2018-12-29 14:15
 **/
@TableName("SensorVo")
@Data
public class SensorVo {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String sensorType;

    private String ieeeAddress = "";

    private String parentAddress = "";

    private String address = "";

    private Data LastReceiveDataTime;

}
