package com.xd.modules.sys.vo;

import com.xd.modules.sys.entity.*;
import lombok.Data;

import java.util.List;

@Data
public class EquipCheckVO {

    private String taskId;

    private EquipCheck equipCheck;

    private List<Problem> problems;

//    private List<CheckOpinion> checkOpinions;

    private List<MyComment> myComments;

    private FlowVariate flowVariate;

}
