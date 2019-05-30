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
 * 设备开箱检查
 * </p>
 *
 * @author zxd
 * @since 2019-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_equip_check")
@ApiModel(value="EquipCheck对象", description="设备开箱检查")
public class EquipCheck implements Serializable {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "设备开箱检查ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "设备名称")
    @TableField("equipName")
    private String equipName;

    @ApiModelProperty(value = "生产厂家")
    private String manufacturer;

    @ApiModelProperty(value = "开箱验收通知单")
    @TableField("informBill")
    private String informBill;

    @ApiModelProperty(value = "开箱验收申请单")
    @TableField("applyBill")
    private String applyBill;

    @ApiModelProperty(value = "接受记录单")
    @TableField("recordBill")
    private String recordBill;

    @ApiModelProperty(value = "缺货清单")
    @TableField("lackBill")
    private String lackBill;

    @ApiModelProperty(value = "补货清单")
    @TableField("addBill")
    private String addBill;

    @ApiModelProperty(value = "流程实例id")
    private String processId;


}
