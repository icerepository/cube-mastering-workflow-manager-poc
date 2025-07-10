package com.iceservices.poc.workflowmgr.statemachine.events;

public enum MasteringEvent {

    // Happy Path Only
    SAVE_MASTER_REQUESTED, // find a better name
    SAVED_MASTER,
    MATCHED_WORK,
    MERGED_WORK
}
