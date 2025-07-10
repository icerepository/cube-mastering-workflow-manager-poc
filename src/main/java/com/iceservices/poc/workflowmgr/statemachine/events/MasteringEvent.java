package com.iceservices.poc.workflowmgr.statemachine.events;

public enum MasteringEvent {

    // Happy Path Only
    SAVED_MASTER,
    MATCHED_WORK,
    MERGED_WORK,

    //FIXME: Seem like commands?
    SAVE_MASTER_COMMAND,
    MATCH_WORK_COMMAND,
    MERGE_WORK_COMMAND,
}
