package cn.qqhxj.wsn.modules.wsn.controller;

import cn.qqhxj.wsn.modules.wsn.service.impl.AirPressureAltitudeSensorServiceImpl;
import cn.qqhxj.wsn.modules.wsn.vo.AirPressureAltitudeSensorVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author han xinjian
 * @date 2018-12-25 12:52
 *
 **/
@RestController
@Api("气压海拔")
@RequestMapping("AirPressureAltitudeSensorController")
public class AirPressureAltitudeSensorController extends BaseController<AirPressureAltitudeSensorServiceImpl, AirPressureAltitudeSensorVo> {
}
