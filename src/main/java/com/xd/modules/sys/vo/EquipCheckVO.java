package com.xd.modules.sys.vo;

import com.xd.modules.sys.entity.CheckOpinion;
import com.xd.modules.sys.entity.EquipCheck;
import com.xd.modules.sys.entity.FlowVariate;
import com.xd.modules.sys.entity.Problem;
import lombok.Data;

import java.util.List;

@Data
public class EquipCheckVO {

//    private Long id;
//
//    private String equipName;
//
//    private String manufacturer;
//
//    private String informBill;
//
//    private String applyBill;
//
//    private String recordBill;
//
//    private String lackBill;
//
//    private String addBill;
//
//    private String processId;

    private String taskId;

    private EquipCheck equipCheck;

    private List<Problem> problems;

    private List<CheckOpinion> checkOpinions;

    private FlowVariate flowVariate;

}
