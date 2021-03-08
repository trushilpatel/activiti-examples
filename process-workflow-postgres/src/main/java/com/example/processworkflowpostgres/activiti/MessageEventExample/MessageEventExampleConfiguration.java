package com.example.processworkflowpostgres.activiti.MessageEventExample;

import org.activiti.api.process.runtime.connector.Connector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageEventExampleConfiguration {
    @Bean
    Connector helloThere_MessageEventExampleConfiguration() {
        return integrationContext -> {
            System.out.println("HEllo there from Message Event Example Process");
            return integrationContext;
        };
    }
}
