package com.example.processworkflowpostgres.activiti.DecisionBasedProcess;

import com.example.processworkflowpostgres.security.SecurityUtils;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/decision-based-process")
public class DecisionBasedProcessController {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    ProcessRuntime processRuntime;

    @Autowired
    SecurityUtils securityUtils;

    @Autowired
    HistoryService historyService;

    @Autowired
    TaskRuntime taskRuntime;

    @Autowired
    TaskService taskService;

    @PostMapping("")
    String createProcess(@RequestBody(required = true) DecisionBasedProcess decisionBasedProcess) {
        securityUtils.logInAs(decisionBasedProcess.getUsername());

        try {
            taskRuntime.create(TaskPayloadBuilder.create().withName("TaskName").build());
//            Map<String, Object> variables = new HashMap<String, Object>();
//            variables.put("decisionBasedProcess", decisionBasedProcess);
//            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("DecisionBasedProcess", variables);
//            return "Process Created : " + processInstance.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ERROR: WHILE STARTING PROCESS";
    }

    @GetMapping("/completed-process")
    List<HistoricProcessInstance> completedProcess(@RequestParam(required = true) String username) {
        securityUtils.logInAs(username);
        return historyService.createHistoricProcessInstanceQuery().processDefinitionKey("decisionBasedProcess").list();
    }

    @GetMapping("/completed-tasks")
    List<HistoricTaskInstance> completedTasksByUsername(@RequestParam(required = true) String username) {
        securityUtils.logInAs(username);
        return historyService.createHistoricTaskInstanceQuery().finished().taskAssigneeLikeIgnoreCase(username).list();
    }

    @GetMapping("/pending-tasks")
    List<HistoricTaskInstance> pendingTasks(@RequestParam(required = true) String username) {
        securityUtils.logInAs(username);
        return historyService.createHistoricTaskInstanceQuery().unfinished().taskAssigneeLikeIgnoreCase(username).list();
    }
}
