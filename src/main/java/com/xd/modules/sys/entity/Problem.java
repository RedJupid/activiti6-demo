package com.xd.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 问题
 * </p>
 *
 * @author zxd
 * @since 2019-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_problem")
@ApiModel(value="Problem对象", description="问题")
public class Problem implements Serializable {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "问题ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "问题标题")
    private String title;

    @ApiModelProperty(value = "问题类型")
    private String type;

    @ApiModelProperty(value = "问题严重程度")
    private String degree;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "预警时间")
    private Date warmTime;

    @ApiModelProperty(value = "处理状态")
    private String status;

    @ApiModelProperty(value = "处理方案文字")
    private String schemeText;

    @ApiModelProperty(value = "处理方案文件")
    private String schemeFile;

    @ApiModelProperty(value = "流程实例id")
    private String processId;


}
