package com.example.demo;

import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MyService {
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    private RuntimeService runtimeService;

    public String startProcess(String assignee) {
        Map<String, Object> variable = new HashMap<String, Object>();
        variable.put("user", assignee);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("simpleProcess", variable);
        return "Process Created : " + processInstance.getId();
    }

    public String getTasks(String assignee) {
        List<Task> tasks = this.taskService.createTaskQuery().taskAssignee(assignee).list();
        System.out.println(tasks);
        return tasks.toString();
    }

    public void completeTask(String taskId) {
        this.taskService.complete(taskId);
    }

    private String processInfo() {
        List<Task> tasks = taskService.createTaskQuery().orderByTaskCreateTime().asc().list();

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Number of process definitions : ").append(repositoryService.createProcessDefinitionQuery().count()).append("--> Tasks >> ");

        for (Task task : tasks) {
            stringBuilder.append(task).append(" | Assignee: ").append(task.getAssignee()).append(" | Description: ").append(task.getDescription());
        }

        return stringBuilder.toString();
    }
}
