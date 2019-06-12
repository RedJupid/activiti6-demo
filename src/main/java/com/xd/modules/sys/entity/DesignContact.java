package com.xd.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 设计联络
 * </p>
 *
 * @author zxd
 * @since 2019-06-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_design_contact")
@ApiModel(value="DesignContact对象", description="设计联络")
public class DesignContact implements Serializable {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "设计联络ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "任务标题")
    private String title;

    @ApiModelProperty(value = "设备名称")
    @TableField("equipName")
    private String equipName;

    @ApiModelProperty(value = "生产厂家")
    private String manufacturer;

    @ApiModelProperty(value = "社联通知单")
    @TableField("informBill")
    private String informBill;

    @ApiModelProperty(value = "社联相关材料")
    @TableField("dataBill")
    private String dataBill;

    @ApiModelProperty(value = "会议纪要")
    @TableField("meetBill")
    private String meetBill;

    @ApiModelProperty(value = "流程实例id")
    private String processId;


}
