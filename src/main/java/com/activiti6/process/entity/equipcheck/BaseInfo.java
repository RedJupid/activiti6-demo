package com.activiti6.process.entity.equipcheck;

import lombok.Data;

import java.io.Serializable;

/**
 * 填写基本信息流程变量
 */
@Data
public class BaseInfo implements Serializable {

    private static final long serialVersionUID = -4411987617348150183L;

    private String checkInfo; //开箱验收通知单

    private String checkApply; //开箱验收申请

    private String accRecord; //接受记录单

    private Integer sendOwner; //是否通知业主

    private Integer sendManager; //是否交给经理

    private Integer sendHandler; //是否交给问题处理人

    private Integer isOK; //是否有问题

    private String managerRemark; //经理备注

    private String handlerRemark; //问题处理人备注
}