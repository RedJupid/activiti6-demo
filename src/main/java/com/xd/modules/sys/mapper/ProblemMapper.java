package com.xd.modules.sys.mapper;

import com.xd.modules.BaseMapper;
import com.xd.modules.sys.entity.Problem;

public interface ProblemMapper extends BaseMapper<Problem> {
    int deleteByPrimaryKey(Long id);

    int insert(Problem record);

    int insertSelective(Problem record);

    Problem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Problem record);

    int updateByPrimaryKey(Problem record);
}