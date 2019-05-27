package com.activiti6.process;


import io.swagger.annotations.ApiOperation;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;

    @ApiOperation(value = "查询某个角色的所有当前任务或某流程实例的当前任务")
    @GetMapping("/task")
    public List<Object> getTask(@RequestParam String applicant, String instanceId){

        List<Task> list = new ArrayList<>();
        if (instanceId !=null && instanceId!=""){
            list = taskService.createTaskQuery()
                    .taskAssignee(applicant)
                    .processInstanceId(instanceId)
                    .list();
        }else{
            list = taskService.createTaskQuery().taskAssignee(applicant).list();
        }
        List<Object> l = new ArrayList<>();
        for (Task t: list){
            Map<String, Object> m = new HashMap<>();
            m.put("id", t.getId());
            m.put("name",t.getName());
            m.put("instanceId",t.getProcessInstanceId());
            m.put("key",t.getTaskDefinitionKey());
            l.add(m);
        }
        return l;
    }

    @ApiOperation(value = "获取所有流程实例")
    @GetMapping("instance")
    public List<HistoricProcessInstance> getInstance(String name){
        return historyService.createHistoricProcessInstanceQuery().processDefinitionKey(name).list();
    }

    @GetMapping("/historyVariable")
    @ApiOperation(value = "根据流程实例id获取历史流程变量")
    public Map<String, Object> historyVariable(String instanceId){
        List<HistoricVariableInstance> list = historyService.createHistoricVariableInstanceQuery().processInstanceId(instanceId).list();
        Map<String, Object> m = new HashMap<>();
        for (HistoricVariableInstance h: list){
            m.put(h.getVariableName(),h.getValue());
        }
        return m;
    }



}
