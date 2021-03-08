package com.example.processworkflowpostgres.activiti.EventListeners;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;

public class CommonEventListener implements ActivitiEventListener {
    @Override
    public void onEvent(ActivitiEvent event) {
        switch (event.getType()) {
            case PROCESS_STARTED: {
                System.out.println("NEW PROCESS STARTED " + event.getExecutionId());
            }
            case PROCESS_COMPLETED: {
                System.out.println("PROCESS COMPLETED " + event.getExecutionId());
            }
            case VARIABLE_CREATED: {
                System.out.println("VARIABLE CREATED " + event.getExecutionId());
            }
            case TASK_ASSIGNED: {
                System.out.println("TASK ASSIGNED " + event.getExecutionId());
            }
            case TASK_COMPLETED: {
                System.out.println("TASK COMPLETED " + event.getExecutionId());
            }
            case TASK_CREATED: {
                System.out.println("TASK CREATED " + event.getExecutionId());
            }
        }
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}
