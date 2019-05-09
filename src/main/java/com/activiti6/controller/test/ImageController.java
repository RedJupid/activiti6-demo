package com.activiti6.controller.test;

import com.activiti6.util.FlowUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

@Controller
public class ImageController {

    @Autowired
    FlowUtils flowUtils;

    @GetMapping(value = "/image")
    public ModelAndView home(ModelAndView modelAndView){
        modelAndView.setViewName("home");
        return modelAndView;
    }

    /**
     * 查看实例流程图，根据流程实例ID获取流程图
     */
    @GetMapping(value="processImg/{instanceId}")
    public void traceprocess(HttpServletResponse response, @PathVariable("instanceId")String instanceId) throws Exception{

        InputStream in = flowUtils.getResourceDiagramInputStream(instanceId);
        ServletOutputStream output = response.getOutputStream();
        IOUtils.copy(in, output);


    }
}
