package com.example.processworkflowpostgres.activiti.CommercialCarrierRoadLicenseProcess;

import com.example.processworkflowpostgres.security.SecurityUtils;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.bpmn.model.Task;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/commercial-carrier-road")
public class CommercialCarrierRoadLicenseProcessController {
    @Autowired
    RuntimeService runtimeService;

    @Autowired
    ProcessRuntime processRuntime;

    @Autowired
    SecurityUtils securityUtils;

    @Autowired
    HistoryService historyService;

    @Autowired
    TaskService taskService;

    @PostMapping("")
    String createProcess(@RequestBody(required = true) CommercialCarrierRoadLicenseProcess commercialCarrierRoadLicenseProcess) {

        try {
            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("commercialCarrierRoadLicenseProcess", commercialCarrierRoadLicenseProcess);
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("commercialCarrierRoadLicenseProcess", variables);
            return "Process Created : " + processInstance.getId();
        } catch (Exception e) {
            System.out.println(e.toString());
            return "ERROR: WHILE STARTING PROCESS";
        }
    }

    @GetMapping("/all-tasks")
    String allTasks(@RequestParam String username) {
        return taskService.createTaskQuery().processDefinitionKey("commercialCarrierRoadLicenseProcess").list().toString();
    }

    @GetMapping("/user-tasks")
    String userTasks(@RequestParam String username) {
        return taskService.createTaskQuery().taskAssignee(username).processDefinitionKey("commercialCarrierRoadLicenseProcess").list().toString();
    }

    @GetMapping("/claim-task")
    String claimTask(@RequestParam String username, @RequestParam String taskId) {
        taskService.claim(taskId, username);
        return this.allTasks(username);
    }

    @GetMapping("/complete-task")
    String completeTask(@RequestParam String username, @RequestParam String taskId, @RequestParam String next) {
       /*
        next => "rejectedApplication" or "generatePaymentReceipt" or "updateDocuments"
        */
        Map<String, Object> variable = new HashMap<>();
        variable.put("nextTask", next);
        taskService.complete(taskId, variable);
        return this.allTasks(username);
    }

    @GetMapping("/completed-process")
    List<HistoricProcessInstance> completedProcess(@RequestParam(required = true) String username) {
        return historyService.createHistoricProcessInstanceQuery().processDefinitionKey("commercialCarrierRoadLicenseProcess").finished().list();
    }

    @GetMapping("/completed-tasks")
    List<HistoricTaskInstance> completedTasksByUsername(@RequestParam(required = true) String username) {
        return historyService.createHistoricTaskInstanceQuery().finished().taskAssigneeLikeIgnoreCase(username).list();
    }

    @GetMapping("/pending-tasks")
    String pendingTasks(@RequestParam(required = true) String username) {
        return taskService.createTaskQuery().taskAssignee(username).list().toString();
    }

    @GetMapping("/task-by-processId")
    String taskByProcessId(@RequestParam String username, @RequestParam String processId) {
        return taskService.createTaskQuery().processInstanceId(processId).list().toString();
    }

    @GetMapping("/task-by-task-name")
    String taskByTaskName(@RequestParam String taskName) {
        return taskService.createTaskQuery().taskName(taskName).list().toString();
    }

    @GetMapping("/historic-task-by-task-name")
    String historicTaskByTaskName(@RequestParam String taskName) {
//        System.out.println(historyService.getHistoricIdentityLinksForTask(taskName));
        return historyService.createHistoricTaskInstanceQuery().finished().taskName(taskName).list().toString();
    }

    @GetMapping("/historic-process-by-processId")
    String historicProcessByProcessId(@RequestParam String processId) {
        return historyService.createHistoricProcessInstanceQuery().finished().processInstanceId(processId).list().toString();
    }

    @GetMapping("/task-by-group")
    String taskByGroup(@RequestParam String groupName) {
        return taskService.createTaskQuery().taskCandidateGroup(groupName).list().toString();
    }
}
