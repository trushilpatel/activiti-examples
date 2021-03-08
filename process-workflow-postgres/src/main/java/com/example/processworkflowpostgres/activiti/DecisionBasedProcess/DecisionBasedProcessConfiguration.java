package com.example.processworkflowpostgres.activiti.DecisionBasedProcess;

import org.activiti.api.process.runtime.connector.Connector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class DecisionBasedProcessConfiguration {
    @Bean
    public Connector applyForLicense_DecisionBasedProcess() {
        return integrationContext -> {
            Map<String, Object> inBoundVariables = integrationContext.getInBoundVariables();
            DecisionBasedProcess decisionBasedProcess = (DecisionBasedProcess) inBoundVariables.get("decisionBasedProcess");
            if (decisionBasedProcess.getValid()) {
                System.out.println("Approving Licence : " + integrationContext.getProcessInstanceId());
//                integrationContext.addOutBoundVariable("approved", true);
            } else {
                System.out.println("Rejecting Licence : " + integrationContext.getProcessInstanceId());
//                integrationContext.addOutBoundVariable("approved", false);
            }
            return integrationContext;
        };
    }

    @Bean
    public Connector approvedLicense_DecisionBasedProcess() {
        return integrationContext -> {
            System.out.println("APPROVED Licence : " + integrationContext.getProcessInstanceId());
            return integrationContext;
        };
    }

    @Bean
    public Connector rejectedLicense_DecisionBasedProcess() {
        return integrationContext -> {
            System.out.println("REJECTED Licence : " + integrationContext.getProcessInstanceId());
            return integrationContext;
        };
    }
}
