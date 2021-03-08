package com.example.processworkflowpostgres.activiti.SignalEventExample;

import com.example.processworkflowpostgres.constants.ActivitiRoles;
import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("signal-event-example-process")
@Secured(ActivitiRoles.ActivitiUser)
public class SignalEventExampleController {
    @Autowired
    RuntimeService runtimeService;

    @PostMapping("/create-process")
    void createProcess(@RequestParam String username) {
        runtimeService.signalEventReceived("AlertSignal");
    }
}
