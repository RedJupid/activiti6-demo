package com.xd.modules.sys.mapper;

import com.xd.modules.BaseMapper;
import com.xd.modules.sys.entity.EquipCheck;
import com.xd.modules.sys.vo.EquipCheckVO;

import java.util.List;

public interface EquipCheckMapper extends BaseMapper<EquipCheck> {
    int deleteByPrimaryKey(Long id);

    int insert(EquipCheck record);

    int insertSelective(EquipCheck record);

    EquipCheck selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EquipCheck record);

    int updateByPrimaryKey(EquipCheck record);

    List<EquipCheck> selectAll();

    List<EquipCheckVO> selectTotal(String processId);
}