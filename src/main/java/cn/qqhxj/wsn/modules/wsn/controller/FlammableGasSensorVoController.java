package cn.qqhxj.wsn.modules.wsn.controller;

import cn.qqhxj.wsn.modules.wsn.service.FlammableGasSensorVoService;
import cn.qqhxj.wsn.modules.wsn.vo.FlammableGasSensorVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author han xinjian
 * @date 2018-12-24 15:35
 **/

@RestController
@Api("可燃气体传感器")
@RequestMapping("FlammableGasSensorVoController")
public class FlammableGasSensorVoController
        extends BaseController<FlammableGasSensorVoService, FlammableGasSensorVo>{


}
