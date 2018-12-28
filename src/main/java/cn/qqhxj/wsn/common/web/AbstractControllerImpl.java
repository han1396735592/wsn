package cn.qqhxj.wsn.common.web;

import cn.qqhxj.common.web.WebResult;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author han xinjian
 * @date 2018-11-30 21:59
 **/
public abstract class AbstractControllerImpl<S extends IService<T>,T> implements IController<T> {

    @Autowired
    protected IService<T> service;

    @Override
    public WebResult save(T var1) {
        return WebResult.okOrErr(service.save(var1));
    }

    @Override
    public WebResult saveBatch(Collection<T> var1) {
        return WebResult.okOrErr(service.saveBatch(var1));
    }

    @Override
    public WebResult removeById(Serializable var1) {
        return WebResult.okOrErr(service.removeById(var1));
    }

    @Override
    public WebResult remove(Wrapper<T> var1) {
        return WebResult.okOrErr(service.remove(var1));
    }

    @Override
    public WebResult removeByIds(Collection<? extends Serializable> var1) {
        return WebResult.okOrErr(service.removeByIds(var1));
    }

    @Override
    public WebResult updateById(T var1) {
        return WebResult.okOrErr(service.updateById(var1));
    }

    @Override
    public WebResult update(T var1, Wrapper<T> var2) {
        return WebResult.okOrErr(service.update(var1,var2));
    }

    @Override
    public WebResult updateBatchById(Collection<T> var1) {
        return WebResult.okOrErr(service.updateBatchById(var1));
    }

    @Override
    public WebResult<T> getById(Serializable var1) {
        return WebResult.ok(((T) service.getById(var1)));
    }

    @Override
    public WebResult<Collection<T>> listByIds(Collection<? extends Serializable> var1) {
        return WebResult.ok(service.listByIds(var1));
    }


    @Override
    public WebResult<Integer> count(Wrapper<T> var1) {
        return WebResult.ok(service.count(var1));
    }

    @Override
    public WebResult<List<T>> list(Wrapper<T> var1) {
        return WebResult.ok(service.list(var1));
    }

    @Override
    public WebResult<IPage<T>> page(Page<T> var1, QueryWrapper<T> var2) {
        return WebResult.ok(service.page(var1,var2));
    }


}
