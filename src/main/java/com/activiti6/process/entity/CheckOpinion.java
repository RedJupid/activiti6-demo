package com.activiti6.process.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 审核意见类
 */
@Data
public class CheckOpinion implements Serializable {
    private static final long serialVersionUID = -591307513036354530L;
    private String checker; //审核人

    private Date checkTime; //审核日期

    private Integer pass; //是否通过，1表示通过，0表示不通过

    private String opinion; //审核意见
}
