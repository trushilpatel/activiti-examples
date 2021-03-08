package com.example.processworkflowpostgres.activiti.MessageEventExample;

import com.example.processworkflowpostgres.constants.ActivitiRoles;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Secured(ActivitiRoles.ActivitiUser)
@RequestMapping(value = "message-event-example-process")
public class MessageEventExampleController {
    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    RepositoryService repositoryService;

    @PostMapping("/create-process")
    String createProcess(@RequestParam String username) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().messageEventSubscriptionName("New Message").singleResult();
        System.out.println(processDefinition);

        return runtimeService.startProcessInstanceByMessage("New Message").toString();

    }
}
