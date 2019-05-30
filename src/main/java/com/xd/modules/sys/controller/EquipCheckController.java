package com.xd.modules.sys.controller;

import com.xd.modules.BaseController;
import com.xd.modules.sys.entity.EquipCheck;
import com.xd.modules.sys.service.EquipCheckService;
import com.xd.modules.sys.vo.EquipCheckVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys/equipCheck")
public class EquipCheckController extends BaseController<EquipCheckService,EquipCheck> {

    @Autowired
    EquipCheckService equipCheckService;

    @GetMapping("/list")
    public List<EquipCheck> list(){
        return equipCheckService.list();
    }

    @GetMapping("/total")
    public List<EquipCheckVO> total(String processId){
        return equipCheckService.listTotal(processId);
    }
}
