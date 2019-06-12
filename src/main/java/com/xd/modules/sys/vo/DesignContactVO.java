package com.xd.modules.sys.vo;

import com.xd.modules.sys.entity.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author xd
 * @Description
 * @Date 2019/6/12 8:55
 */
@Data
public class DesignContactVO {

    @ApiModelProperty(value = "所要完成的任务id")
    private String taskId; //所要完成的任务id

    @ApiModelProperty(value = "设计联络信息")
    private DesignContact designContact;//设计联络信息

    @ApiModelProperty(value = "问题")
    private List<Problem> problems;//问题

    @ApiModelProperty(value = "审核意见")
    private List<MyComment> myComments;//审核意见

    @ApiModelProperty(value = "流程变量控制节点走向")
    private FlowVariate flowVariate;//流程变量控制节点走向

}
