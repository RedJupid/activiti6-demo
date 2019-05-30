package com;

import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.xd.modules.**.mapper")
public class Activiti6DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Activiti6DemoApplication.class, args);
    }


    @Bean
    public ProcessDiagramGenerator processDiagramGenerator(){
        return new DefaultProcessDiagramGenerator();
    }
}
