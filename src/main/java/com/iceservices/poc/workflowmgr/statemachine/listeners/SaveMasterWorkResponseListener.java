package com.iceservices.poc.workflowmgr.statemachine.listeners;


import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaveMasterWorkResponseListener {


    @SqsListener("masterWorkSavedQueue")
    public void saveMasterWorkHandler(String message) {
        //TODO fetch statemachine by request-id or work-id
        log.info("i will fetch the statemachine here and trigger the next event...: {}", message);
    }
}
