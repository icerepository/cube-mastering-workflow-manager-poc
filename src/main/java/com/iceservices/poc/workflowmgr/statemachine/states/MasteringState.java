package com.iceservices.poc.workflowmgr.statemachine.states;

public enum MasteringState {

    // Happy Path Only
    INITIAL,
    AWAITING_SAVE_MASTER_WORK,

    SAVED_MASTER_WORK,
    AWAITING_MATCH_MASTER_WORK,

    MATCHED_MASTER_WORK,
    AWAITING_MERGE_MASTER_WORK,
    
    MERGED_MASTER_WORK,
}
