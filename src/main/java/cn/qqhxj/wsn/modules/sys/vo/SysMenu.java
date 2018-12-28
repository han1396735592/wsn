package cn.qqhxj.wsn.modules.sys.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author han xinjian
 * @date 2018-12-26 23:48
 **/
@TableName("sysmenu")
@Data
public class SysMenu  implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String url;

    private Integer pId;
    @TableField(exist = false)
    private List<SysMenu> children;


}
