package com.iceservices.poc.workflowmgr.actionhandlers;

import com.iceservices.poc.workflowmgr.statemachine.actions.WorkMatcher;
import com.iceservices.poc.workflowmgr.statemachine.data.ActionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static com.iceservices.poc.workflowmgr.utils.UtilityFunctions.tryToSleep;

@Component
@Slf4j
public class DefaultWorkMatcher implements WorkMatcher {

    @Override
    public boolean matchWork(ActionRequest actionRequest) {
        log.info("received a match request: {}", actionRequest);
        tryToSleep(Duration.ofSeconds(2));
        log.info("...work matched!");
        return true;
    }
}
