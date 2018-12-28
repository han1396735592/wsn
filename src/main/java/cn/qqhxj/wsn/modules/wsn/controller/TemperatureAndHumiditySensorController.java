package cn.qqhxj.wsn.modules.wsn.controller;

import cn.qqhxj.wsn.modules.wsn.service.TemperatureAndHumiditySensorService;
import cn.qqhxj.wsn.modules.wsn.vo.TemperatureAndHumiditySensorVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author han xinjian
 * @date 2018-12-24 15:35
 **/

@RestController
@Api("温湿度传感器")
@RequestMapping("temAndHum")
public class TemperatureAndHumiditySensorController
        extends BaseController<TemperatureAndHumiditySensorService, TemperatureAndHumiditySensorVo>{


}
