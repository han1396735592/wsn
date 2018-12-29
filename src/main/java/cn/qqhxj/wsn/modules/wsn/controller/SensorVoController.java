package cn.qqhxj.wsn.modules.wsn.controller;

import cn.qqhxj.common.web.WebResult;
import cn.qqhxj.wsn.modules.wsn.service.SensorVoService;
import cn.qqhxj.wsn.modules.wsn.vo.SensorVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author han xinjian
 * @date 2018-12-29 14:24
 **/
@Api("传感器控制器")
@RequestMapping("wsn/sensorVo")
@RestController
public class SensorVoController {

    @Autowired
    private SensorVoService sensorVoService;

    @ApiOperation("获取所有的传感信息")
    @GetMapping("getAll")
    public WebResult<List<SensorVo>> getAll(){
        return WebResult.ok(sensorVoService.list());
    }

}
