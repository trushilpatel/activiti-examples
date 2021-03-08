package com.example.processworkflowpostgres.activiti.CommercialCarrierRoadLicenseProcess;

public class CommercialCarrierRoadLicenseProcess {
    private String username;
    private String processName;
    private String description;

    public CommercialCarrierRoadLicenseProcess() {
    }

    public CommercialCarrierRoadLicenseProcess(String username, String processName, String description) {
        this.username = username;
        this.processName = processName;
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
