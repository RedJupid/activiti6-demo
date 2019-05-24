package com.activiti6.process.entity.equipcheck;

import lombok.Data;

import java.io.Serializable;

/**
 * 到货开箱检查流程变量
 */
@Data
public class ArrivalCheck implements Serializable {

    private static final long serialVersionUID = 2235676991928649611L;

    private Integer sendManager; //是否交给经理

    private Integer sendHandler; //是否交给问题处理人

    private Integer isOK; //是否有问题

    private String managerRemark; //经理备注

    private String handlerRemark; //问题处理人备注
}
