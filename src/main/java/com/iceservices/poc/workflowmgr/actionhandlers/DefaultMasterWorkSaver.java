package com.iceservices.poc.workflowmgr.actionhandlers;

import com.iceservices.poc.workflowmgr.statemachine.actions.MasterWorkSaver;
import com.iceservices.poc.workflowmgr.statemachine.data.ActionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static com.iceservices.poc.workflowmgr.utils.UtilityFunctions.tryToSleep;

@Component
@Slf4j
public class DefaultMasterWorkSaver implements MasterWorkSaver {

    @Override
    public boolean saveMasterWork(ActionRequest actionRequest) {
        log.info("received request: {}", actionRequest);
        tryToSleep(Duration.ofSeconds(3));
        log.info("...work has been \"saved\"!");
        return true;
    }
}
