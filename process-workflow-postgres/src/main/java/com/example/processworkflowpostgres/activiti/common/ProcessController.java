package com.example.processworkflowpostgres.activiti.common;

import com.example.processworkflowpostgres.security.SecurityUtils;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activiti/process")
public class ProcessController {
    @Autowired
    SecurityUtils securityUtils;

    @Autowired
    ProcessRuntime processRuntime;

    @GetMapping("")
    List<ProcessDefinition> getProcesses(@RequestParam(required = true) String username) {
        System.out.println(">>> PROCESS DEFINITIONS");
        securityUtils.logInAs(username);
        Page<ProcessDefinition> processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0, 1000));

        return processDefinitionPage.getContent();
    }
}
