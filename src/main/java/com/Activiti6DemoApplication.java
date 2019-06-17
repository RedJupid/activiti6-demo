package com;

import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableSwagger2
@EnableTransactionManagement
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
