package com.activiti6.controller.common;

import com.activiti6.util.FlowUtils;
import com.activiti6.util.process.FlowChartUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@Controller
public class ImageController {

//    @Autowired
//    FlowUtils flowUtils;

    @Autowired
    FlowChartUtil flowChartUtil;

    @GetMapping(value = "/image")
    public ModelAndView home(ModelAndView modelAndView){
        modelAndView.setViewName("image");
        return modelAndView;
    }

    /**
     * 查看实例流程图，根据流程实例ID获取流程图
     */
    @GetMapping(value="processImg/{instanceId}")
    public void traceprocess(HttpServletRequest request, HttpServletResponse response, @PathVariable("instanceId")String instanceId) throws Exception{

        response.setContentType("application/x-png");
//        response.addHeader("Content-Disposition", "attachment;fileName=" + "afkajf.png");
//        InputStream in = flowUtils.getResourceDiagramInputStream(instanceId);
        InputStream in = flowChartUtil.generateStream(request, response, instanceId, true);
        ServletOutputStream output = response.getOutputStream();
        IOUtils.copy(in, output);


    }
}
