//package com.activiti6.process;
//
//
//import com.activiti6.util.common.BeanMapUtil;
//import org.activiti.engine.RepositoryService;
//import org.activiti.engine.RuntimeService;
//import org.activiti.engine.TaskService;
//import org.activiti.engine.runtime.ProcessInstance;
//import org.activiti.engine.task.Task;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cglib.beans.BeanMap;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/equipCheck")
//public class EquipCheckController {
//
//    @Autowired
//    RepositoryService repositoryService;
//
//    @Autowired
//    RuntimeService runtimeService;
//
//    @Autowired
//    TaskService taskService;
//
//    @PostMapping("/apply")
//    public String apply(){
//        String key = "设备开箱检查";
//        ProcessInstance pi = runtimeService.startProcessInstanceByKey(key);
//        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
//        Map m = new HashMap();
//        m.put("pass", 0);
//        taskService.complete(task.getId(), m);
//        return "success";
//    }
//
//    /** 查询当前人的个人任务*/
//    @GetMapping("/task")
//    public List<Object> getTask(@RequestParam String applicant){
//        List<Task> list = taskService.createTaskQuery().taskAssignee(applicant).list();
//        List<Object> l = new ArrayList<>();
//        for (Task t: list){
//            Map<String, Object> m = new HashMap<>();
//            m.put("id", t.getId());
//            m.put("name",t.getName());
//            l.add(m);
//        }
//        return l;
//    }
//
//    @GetMapping("/finish")
//    public String finish(String taskId){
//        Map m = new HashMap();
//        m.put("pass", 1);
//        taskService.complete(taskId,m);
//        return "处理完成";
//    }
//
//    @GetMapping("/finish2")
//    public String finish2(String taskId){
//        Map m = new HashMap();
//        m.put("pass", 0);
//        taskService.complete(taskId,m);
//        return "处理完成";
//    }
//}
