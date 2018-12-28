package cn.qqhxj.wsn.modules.sys.service.impl;

import cn.qqhxj.wsn.modules.sys.dao.SysMenuDao;
import cn.qqhxj.wsn.modules.sys.service.SysMenuService;
import cn.qqhxj.wsn.modules.sys.vo.SysMenu;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author han xinjian
 * @date 2018-12-26 23:51
 **/
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {

    @Override
    public List<SysMenu> tree(Integer id) {
        SysMenu sysMenu = this.getById(id);
        List<SysMenu> menus;
        if (sysMenu == null) {
            menus = this.list(new QueryWrapper<SysMenu>().eq("p_id",0));
        } else {
            menus = this.list(new QueryWrapper<SysMenu>().eq("p_id", sysMenu.getId()));
        }
        if (!menus.isEmpty()) {
            for (SysMenu menu : menus) {
                List<SysMenu> tree = tree(menu.getId());
                menu.setChildren(tree);
            }
        }
        return menus;
    }
}
