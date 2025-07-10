package com.iceservices.poc.workflowmgr.statemachine.actions;


import com.iceservices.poc.workflowmgr.statemachine.data.ActionRequest;

public interface MasterWorkSaver {

    boolean saveMasterWork(ActionRequest request);
}
