package com.xd.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class FlowVariate implements Serializable {


    private static final long serialVersionUID = -4631331388390043177L;
    //任务是否顺利完成
    private Integer isOK;

    //是否发送项目经理
    private Integer sendManager;

    //是否发送业主
    private Integer sendOwner;

    //是否发送问题处理人
    private Integer sendHandler;

    //是否发送厂家
    private Integer sendFactory;

    //确定指派人
    private String assignor;
}
