package com.xd.modules.sys.service;


import com.xd.modules.BaseService;
import com.xd.modules.sys.entity.EquipCheck;
import com.xd.modules.sys.mapper.EquipCheckMapper;
import com.xd.modules.sys.vo.EquipCheckVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipCheckService extends BaseService<EquipCheckMapper, EquipCheck> {

    @Autowired
    EquipCheckMapper equipCheckMapper;

    public List<EquipCheck> list(){
        return equipCheckMapper.selectAll();
    }

    public List<EquipCheckVO> listTotal(String processId){
        return equipCheckMapper.selectTotal(processId);
    }

}
