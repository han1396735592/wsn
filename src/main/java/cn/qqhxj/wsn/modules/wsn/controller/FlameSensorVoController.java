package cn.qqhxj.wsn.modules.wsn.controller;

import cn.qqhxj.wsn.modules.wsn.service.FlameSensorVoService;
import cn.qqhxj.wsn.modules.wsn.vo.FlameSensorVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author han xinjian
 * @date 2018-12-24 15:35
 **/

@RestController
@Api("火焰传感器")
@RequestMapping("FlameSensorVoController")
public class FlameSensorVoController
        extends BaseController<FlameSensorVoService, FlameSensorVo>{


}
