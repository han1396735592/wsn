package cn.qqhxj.wsn.modules.wsn.service;

import cn.qqhxj.wsn.modules.wsn.vo.SensorVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author han xinjian
 * @date 2018-12-29 14:22
 **/
public interface SensorVoService extends IService<SensorVo> {
    /**
     *  根据ieeeaddress 获取数据
     * @param address
     * @return
     */
    SensorVo getByIeeeAddress(String address);
}
