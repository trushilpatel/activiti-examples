package com.example.demo;

import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestApiController {

    @Autowired
    TaskRuntime taskRuntime;

    @Autowired
    SecurityUtils securityUtils;

    @GetMapping("/")
    List<Task> home(@RequestParam(required = true) String username) {
        securityUtils.logInAs(username);
        System.out.println("Username : " + username);
        return taskRuntime.tasks(Pageable.of(0, 100)).getContent();
    }

    @GetMapping("/start-task")
    String startTask(@RequestParam(required = true) String username, @RequestParam(required = true) String taskName, @RequestParam(required = true) String taskDescription) {
        securityUtils.logInAs(username);
        Task task = taskRuntime.create(TaskPayloadBuilder.create()
                .withName(taskName)
                .withDescription(taskDescription)
                .withPriority(10)
                .build());
        System.out.println("Task Created ");
        return "Task Started : " + task.getId();
    }

    @GetMapping("/claim-task")
    String claimTask(@RequestParam(required = true) String username, @RequestParam(required = true) String taskId) {
        securityUtils.logInAs(username);
        Task task = taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(taskId).build());
        System.out.println("Task Claimed");
        return "Task Claimed : " + task.getId();
    }

    @GetMapping("/completed-task")
    String completedTask(@RequestParam(required = true) String username, @RequestParam(required = true) String taskId) {
        securityUtils.logInAs(username);
        Task task = taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(taskId).build());
        System.out.println("Task Completed");
        return ("Task Completed : " + task.getId());
    }
}
