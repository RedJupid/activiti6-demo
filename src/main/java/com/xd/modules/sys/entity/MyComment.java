package com.xd.modules.sys.entity;

import lombok.Data;

import java.util.Date;

/**
 * 包装act_hi_comment用于前端数据返回
 */
@Data
public class MyComment {

    private String id;

    private String userId;//用户名，对应的是act_hi_comment表的USER_ID_字段

    private Date date;

    private String taskId;//任务id

    private String processInstanceId;//流程定义id

    private String message;//审批信息

    private String fullMessage;
}
