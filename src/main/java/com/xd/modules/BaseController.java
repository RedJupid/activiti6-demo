package com.xd.modules;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class BaseController<S extends BaseService,T>{
    @Autowired
    S baseService;

    /**
     * 判断操作是否成功
     * @param flag
     * @return
     */
    public Object judge(int flag){
        if (flag>0){
            return JSONObject.parseObject("{\"code\":200,\"message\":\"处理成功\"}");
        }else{
            return JSONObject.parseObject("{\"code\":500,\"message\":\"处理失败\"}");
        }
    }

    @GetMapping("/{id}")
    public T getById(@PathVariable Long id){
        return (T)baseService.selectByPrimaryKey(id);
    }

    @PostMapping
    public Object add(T entity){
        int flag = baseService.insert(entity);
        return judge(flag);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Long id){
        int flag = baseService.deleteByPrimaryKey(id);
        return judge(flag);
    }

    @PutMapping
    public Object update(T entity){
        int flag = baseService.updateByPrimaryKey(entity);
        return judge(flag);
    }

}
