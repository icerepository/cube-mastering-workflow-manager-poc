package com.iceservices.poc.workflowmgr.statemachine.actions;


import com.iceservices.poc.workflowmgr.statemachine.data.ActionRequest;

public interface WorkMerger {

    boolean mergeWork(ActionRequest actionRequest);
}
