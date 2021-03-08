package com.example.processworkflowpostgres.activiti.SignalEventExample;

import org.activiti.api.process.runtime.connector.Connector;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SignalEventExampleConfiguration {
    @Autowired
    RuntimeService runtimeService;

    @Autowired
    RepositoryService repositoryService;

    @Bean
    Connector helloThere_SignalEventExampleConfiguration() {
        return integrationContext -> {
            System.out.println(repositoryService.createProcessDefinitionQuery().messageEventSubscriptionName("message_SignalEventExampleConfiguration").list());
            System.out.println("Hello there from Signal Event Example Configuration");
            return integrationContext;
        };
    }

    @Bean
    Connector helloThere() {
        return integrationContext -> {
            System.out.println("Hello there");
            return integrationContext;
        };
    }

    @Bean
    Connector helloThereFromMessageEventService_SignalEventExampleConfiguration() {
        return integrationContext -> {
            System.out.println("SignalEventExampleConfiguration");
            return integrationContext;
        };
    }
}
