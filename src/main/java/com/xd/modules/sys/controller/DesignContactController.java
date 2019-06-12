package com.xd.modules.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.modules.sys.entity.EquipCheck;
import com.xd.modules.sys.entity.MyComment;
import com.xd.modules.sys.entity.Problem;
import com.xd.modules.sys.service.IEquipCheckService;
import com.xd.modules.sys.service.IProblemService;
import com.xd.modules.sys.vo.DesignContactVO;
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
import com.xd.modules.sys.service.IDesignContactService;
import com.xd.modules.sys.entity.DesignContact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *   @description : DesignContact 控制器
 *   ---------------------------------
 *   @author zxd
 *   @since 2019-06-12
 */
@Api(tags = {"1设计联络控制器"})
@RestController
@RequestMapping("/sys/design-contact")
public class DesignContactController {
private final Logger logger = LoggerFactory.getLogger(DesignContactController.class);

    @Autowired
    public IDesignContactService designContactService;

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;

    @Autowired
    IProblemService problemService;

    @PostMapping("/start")
    @ApiOperation(value = "开启流程并填写基本信息")
    @Transactional
    public String start(@RequestBody DesignContactVO designContactVO){
        String key = "设计联络";
        Map m = new HashMap();
        m.put("fv", designContactVO.getFlowVariate());
        //开启流程并加入流程变量
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(key,m);
        String processId = pi.getProcessInstanceId();//流程实例id
        DesignContact designContact = designContactVO.getDesignContact();
        designContact.setProcessId(processId);
        designContactService.save(designContact);//保存基本信息
        return processId;
    }

    @PostMapping("/finish")
    @Transactional
    public String finishTask(@RequestBody DesignContactVO designContactVO){
        String id = designContactVO.getTaskId();
        Task task = taskService.createTaskQuery().taskId(id).singleResult();
        if (task == null){
            return "没有相应任务";
        }
        String processId = task.getProcessInstanceId();

        //如果存在审批意见就保存到数据库当中
        List<MyComment> comments = designContactVO.getMyComments();
        if (comments!=null && comments.size()>0){
            MyComment comment = comments.get(0);
            Authentication.setAuthenticatedUserId(comment.getUserId());
            taskService.addComment(id, processId,comment.getMessage());
        }
        //更新基本信息
        designContactService.updateById(designContactVO.getDesignContact());

        taskService.setVariable(id, "fv", designContactVO.getFlowVariate());
        taskService.complete(id);
        //如果提出了问题就保存到数据库当中
        List<Problem> problems = designContactVO.getProblems();
        if (problems!=null && problems.size()>0){
            Problem problem = problems.get(0);
            problem.setProcessId(processId);
            problemService.save(problem);
        }
        return "success";
    }

    @GetMapping("/selectWithProAndCom")
    @ApiOperation(value = "获取对应流程实例的问题和意见以及基本信息")
    public List<DesignContactVO> getWithProAndOpi(String processId){
        return designContactService.selectWithProAndCom(processId);
    }

    @GetMapping("/all")
    @ApiOperation(value = "获取所有设计联络")
    public List<DesignContact> all(){
        return designContactService.list();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取设计联络")
    public DesignContact getById(@PathVariable Integer id){
        return designContactService.getById(id);
    }

    @PostMapping
    @ApiOperation(value = "新增设计联络")
    public Object add(@RequestBody DesignContact o){
        boolean flag = designContactService.save(o);
        String result = "";
        if(flag){
            result = "{\"code\":200,\"message\":\"处理成功\"}";
        }else{
            result = "{\"code\":500,\"message\":\"处理失败\"}";
        }
        return JSONObject.parseObject(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据id删除设计联络")
    public Object delete(@PathVariable Integer id){
        boolean flag = designContactService.removeById(id);
        String result = "";
        if(flag){
        result = "{\"code\":200,\"message\":\"处理成功\"}";
        }else{
        result = "{\"code\":500,\"message\":\"处理失败\"}";
        }
        return JSONObject.parseObject(result);
    }

    @DeleteMapping("/ids")
    @ApiOperation(value = "批量删除设计联络")
    public Object deleteIds(@RequestBody List<Integer> ids){
        boolean flag = designContactService.removeByIds(ids);
        String result = "";
        if(flag){
        result = "{\"code\":200,\"message\":\"处理成功\"}";
        }else{
        result = "{\"code\":500,\"message\":\"处理失败\"}";
        }
        return JSONObject.parseObject(result);
    }

    @PutMapping
    @ApiOperation(value = "更新设计联络")
    public Object update(@RequestBody DesignContact o){
        boolean flag = designContactService.updateById(o);
        String result = "";
        if(flag){
        result = "{\"code\":200,\"message\":\"处理成功\"}";
        }else{
        result = "{\"code\":500,\"message\":\"处理失败\"}";
        }
        return JSONObject.parseObject(result);
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页得到设计联络")
    public IPage<DesignContact> page(String key, @RequestParam Integer current, @RequestParam Integer size){
        Page<DesignContact> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        QueryWrapper<DesignContact> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", key);
        IPage<DesignContact> result;
        if(key!=null && key!=""){
            result = designContactService.page(page, queryWrapper);
        }else{
            result = designContactService.page(page, null);
        }
        return result;
    }
}




