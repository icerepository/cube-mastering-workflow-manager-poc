package com.iceservices.poc.workflowmgr.actionhandlers;

import com.iceservices.poc.workflowmgr.statemachine.actions.WorkMerger;
import com.iceservices.poc.workflowmgr.statemachine.data.ActionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static com.iceservices.poc.workflowmgr.utils.UtilityFunctions.tryToSleep;

@Component
@Slf4j
public class DefaultWorkMerger implements WorkMerger {

    @Override
    public boolean mergeWork(ActionRequest actionRequest) {
        log.info("received a merge-work request: {}", actionRequest);
        tryToSleep(Duration.ofSeconds(1));
        log.info("...works merged!");
        return true;
    }
}
