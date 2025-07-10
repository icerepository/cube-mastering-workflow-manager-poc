package com.iceservices.poc.workflowmgr.actionhandlers;

import com.iceservices.poc.workflowmgr.infrastructure.message.AppMessenger;
import com.iceservices.poc.workflowmgr.infrastructure.message.config.MessageConfig;
import com.iceservices.poc.workflowmgr.infrastructure.message.data.AppMessage;
import com.iceservices.poc.workflowmgr.statemachine.actions.MasterWorkSaver;
import com.iceservices.poc.workflowmgr.statemachine.data.ActionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class DefaultMasterWorkSaver implements MasterWorkSaver {

    private final AppMessenger appMessenger;

    private final MessageConfig messageConfig;

    @Override
    public boolean saveMasterWork(ActionRequest actionRequest) {
        log.info("received request: {}", actionRequest);
        appMessenger.sendMessage(new AppMessage<>(
                actionRequest,
                messageConfig.getDestination().getSaveMasterWork(),
                "default-master-work-saver"
        ));
        log.info("...work has been \"saved\"!");
        return true;
    }
}
