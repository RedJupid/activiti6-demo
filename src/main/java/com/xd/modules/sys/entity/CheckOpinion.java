package com.xd.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 审核意见
 * </p>
 *
 * @author zxd
 * @since 2019-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_check_opinion")
@ApiModel(value="CheckOpinion对象", description="审核意见")
public class CheckOpinion implements Serializable {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "审核意见ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "审核人")
    private String checker;

    @ApiModelProperty(value = "审核时间")
    @TableField("checkTime")
    private Date checkTime;

    @ApiModelProperty(value = "是否通过")
    private Integer pass;

    @ApiModelProperty(value = "审核意见")
    private String opinion;

    @ApiModelProperty(value = "流程实例id")
    private String processId;


}
