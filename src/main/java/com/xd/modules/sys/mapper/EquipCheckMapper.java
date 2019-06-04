package com.xd.modules.sys.mapper;

import com.xd.modules.sys.entity.EquipCheck;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xd.modules.sys.vo.EquipCheckVO;

import java.util.List;

/**
 * <p>
 * 设备开箱检查 Mapper 接口
 * </p>
 *
 * @author zxd
 * @since 2019-05-30
 */
public interface EquipCheckMapper extends BaseMapper<EquipCheck> {

    List<EquipCheckVO> selectWithProAndCom(String processId);

}
