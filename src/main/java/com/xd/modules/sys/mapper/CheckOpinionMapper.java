package com.xd.modules.sys.mapper;

import com.xd.modules.BaseMapper;
import com.xd.modules.sys.entity.CheckOpinion;

public interface CheckOpinionMapper extends BaseMapper<CheckOpinion> {
    int deleteByPrimaryKey(Long id);

    int insert(CheckOpinion record);

    int insertSelective(CheckOpinion record);

    CheckOpinion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CheckOpinion record);

    int updateByPrimaryKey(CheckOpinion record);
}