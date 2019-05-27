package com.activiti6.process.controller;


import com.activiti6.process.entity.equipcheck.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/equipCheck2")
public class EquipCheck2Controller {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;


    @PostMapping("/start")
    @ApiOperation(value = "开启流程并填写基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "checkInfo", value = "开箱验收通知单"),
            @ApiImplicitParam(name = "checkApply", value = "开箱验收申请"),
            @ApiImplicitParam(name = "accRecord", value = "接受记录单"),
            @ApiImplicitParam(name = "sendManager", value = "是否交给经理"),
            @ApiImplicitParam(name = "sendHandler", value = "是否交给问题处理人"),
            @ApiImplicitParam(name = "sendOwner", value = "是否发给业主"),
            @ApiImplicitParam(name = "isOK", value = "是否进入下一环节"),
    })
    public String start(BaseInfo baseInfo){
        String key = "设备开箱检查2";
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(key);
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        Map m = new HashMap();
        m.put("baseInfo", baseInfo);
        taskService.complete(task.getId(), m);
        return "success";
    }

    @ApiOperation(value = "根据任务id查询流程变量")
    @GetMapping("/variable")
    public Map<String, Object> getVariable(String taskId){

        BaseInfo baseInfo = taskService.getVariable(taskId, "baseInfo", BaseInfo.class);
        DoUnpack doUnpack = taskService.getVariable(taskId, "doUnpack", DoUnpack.class);
        FillProblem fillProblem = taskService.getVariable(taskId, "fillProblem", FillProblem.class);
        UploadLack uploadLack = taskService.getVariable(taskId, "uploadLack", UploadLack.class);
        ArrivalCheck arrivalCheck = taskService.getVariable(taskId, "arrivalCheck", ArrivalCheck.class);
        Map<String, Object> m = new HashMap<>();
        m.put("baseInfo",baseInfo);
        m.put("doUnpack",doUnpack);
        m.put("fillProblem",fillProblem);
        m.put("uploadLack",uploadLack);
        m.put("arrivalCheck",arrivalCheck);
        return m;
    }

    @GetMapping("/finish")
    public String finishTask(String id, ArrivalCheck arrivalCheck,
                             BaseInfo baseInfo, DoUnpack doUnpack,
                             FillProblem fillProblem, UploadLack uploadLack){
        Task task = taskService.createTaskQuery().taskId(id).singleResult();
        if (task == null){
            return "没有相应任务";
        }
        String key = task.getTaskDefinitionKey();
        key = StringUtils.substringAfterLast(key, "-");
//        Map<String, Object> m = new HashMap<>();
        if (key.equals("baseInfo")){
            taskService.setVariable(id, "baseInfo", baseInfo);
        }else if (key.equals("doUnpack")){
            System.out.println("doUnpack");
            taskService.setVariable(id,"doUnpack", doUnpack);
        }else if (key.equals("fillProblem")){
            taskService.setVariable(id,"fillProblem", fillProblem);
        }else if (key.equals("uploadLack")){
            taskService.setVariable(id,"uploadLack", uploadLack);
        }else if (key.equals("arrivalCheck")){
            taskService.setVariable(id,"arrivalCheck", arrivalCheck);
        }
        taskService.complete(id);
        return "success";
    }






}
