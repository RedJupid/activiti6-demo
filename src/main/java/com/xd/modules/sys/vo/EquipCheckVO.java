package com.xd.modules.sys.vo;

import com.xd.modules.sys.entity.CheckOpinion;
import com.xd.modules.sys.entity.Problem;
import lombok.Data;

import java.util.List;

@Data
public class EquipCheckVO {

    private Long id;

    private String equipname;

    private String manufacturer;

    private String informbill;

    private String applybill;

    private String recordbill;

    private String lackbill;

    private String addbill;

    private String processId;

    private List<CheckOpinion> checkOpinions;

    private List<Problem> problems;
}
