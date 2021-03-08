package com.example.demo;

import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class RestApiController {

    @Autowired
    SecurityUtils securityUtils;

    @Autowired
    ProcessRuntime processRuntime;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    MyService myService;

    @GetMapping("/task-list")
    String processList(@RequestParam(required = true) String username) {
        securityUtils.logInAs(username);
        System.out.println("Username : " + username);
        return myService.getTasks(username);
    }

    @GetMapping("/start-process")
    String startprocess(@RequestParam(required = true) String username) {
        securityUtils.logInAs(username);
        return myService.startProcess(username);
    }

    @GetMapping("/completed-process")
    String completedProcess(@RequestParam(required = true) String username, @RequestParam(required = true) String taskId) {
        securityUtils.logInAs(username);
        myService.completeTask(taskId);
        System.out.println("Process Completed");
        return ("process Completed : ");
    }

    @GetMapping("/tasks/{assignee}")
    String getTasks(@PathVariable("assignee") String assignee) {
//        securityUtils.logInAs(assignee);
        return myService.getTasks(assignee).toString();
    }
}
