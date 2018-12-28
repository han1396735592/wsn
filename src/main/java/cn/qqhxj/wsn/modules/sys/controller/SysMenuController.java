package cn.qqhxj.wsn.modules.sys.controller;

import cn.qqhxj.wsn.modules.sys.service.SysMenuService;
import cn.qqhxj.wsn.modules.sys.vo.SysMenu;
import cn.qqhxj.wsn.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author han xinjian
 * @date 2018-12-26 23:52
 **/
@Api("系统菜单")
@RestController
@Slf4j
@RequestMapping("/sys/sys-menu")
public class SysMenuController extends BaseController<SysMenuService,SysMenu> {

    @Autowired
    private SysMenuService sysMenuService;


    @ApiOperation("获取菜单树")
    @GetMapping("tree")
    public List<SysMenu> tree(@RequestParam(required = false,defaultValue = "") Integer id){
        return sysMenuService.tree(id);
    }

}