package com.xd.modules;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<M extends BaseMapper<T>,T> {

    @Autowired
    M baseMapper;

    public int deleteByPrimaryKey(Long id){
        return baseMapper.deleteByPrimaryKey(id);
    }

    public int insert(T record){
        return baseMapper.insert(record);
    }

    public int insertSelective(T record){
        return baseMapper.insertSelective(record);
    }

    public T selectByPrimaryKey(Long id){
        return baseMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(T record){
        return baseMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(T record){
        return baseMapper.updateByPrimaryKey(record);
    }

}
