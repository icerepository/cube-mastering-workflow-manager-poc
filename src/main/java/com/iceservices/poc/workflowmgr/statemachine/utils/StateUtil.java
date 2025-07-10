package com.iceservices.poc.workflowmgr.statemachine.utils;

import com.iceservices.poc.workflowmgr.statemachine.events.MasteringEvent;
import com.iceservices.poc.workflowmgr.statemachine.states.MasteringState;
import org.springframework.statemachine.action.Action;

public interface StateUtil {

    Action<MasteringState, MasteringEvent> triggerEventOnEntry(MasteringEvent event);
}
