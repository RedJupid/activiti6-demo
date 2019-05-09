package com.activiti6.controller.test;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.form.FormPropertyImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    FormService formService;


    @GetMapping("/form")
    public Object form(String id){
        StartFormData startFormData = formService.getStartFormData(id);
        List<FormProperty> formProperties = startFormData.getFormProperties();
        ProcessDefinition pd = startFormData.getProcessDefinition();
        for (FormProperty f : formProperties){
            System.out.println("id:"+f.getId());
            System.out.println("name:"+f.getName());
        }
        return formProperties;
    }

    @PostMapping("/apply")
    public String apply(@RequestBody List<MyFormProperty> list){
        String key = "请假流程";
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(key);
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        taskService.setAssignee(task.getId(), "lisi");

        Map m = new HashMap();
        m.put("list", list);
        taskService.complete(task.getId(), m);
        return "success";
    }



}
