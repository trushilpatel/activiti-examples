package com.example.processworkflowpostgres.activiti.verifyLicenseDetailsProcess;

import org.activiti.api.process.runtime.connector.Connector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class VerifyLicenseDetailsConfiguration {

    @Bean
    public Connector verifyLicenseDetails_verifyLicenseDetailsProcess() {
        return integrationContext -> {
            Map<String, Object> inboundVariables = integrationContext.getInBoundVariables();
            System.out.println("verifyLicenseDetails_verifyLicenseDetailsProcess");
            System.out.println(inboundVariables.get("username"));
            System.out.println(inboundVariables.get("approved"));
            return integrationContext;
        };
    }

    @Bean
    public Connector approvedLicense_verifyLicenseDetailsProcess() {
        return integrationContext -> {
            System.out.println("Approved : " + integrationContext.getInBoundVariables().get("approved"));
            System.out.println("License Approved " + integrationContext.getProcessInstanceId());
            return integrationContext;
        };
    }

    @Bean
    public Connector rejectedLicense_verifyLicenseDetailsProcess() {
        return integrationContext -> {
            System.out.println("Rejected : " + integrationContext.getInBoundVariables().get("approved"));
            System.out.println("License Rejected " + integrationContext.getProcessInstanceId());
            return integrationContext;
        };
    }

}
