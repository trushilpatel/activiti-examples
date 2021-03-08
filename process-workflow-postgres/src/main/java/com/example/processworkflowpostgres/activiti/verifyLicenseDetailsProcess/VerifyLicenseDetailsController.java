package com.example.processworkflowpostgres.activiti.verifyLicenseDetailsProcess;

import com.example.processworkflowpostgres.security.SecurityUtils;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/verify-license-details")
public class VerifyLicenseDetailsController {
    @Autowired
    SecurityUtils securityUtils;

    @Autowired
    TaskService taskService;

    @Autowired
    RuntimeService runtimeService;

    @PostMapping("/start-process")
    String startProcess(@RequestParam String username) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("username", username);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("verifyLicenseDetailsProcess", variables);
        return "Process Created with ID : " + processInstance.getId();
    }

    @GetMapping("/all-tasks")
    String getTasksList(@RequestParam String username) {
        return taskService.createTaskQuery().list().toString();
    }

    @GetMapping("/user-tasks")
    String getTasksForUser(@RequestParam String username) {
        return taskService.createTaskQuery().taskAssignee(username).list().toString();
    }

    @GetMapping("/claim-task")
    String claimTask(@RequestParam String username, @RequestParam String taskId) {
        taskService.claim(taskId, username);
        return "Task " + taskId + " is claimed by user " + username;
    }

    @GetMapping("/complete-task")
    String completeTask(@RequestParam String username, @RequestParam String taskId, @RequestParam Boolean approved) {
        Map<String, Object> variables = taskService.getVariables(taskId);
        variables.put("approved", approved);
        taskService.complete(taskId, variables);
        return "Task Completed : " + taskId;
    }
}
