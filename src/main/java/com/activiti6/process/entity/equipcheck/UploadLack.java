package com.activiti6.process.entity.equipcheck;

import lombok.Data;

import java.io.Serializable;

/**
 * 上传缺货清单流程变量
 */
@Data
public class UploadLack implements Serializable {

    private static final long serialVersionUID = -8964624557212715720L;

    private String addForm; //补货清单

    private Integer isOK; //是否有问题
}
