package com.activiti6.process.entity.equipcheck;

import lombok.Data;

import java.io.Serializable;

/**
 * 实施开箱流程变量
 *
 */
@Data
public class DoUnpack implements Serializable {

    private static final long serialVersionUID = -7621001100575761022L;

    private String unpackForm; //开箱单

    private String unpackRecord; //开箱记录

    private Integer sendFactory; //是否送厂家处理

    private Integer isOK; //是否有问题
}
