package com.example.processworkflowpostgres;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
    @Bean
    public DataSource database() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/process_workflow_postgresql")
                .username("activiti_admin")
                .password("argusadmin")
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}
