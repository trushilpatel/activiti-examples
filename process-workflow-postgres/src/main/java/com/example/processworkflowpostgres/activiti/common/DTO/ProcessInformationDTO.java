package com.example.processworkflowpostgres.activiti.common.DTO;

import java.io.Serializable;

public class ProcessInformationDTO implements Serializable {
    String id;
    String name;
    String description;
    String startUserId;

    public ProcessInformationDTO() {
    }

    public ProcessInformationDTO(String id, String name, String description, String startUserId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startUserId = startUserId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartUserId() {
        return startUserId;
    }

    public void setStartUserId(String startUserId) {
        this.startUserId = startUserId;
    }
}
