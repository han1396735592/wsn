package cn.qqhxj.wsn.common.web;

import cn.qqhxj.common.web.WebResult;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface IController<T> {

    /***
     *  保存数据
     * @param var1 数据
     * @return 处理结果
     */
    WebResult save(T var1);


    /**
     * 批量保存数据
     *
     * @param var1
     * @return
     */
    WebResult saveBatch(Collection<T> var1);


    /***
     *  根据删除数据
     * @param var1
     * @return
     */
    WebResult removeById(Serializable var1);


    /**
     * 根据统计删除数据
     *
     * @param var1
     * @return
     */
    WebResult remove(Wrapper<T> var1);

    /**
     * 批量删除数据
     *
     * @param var1
     * @return
     */
    WebResult removeByIds(Collection<? extends Serializable> var1);


    /**
     * 根据id跟新数据
     *
     * @param var1
     * @return
     */
    WebResult updateById(T var1);

    /**
     * 根据条件批量更新数据
     *
     * @param var1
     * @param var2
     * @return
     */
    WebResult update(T var1, Wrapper<T> var2);


    /**
     * 批量更新数据
     *
     * @param var1
     * @return
     */
    WebResult updateBatchById(Collection<T> var1);


    /**
     * 根据id获取数据
     *
     * @param var1
     * @return
     */
    WebResult<T> getById(Serializable var1);

    /**
     *  根据id 批量获取数据
     * @param var1
     * @return
     */
    WebResult<Collection<T>> listByIds(Collection<? extends Serializable> var1);


    /**
     * 获取数据的长度
     *
     * @param var1
     * @return
     */
    WebResult<Integer> count(Wrapper<T> var1);


    /**
     * 获取数据列表
     *
     * @param var1
     * @return
     */
    WebResult<List<T>> list(Wrapper<T> var1);


    /**
     * 获取分页数据
     *
     * @param var1 分页
     * @param var2 查询
     * @return
     */
    WebResult<IPage<T>> page(Page<T> var1, QueryWrapper<T> var2);

}
