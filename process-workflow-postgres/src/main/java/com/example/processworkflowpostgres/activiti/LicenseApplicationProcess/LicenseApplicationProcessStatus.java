package com.example.processworkflowpostgres.activiti.LicenseApplicationProcess;

public class LicenseApplicationProcessStatus {
    public static final String TASK_NAME_COMPLETE_APPLICATION_FIELDS = "Complete Application Fields";
    public static final String TASK_NAME_VERIFY_PAYMENT = "Verify Payment";
    public static final String TASK_NAME_PAYMENT = "Payment";
    public static final String TASK_NAME_UPLOAD_REQUIRED_DOCUMENTS = "Upload Required Documents";
    public static final String TASK_NAME_GENERATE_PAYMENT_RECEIPT = "Generate Receipt";
    public static final String TASK_NAME_PRINT_AND_SEND_RECEIPT = "Print And Send Receipt";

    public static final String ON_GOING_COMPLETE_APPLICATION_FIELDS = "ON GOING COMPLETE APPLICATION FIELDS";
    public static final String ON_GOING_VERIFY_PAYMENT = "ON GOING VERIFY PAYMENT";
    public static final String ON_GOING_PAYMENT = "ON GOING PAYMENT";
    public static final String ON_GOING_UPLOAD_REQUIRED_DOCUMENTS = "ON GOING UPLOAD REQUIRED DOCUMENTS";
    public static final String ON_GOING_GENERATE_PAYMENT_RECEIPT = "ON GOING GENERATE PAYMENT RECEIPT";
    public static final String ON_GOING_PRINT_AND_SEND_RECEIPT = "ON GOING PRINT AND SEND RECEIPT";

    public static String getStatus(String taskName) {
        String status;

        switch (taskName) {
            case TASK_NAME_COMPLETE_APPLICATION_FIELDS:
                return ON_GOING_COMPLETE_APPLICATION_FIELDS;
            case TASK_NAME_UPLOAD_REQUIRED_DOCUMENTS:
                return ON_GOING_UPLOAD_REQUIRED_DOCUMENTS;
            case TASK_NAME_PAYMENT:
                return ON_GOING_PAYMENT;
            case TASK_NAME_GENERATE_PAYMENT_RECEIPT:
                return ON_GOING_GENERATE_PAYMENT_RECEIPT;
            case TASK_NAME_VERIFY_PAYMENT:
                return ON_GOING_VERIFY_PAYMENT;
            case TASK_NAME_PRINT_AND_SEND_RECEIPT:
                return ON_GOING_PRINT_AND_SEND_RECEIPT;
            default:
                return "SOMETHING IS WRONG WITH STATUS...";
        }
    }

}
