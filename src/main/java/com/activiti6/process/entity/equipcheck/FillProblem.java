package com.activiti6.process.entity.equipcheck;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * 填写问题流程变量
 */
@Data
public class FillProblem implements Serializable {

    private static final long serialVersionUID = 7328594139899279458L;

    private String lackForm; //缺货清单

    private String addForm; //补货清单

    private Integer sendManager; //是否交给经理

    private Integer sendHandler; //是否交给问题处理人

    private Integer isOK; //是否有问题

    private String managerRemark; //经理备注

    private String handlerRemark; //问题处理人备注
}
