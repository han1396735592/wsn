package cn.qqhxj.wsn.modules.wsn.controller;

import cn.qqhxj.common.web.WebResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @author han xinjian
 * @date 2018-12-24 16:26
 **/
public abstract class BaseController<S, T> {
    @Autowired
    protected IService<T> service;


    @ApiOperation("获取所有的数据")
    @GetMapping("list")
    public WebResult<List<T>> getList(@RequestParam(required = false, defaultValue = "0") Integer begin,
                                      @RequestParam(required = false, defaultValue = "id") String desc_columns,
                                      @RequestParam(required = false, defaultValue = "") String asc_columns,
                                      @RequestParam(required = false, defaultValue = "10") Integer len) {
        return WebResult.ok(service.list(new QueryWrapper<T>().last("limit " + begin + "," + len)
                .orderByDesc(!StringUtils.isEmpty(desc_columns), "id").orderByAsc(!StringUtils.isEmpty(asc_columns), asc_columns)));
    }

    @ApiOperation("获取最后采集的数据")
    @GetMapping("last")
    public WebResult<T> last() {
        return WebResult.ok(service.getOne(new QueryWrapper<T>().orderByDesc("id").last("limit 1")));
    }


    @GetMapping("getById")
    @ApiOperation("根据id获取")
    public WebResult<T> getById(@RequestParam Integer id) {
        return WebResult.ok(service.getById(id));
    }

    @GetMapping("getByTimeLimit")
    @ApiOperation("根据时间段获取数据")
    public WebResult<List<T>> getById(@RequestParam(required = false) String start,
                                      @RequestParam(required = false) String end,
                                      @RequestParam(required = false,defaultValue = "10") String len) {

        if (StringUtils.isEmpty(end)) {
            end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        }
        List<T> list = service.list(new QueryWrapper<T>().le(!StringUtils.isEmpty(end), "generate_time", end).ge(!StringUtils.isEmpty(start), "generate_time", start).last("limit "+len));
        return WebResult.ok(list);
    }

}
