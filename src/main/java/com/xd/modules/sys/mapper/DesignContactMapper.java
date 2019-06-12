package com.xd.modules.sys.mapper;

import com.xd.modules.sys.entity.DesignContact;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xd.modules.sys.vo.DesignContactVO;
import com.xd.modules.sys.vo.EquipCheckVO;

import java.util.List;

/**
 * <p>
 * 设计联络 Mapper 接口
 * </p>
 *
 * @author zxd
 * @since 2019-06-12
 */
public interface DesignContactMapper extends BaseMapper<DesignContact> {

    List<DesignContactVO> selectWithProAndCom(String processId);
}
