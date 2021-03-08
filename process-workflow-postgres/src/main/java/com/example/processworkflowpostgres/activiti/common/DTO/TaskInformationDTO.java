package com.example.processworkflowpostgres.activiti.common.DTO;

import org.springframework.data.util.Streamable;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Date;

public class TaskInformationDTO implements Serializable {
    String id;
    String name;
    String assignee;
    Date claimTime;
    Date createTime;
    Date dueData;
    Date endTime;

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public TaskInformationDTO() {
    }

    public TaskInformationDTO(String id, String name, Date endTime, String assignee, Date claimTime, Date createTime, Date dueData) {
        this.id = id;
        this.name = name;
        this.endTime = endTime;
        this.assignee = assignee;
        this.claimTime = claimTime;
        this.createTime = createTime;
        this.dueData = dueData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Date getClaimTime() {
        return claimTime;
    }

    public void setClaimTime(Date claimTime) {
        this.claimTime = claimTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDueData() {
        return dueData;
    }

    public void setDueData(Date dueData) {
        this.dueData = dueData;
    }
}
