package com.example.processworkflowpostgres;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProcessWorkflowPostgresApplication {

    @Autowired
    private SpringProcessEngineConfiguration springProcessEngineConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(ProcessWorkflowPostgresApplication.class, args);
    }
}
