package com.rpm.tootajatehaldamine.workflow;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface

public interface EmployeeOnboardingWorkflow {
    @WorkflowMethod
    void startOnboarding(EmployeeData data);
}

