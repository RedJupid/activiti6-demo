package com.activiti6;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class Activiti6DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Activiti6DemoApplication.class, args);
    }


    @Bean
    public ProcessDiagramGenerator processDiagramGenerator(){
        return new DefaultProcessDiagramGenerator();
    }
}
