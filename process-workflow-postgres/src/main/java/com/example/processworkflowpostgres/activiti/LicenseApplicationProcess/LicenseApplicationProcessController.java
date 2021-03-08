package com.example.processworkflowpostgres.activiti.LicenseApplicationProcess;

import com.example.processworkflowpostgres.activiti.EventListeners.CommonEventListener;
import com.example.processworkflowpostgres.activiti.common.DTO.ProcessInformationDTO;
import com.example.processworkflowpostgres.activiti.common.DTO.TaskInformationDTO;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.task.model.Task;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/license-application-process")
@Secured("ROLE_ACTIVITI_USER")
public class LicenseApplicationProcessController {
    @Autowired
    ProcessRuntime processRuntime;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;

    @Autowired
    LicenseApplicationProcessController(RuntimeService runtimeService) {
        runtimeService.addEventListener(new CommonEventListener());
    }

    @PostMapping("/start-process")
    ProcessInformationDTO startLicenseApplicationProcessController() {
        runtimeService.startProcessInstanceByKey("InvoiceProcess");
        System.out.println("Hello");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("LicenseApplicationProcessController");
        ProcessInformationDTO processInformationDTO = new ProcessInformationDTO();
        processInformationDTO.setId(processInstance.getId());
        processInformationDTO.setName(processInstance.getProcessDefinitionKey());
        processInformationDTO.setDescription(processInstance.getDescription());
        processInformationDTO.setStartUserId(processInstance.getStartUserId());
        System.out.println(processInstance.toString());
        return processInformationDTO;
    }

    @GetMapping("/all-process")
    List<ProcessInformationDTO> getAllProcessByDefinition() {
        return runtimeService.createExecutionQuery().processDefinitionKey("LicenseApplicationProcessController").list().stream().map(p -> {
            ProcessInformationDTO processInformationDTO = new ProcessInformationDTO();
            processInformationDTO.setId(p.getId());
            processInformationDTO.setName("License Application Process");
            processInformationDTO.setDescription(p.getDescription());
            return processInformationDTO;
        }).collect(Collectors.toList());
    }

    @GetMapping("/current-status")
    String getCurrentStatusByProcessId(@RequestParam String processId) {
        List<TaskInformationDTO> tasks = getCurrentTaskByProcessId(processId);

        if (tasks.size() >= 1) {
            return LicenseApplicationProcessStatus.getStatus(tasks.get(0).getName());
        }

        return LicenseApplicationProcessStatus.getStatus("");

    }

    @GetMapping("/current-task")
    List<TaskInformationDTO> getCurrentTaskByProcessId(@RequestParam String processId) {
        return taskService.createTaskQuery().processInstanceId(processId).list().stream().map(t -> {
            TaskInformationDTO taskInformationDTO = new TaskInformationDTO();

            taskInformationDTO.setAssignee(t.getAssignee());
            taskInformationDTO.setName(t.getName());
            taskInformationDTO.setId(t.getId());
            taskInformationDTO.setClaimTime(t.getClaimTime());
            taskInformationDTO.setDueData(t.getDueDate());
            taskInformationDTO.setCreateTime(t.getCreateTime());
            return taskInformationDTO;
        }).collect(Collectors.toList());
    }

    @GetMapping("/claim/complete-application-fields")
    String claimCompleteApplicationFieldsTask(@RequestParam String taskId, @RequestParam String assignee) {
        taskService.claim(taskId, assignee);
        return "claiming Complete Application Fields Task id " + taskId;
    }

    @GetMapping("/claim/upload-required-documents")
    String claimUploadRequiredDocumentsTask(@RequestParam String taskId, @RequestParam String assignee) {
        taskService.claim(taskId, assignee);
        return "claiming Upload Required document Task id " + taskId;
    }

    @GetMapping("/claim/payment")
    String claimPaymentTask(@RequestParam String taskId, @RequestParam String assignee) {
        taskService.claim(taskId, assignee);
        return "claiming Payment Task id " + taskId;
    }

    @GetMapping("/claim/verify-payment")
    String claimVerifyPaymentTask(@RequestParam String taskId, @RequestParam String assignee) {
        taskService.claim(taskId, assignee);
        return "complete Verify Payment Task id " + taskId;
    }

    @GetMapping("/complete/verify-payment")
    String completeVerifyPaymentTask(@RequestParam String taskId, @RequestParam boolean valid) {
        taskService.setVariable(taskId, "valid", valid);
        taskService.complete(taskId);
        return "complete Verify Payment Task id " + taskId;
    }

    @GetMapping("/complete/complete-application-fields")
    String completeCompleteApplicationFieldsTask(@RequestParam String taskId, @RequestParam String valid) {
        taskService.setVariable(taskId, "valid", valid);
        taskService.complete(taskId);
        return "complete Complete Application Fields Task id " + taskId;
    }

    @GetMapping("/complete/upload-required-documents")
    String completeUploadRequiredDocumentsTask(@RequestParam String taskId, @RequestParam String valid) {
        taskService.setVariable(taskId, "valid", valid);
        taskService.complete(taskId);
        return "complete Upload Required document Task id " + taskId;
    }

    @GetMapping("/complete/payment")
    String completePaymentTask(@RequestParam String taskId, @RequestParam String valid) {
        taskService.setVariable(taskId, "valid", valid);
        taskService.complete(taskId);
        return "complete Payment Task id " + taskId;
    }

    @GetMapping("/all-tasks-by-processId")
    List<TaskInformationDTO> allTasksByProcessId(@RequestParam String processId) {
        List<TaskInformationDTO> tasks = new ArrayList<>(historyService.createHistoricTaskInstanceQuery().processInstanceId(processId).finished().list().stream().map(t -> {
            TaskInformationDTO taskInformationDTO = new TaskInformationDTO();

            taskInformationDTO.setAssignee(t.getAssignee());
            taskInformationDTO.setName(t.getName());
            taskInformationDTO.setId(t.getId());
            taskInformationDTO.setClaimTime(t.getClaimTime());
            taskInformationDTO.setEndTime(t.getEndTime());
            taskInformationDTO.setCreateTime(t.getCreateTime());
            return taskInformationDTO;
        }).collect(Collectors.toList()));
        tasks.addAll(taskService.createTaskQuery().processInstanceId(processId).list().stream().map(t -> {
            TaskInformationDTO taskInformationDTO = new TaskInformationDTO();

            taskInformationDTO.setAssignee(t.getAssignee());
            taskInformationDTO.setName(t.getName());
            taskInformationDTO.setId(t.getId());
            taskInformationDTO.setClaimTime(t.getClaimTime());
            taskInformationDTO.setCreateTime(t.getCreateTime());
            return taskInformationDTO;
        }).collect(Collectors.toList()));
        return tasks;
    }
}
