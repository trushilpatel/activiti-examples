package com.example.processworkflowpostgres.activiti.CommercialCarrierRoadLicenseProcess;

import org.activiti.api.process.runtime.connector.Connector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommercialCarrierRoadLicenseProcessConfiguration {
    @Bean
    public Connector rejectedApplication_CommercialCarrierRoadLicenseProcess() {
        return integrationContext -> {
            System.out.println("rejectedApplication_CommercialCarrierRoadLicenseProcessConfiguration");
            return integrationContext;
        };
    }

    @Bean
    public Connector updateDocuments_CommercialCarrierRoadLicenseProcess() {
        return integrationContext -> {
            System.out.println("updateDocuments_CommercialCarrierRoadLicenseProcess");
            return integrationContext;
        };
    }

    @Bean
    public Connector deliverLicenseToCustomer_CommercialCarrierRoadLicenseProcess() {
        return integrationContext -> {
            System.out.println("deliverLicenseToCustomer_CommercialCarrierRoadLicenseProcess");
            return integrationContext;
        };
    }
}
