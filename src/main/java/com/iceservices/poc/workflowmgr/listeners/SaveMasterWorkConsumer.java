package com.iceservices.poc.workflowmgr.listeners;

import com.iceservices.poc.workflowmgr.infrastructure.message.AppMessenger;
import com.iceservices.poc.workflowmgr.infrastructure.message.config.MessageConfig;
import com.iceservices.poc.workflowmgr.infrastructure.message.data.AppMessage;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaveMasterWorkConsumer {

    private final AppMessenger appMessenger;

    private final MessageConfig messageConfig;


    @SqsListener("saveMasterQueue")
    public void saveMasterWorkHandler(String message) {
        log.info("message: {}", message);
        log.info("...work has been \"saved\"!");
        appMessenger.sendMessage(new AppMessage<>(
                "master work saved",
                "save-master-queue-handler",
                messageConfig.getStatemachineDestination().getMasterWorkSaved()
        ));
    }
}
