package cn.qqhxj.wsn.modules.sys.service;

import cn.qqhxj.wsn.modules.sys.vo.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author han xinjian
 * @date 2018-12-26 23:51
 **/
public interface SysMenuService extends IService<SysMenu> {
    /**
     *  获取系统菜单树
     * @param id id
     * @return  列表树
     */
    List<SysMenu> tree(Integer id);
}
