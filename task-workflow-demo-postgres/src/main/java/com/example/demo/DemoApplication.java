package com.example.demo;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public DataSource database() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/activiti_task_workflow_demo")
                .username("activiti_admin")
                .password("argusadmin")
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}
