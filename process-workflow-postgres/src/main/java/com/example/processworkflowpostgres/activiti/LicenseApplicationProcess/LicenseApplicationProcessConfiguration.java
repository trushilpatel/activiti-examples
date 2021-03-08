package com.example.processworkflowpostgres.activiti.LicenseApplicationProcess;

import org.activiti.api.process.runtime.connector.Connector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LicenseApplicationProcessConfiguration {
    @Bean
    Connector NotifyApplicantInvalidApplicationFields_LicenseApplicationProcess() {
        return integrationContext -> {
            System.out.println("NotifyApplicantInvalidApplicationFields_LicenseApplicationProcess");
            return integrationContext;
        };
    }

    @Bean
    Connector NotifyApplicantInvalidDocuments_LicenseApplicationProcess() {
        return integrationContext -> {
            System.out.println("NotifyApplicantInvalidDocuments_LicenseApplicationProcess");
            return integrationContext;
        };
    }

    @Bean
    Connector NotifyApplicantPaymentFailed_LicenseApplicationProcess() {
        return integrationContext -> {
            System.out.println("NotifyApplicantPaymentFailed_LicenseApplicationProcess");
            return integrationContext;
        };
    }

    @Bean
    Connector GenerateReceipt_LicenseApplicationProcess() {
        return integrationContext -> {
            System.out.println("GenerateReceipt_LicenseApplicationProcess");
            return integrationContext;
        };
    }

    @Bean
    Connector PrintAndSendReceipt_LicenseApplicationProcess() {
        return integrationContext -> {
            System.out.println("PrintAndSendReceipt_LicenseApplicationProcess");
            return integrationContext;
        };
    }
}
