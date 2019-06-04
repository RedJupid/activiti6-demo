package com.xd.modules.sys.service;

import com.xd.modules.sys.entity.EquipCheck;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xd.modules.sys.vo.EquipCheckVO;

import java.util.List;

/**
 * <p>
 * 设备开箱检查 服务类
 * </p>
 *
 * @author zxd
 * @since 2019-05-30
 */
public interface IEquipCheckService extends IService<EquipCheck> {

    List<EquipCheckVO> selectWithProAndCom(String processId);

}
