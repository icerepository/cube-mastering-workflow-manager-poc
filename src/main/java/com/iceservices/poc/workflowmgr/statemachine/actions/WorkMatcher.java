package com.iceservices.poc.workflowmgr.statemachine.actions;


import com.iceservices.poc.workflowmgr.statemachine.data.ActionRequest;

public interface WorkMatcher {

    boolean matchWork(ActionRequest actionRequest);
}
