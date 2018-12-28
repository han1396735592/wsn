package cn.qqhxj.wsn.common.web;

import cn.qqhxj.common.web.WebResult;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;

/**
 * @author han xinjian
 * @date 2018-11-30 22:11
 **/
public abstract class BaseController<S extends IService<T>,T> extends AbstractControllerImpl<S, T> {

    @ApiOperation("查询")
    @GetMapping("get}")
    @Override
    public WebResult<T> getById(@RequestParam Serializable var1) {
        return super.getById(var1);
    }


    @ApiOperation("添加")
    @PostMapping("add")
    @Override
    public WebResult save(@RequestBody T var1) {
        return super.save(var1);
    }

    @ApiOperation("更新")
    @PostMapping("update")
    @Override
    public WebResult updateById(@RequestBody T var1) {
        return super.updateById(var1);
    }

    @ApiOperation("删除")
    @GetMapping("del")
    @Override
    public WebResult removeById(@RequestParam("id") Serializable var1) {
        return super.removeById(var1);
    }

    @ApiOperation("列表")
    @GetMapping("list")
    @Override
    public WebResult<List<T>> list(Wrapper<T> var1) {
        return super.list(var1);
    }


    @GetMapping("page")
    @ApiOperation("分页")
    @Override
    public WebResult<IPage<T>> page(Page<T> var1, QueryWrapper<T> var2) {
        return super.page(var1, var2);
    }
}
