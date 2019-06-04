package com.xd.modules.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.modules.sys.entity.MyComment;
import com.xd.modules.sys.entity.Problem;
import com.xd.modules.sys.service.ICheckOpinionService;
import com.xd.modules.sys.service.IProblemService;
import com.xd.modules.sys.vo.EquipCheckVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import com.xd.modules.sys.service.IEquipCheckService;
import com.xd.modules.sys.entity.EquipCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *   @description : EquipCheck 控制器
 *   ---------------------------------
 *   @author zxd
 *   @since 2019-05-30
 */
@Api(tags = {"设备开箱检查控制器"})
@RestController
@RequestMapping("/sys/equip-check")
public class EquipCheckController {
private final Logger logger = LoggerFactory.getLogger(EquipCheckController.class);

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;

    @Autowired
    IEquipCheckService equipCheckService;

    @Autowired
    IProblemService problemService;

    @Autowired
    ICheckOpinionService checkOpinionService;


    @PostMapping("/start")
    @ApiOperation(value = "开启流程并填写基本信息")
    @Transactional
    public String start(@RequestBody EquipCheckVO equipCheckVO){
        String key = "设备开箱检查";
        Map m = new HashMap();
        m.put("fv", equipCheckVO.getFlowVariate());
        //开启流程并加入流程变量
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(key,m);
        String processId = pi.getProcessInstanceId();//流程实例id
        EquipCheck equipCheck = equipCheckVO.getEquipCheck();
        equipCheck.setProcessId(processId);
        equipCheckService.save(equipCheck);//保存基本信息
        return processId;
    }

    @PostMapping("/finish")
    @Transactional
    public String finishTask(@RequestBody EquipCheckVO equipCheckVO){
        String id = equipCheckVO.getTaskId();
        Task task = taskService.createTaskQuery().taskId(id).singleResult();
        if (task == null){
            return "没有相应任务";
        }
        String processId = task.getProcessInstanceId();

        //如果存在审批意见就保存到数据库当中
        List<MyComment> comments = equipCheckVO.getMyComments();
        if (comments!=null && comments.size()>0){
            MyComment comment = comments.get(0);
            Authentication.setAuthenticatedUserId(comment.getUserId());
            taskService.addComment(id, processId,comment.getMessage());
        }
        //更新基本信息
        equipCheckService.updateById(equipCheckVO.getEquipCheck());

        taskService.setVariable(id, "fv", equipCheckVO.getFlowVariate());
        taskService.complete(id);
        //如果提出了问题就保存到数据库当中
        List<Problem> problems = equipCheckVO.getProblems();
        if (problems!=null && problems.size()>0){
            Problem problem = problems.get(0);
            problem.setProcessId(processId);
            problemService.save(problem);
        }
//        List<CheckOpinion> checkOpinions = equipCheckVO.getCheckOpinions();
//        if (checkOpinions!=null && checkOpinions.size()>0){
//            checkOpinionService.save(checkOpinions.get(0));
//            taskService.addComment()
//        }

        return "success";
    }

    @GetMapping("/selectWithProAndCom")
    @ApiOperation(value = "获取对应流程实例的问题和意见以及基本信息")
    public List<EquipCheckVO> getWithProAndOpi(String processId){
        return equipCheckService.selectWithProAndCom(processId);
    }

    @GetMapping("/all")
    @ApiOperation(value = "获取所有设备开箱检查")
    public List<EquipCheck> all(){
        return equipCheckService.list();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取设备开箱检查")
    public EquipCheck getById(@PathVariable Integer id){
        return equipCheckService.getById(id);
    }

    @PostMapping
    @ApiOperation(value = "新增设备开箱检查")
    public Object add(@RequestBody EquipCheck o){
        boolean flag = equipCheckService.save(o);
        String result = "";
        if(flag){
            result = "{\"code\":200,\"message\":\"处理成功\"}";
        }else{
            result = "{\"code\":500,\"message\":\"处理失败\"}";
        }
        return JSONObject.parseObject(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据id删除设备开箱检查")
    public Object delete(@PathVariable Integer id){
        boolean flag = equipCheckService.removeById(id);
        String result = "";
        if(flag){
        result = "{\"code\":200,\"message\":\"处理成功\"}";
        }else{
        result = "{\"code\":500,\"message\":\"处理失败\"}";
        }
        return JSONObject.parseObject(result);
    }

    @DeleteMapping("/ids")
    @ApiOperation(value = "批量删除设备开箱检查")
    public Object deleteIds(@RequestBody List<Integer> ids){
        boolean flag = equipCheckService.removeByIds(ids);
        String result = "";
        if(flag){
        result = "{\"code\":200,\"message\":\"处理成功\"}";
        }else{
        result = "{\"code\":500,\"message\":\"处理失败\"}";
        }
        return JSONObject.parseObject(result);
    }

    @PutMapping
    @ApiOperation(value = "更新设备开箱检查")
    public Object update(@RequestBody EquipCheck o){
        boolean flag = equipCheckService.updateById(o);
        String result = "";
        if(flag){
        result = "{\"code\":200,\"message\":\"处理成功\"}";
        }else{
        result = "{\"code\":500,\"message\":\"处理失败\"}";
        }
        return JSONObject.parseObject(result);
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页得到设备开箱检查")
    public IPage<EquipCheck> page(String key, @RequestParam Integer current, @RequestParam Integer size){
        Page<EquipCheck> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        QueryWrapper<EquipCheck> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", key);
        IPage<EquipCheck> result;
        if(key!=null && key!=""){
            result = equipCheckService.page(page, queryWrapper);
        }else{
            result = equipCheckService.page(page, null);
        }
        return result;
    }
}




