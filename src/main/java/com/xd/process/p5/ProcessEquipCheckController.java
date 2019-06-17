//package com.xd.process.p5;
//
//
//import com.xd.modules.sys.entity.CheckOpinion;
//import com.xd.modules.sys.entity.Problem;
//import com.xd.modules.sys.service.impl.CheckOpinionServiceImpl;
//import com.xd.modules.sys.service.impl.ProblemServiceImpl;
//import io.swagger.annotations.ApiOperation;
//import org.activiti.engine.HistoryService;
//import org.activiti.engine.RepositoryService;
//import org.activiti.engine.RuntimeService;
//import org.activiti.engine.TaskService;
//import org.activiti.engine.runtime.ProcessInstance;
//import org.activiti.engine.task.Task;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/equipCheck")
//public class ProcessEquipCheckController {
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
//    @Autowired
//    HistoryService historyService;
//
//    @Autowired
//    ProblemServiceImpl problemService;
//
//    @Autowired
//    CheckOpinionServiceImpl checkOpinionService;
//
//    @PostMapping("/start")
//    @ApiOperation(value = "开启流程并填写基本信息")
//    public String start(@RequestBody EquipCheckWrap equipCheckWrap){
//        EquipCheckEntity equipCheckEntity = equipCheckWrap.getEquipCheckEntity();
//        CheckOpinion opinion = equipCheckWrap.getCheckOpinion();
//        Problem problem = equipCheckWrap.getProblem();
//        String key = "设备开箱检查3";
//        ProcessInstance pi = runtimeService.startProcessInstanceByKey(key);
//        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
//        Map m = new HashMap();
//        m.put("equipCheckEntity", equipCheckEntity);
//        taskService.complete(task.getId(), m);
//        String instanceId = pi.getProcessInstanceId();
//        if (opinion!=null){
//            opinion.setProcessId(instanceId);
//            checkOpinionService.save(opinion);
//        }
//        if (problem!=null){
//            problem.setProcessId(instanceId);
//            problemService.save(problem);
//        }
//        return pi.getProcessInstanceId();
//    }
//
//
//    @GetMapping("/finish")
//    public String finishTask(String id, EquipCheckEntity equipCheckEntity, CheckOpinion opinion, Problem problem){
//        Task task = taskService.createTaskQuery().taskId(id).singleResult();
//        if (task == null){
//            return "没有相应任务";
//        }
//        String instanceId = task.getProcessInstanceId();
//        taskService.setVariable(id, "equipCheckEntity", equipCheckEntity);
//        taskService.complete(id);
//        if (opinion!=null){
//            opinion.setProcessId(instanceId);
//            checkOpinionService.save(opinion);
//        }
//        if (problem!=null){
//            problem.setProcessId(instanceId);
//            problemService.save(problem);
//        }
//        return "success";
//    }
//
//
//
//
//}
