package com.xd.modules.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import com.xd.modules.sys.service.ICheckOpinionService;
import com.xd.modules.sys.entity.CheckOpinion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 *   @description : CheckOpinion 控制器
 *   ---------------------------------
 *   @author zxd
 *   @since 2019-05-30
 */
@Api(tags = {"审核意见控制器"})
@RestController
@RequestMapping("/sys/check-opinion")
public class CheckOpinionController {
private final Logger logger = LoggerFactory.getLogger(CheckOpinionController.class);

    @Autowired
    public ICheckOpinionService checkOpinionService;


    @GetMapping("/all")
    @ApiOperation(value = "获取所有审核意见")
    public List<CheckOpinion> all(){
        return checkOpinionService.list();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取审核意见")
    public CheckOpinion getById(@PathVariable Integer id){
        return checkOpinionService.getById(id);
    }

    @PostMapping
    @ApiOperation(value = "新增审核意见")
    public Object add(@RequestBody CheckOpinion o){
        boolean flag = checkOpinionService.save(o);
        String result = "";
        if(flag){
            result = "{\"code\":200,\"message\":\"处理成功\"}";
        }else{
            result = "{\"code\":500,\"message\":\"处理失败\"}";
        }
        return JSONObject.parseObject(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据id删除审核意见")
    public Object delete(@PathVariable Integer id){
        boolean flag = checkOpinionService.removeById(id);
        String result = "";
        if(flag){
        result = "{\"code\":200,\"message\":\"处理成功\"}";
        }else{
        result = "{\"code\":500,\"message\":\"处理失败\"}";
        }
        return JSONObject.parseObject(result);
    }

    @DeleteMapping("/ids")
    @ApiOperation(value = "批量删除审核意见")
    public Object deleteIds(@RequestBody List<Integer> ids){
        boolean flag = checkOpinionService.removeByIds(ids);
        String result = "";
        if(flag){
        result = "{\"code\":200,\"message\":\"处理成功\"}";
        }else{
        result = "{\"code\":500,\"message\":\"处理失败\"}";
        }
        return JSONObject.parseObject(result);
    }

    @PutMapping
    @ApiOperation(value = "更新审核意见")
    public Object update(@RequestBody CheckOpinion o){
        boolean flag = checkOpinionService.updateById(o);
        String result = "";
        if(flag){
        result = "{\"code\":200,\"message\":\"处理成功\"}";
        }else{
        result = "{\"code\":500,\"message\":\"处理失败\"}";
        }
        return JSONObject.parseObject(result);
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页得到审核意见")
    public IPage<CheckOpinion> page(String key, @RequestParam Integer current, @RequestParam Integer size){
        Page<CheckOpinion> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        QueryWrapper<CheckOpinion> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", key);
        IPage<CheckOpinion> result;
        if(key!=null && key!=""){
            result = checkOpinionService.page(page, queryWrapper);
        }else{
            result = checkOpinionService.page(page, null);
        }
        return result;
    }
}




