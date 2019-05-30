package com.xd.process.p5;

import lombok.Data;

import java.io.Serializable;

@Data
public class EquipCheckEntity implements Serializable {

    private static final long serialVersionUID = -3639321903221583550L;
    private String taskTitle; //任务标题

    private String equipName; //设备名称

    private String manufacturer; //生产厂家

    private String informBill; //开箱验收通知单

    private String applyBill; //开箱验收申请单

    private String recordBill; //接受记录单

    private String lackBill; //缺货清单

    private String addBill; //补货清单

    private Integer pass; //是否通过

    private Integer sendManager; //是否送项目经理

    private Integer sendHandler; //是否送问题处理人

    private Integer sendOwner; //是否送业主

    private Integer sendFactory; //是否送厂家

}
