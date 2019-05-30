package com.xd.modules.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import com.xd.modules.sys.service.IProblemService;
import com.xd.modules.sys.entity.Problem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 *   @description : Problem 控制器
 *   ---------------------------------
 *   @author zxd
 *   @since 2019-05-30
 */
@Api(tags = {"问题控制器"})
@RestController
@RequestMapping("/sys/problem")
public class ProblemController {
private final Logger logger = LoggerFactory.getLogger(ProblemController.class);

    @Autowired
    public IProblemService problemService;


    @GetMapping("/all")
    @ApiOperation(value = "获取所有问题")
    public List<Problem> all(){
        return problemService.list();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取问题")
    public Problem getById(@PathVariable Integer id){
        return problemService.getById(id);
    }

    @PostMapping
    @ApiOperation(value = "新增问题")
    public Object add(@RequestBody Problem o){
        boolean flag = problemService.save(o);
        String result = "";
        if(flag){
            result = "{\"code\":200,\"message\":\"处理成功\"}";
        }else{
            result = "{\"code\":500,\"message\":\"处理失败\"}";
        }
        return JSONObject.parseObject(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据id删除问题")
    public Object delete(@PathVariable Integer id){
        boolean flag = problemService.removeById(id);
        String result = "";
        if(flag){
        result = "{\"code\":200,\"message\":\"处理成功\"}";
        }else{
        result = "{\"code\":500,\"message\":\"处理失败\"}";
        }
        return JSONObject.parseObject(result);
    }

    @DeleteMapping("/ids")
    @ApiOperation(value = "批量删除问题")
    public Object deleteIds(@RequestBody List<Integer> ids){
        boolean flag = problemService.removeByIds(ids);
        String result = "";
        if(flag){
        result = "{\"code\":200,\"message\":\"处理成功\"}";
        }else{
        result = "{\"code\":500,\"message\":\"处理失败\"}";
        }
        return JSONObject.parseObject(result);
    }

    @PutMapping
    @ApiOperation(value = "更新问题")
    public Object update(@RequestBody Problem o){
        boolean flag = problemService.updateById(o);
        String result = "";
        if(flag){
        result = "{\"code\":200,\"message\":\"处理成功\"}";
        }else{
        result = "{\"code\":500,\"message\":\"处理失败\"}";
        }
        return JSONObject.parseObject(result);
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页得到问题")
    public IPage<Problem> page(String key, @RequestParam Integer current, @RequestParam Integer size){
        Page<Problem> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        QueryWrapper<Problem> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", key);
        IPage<Problem> result;
        if(key!=null && key!=""){
            result = problemService.page(page, queryWrapper);
        }else{
            result = problemService.page(page, null);
        }
        return result;
    }
}




