package com.iceservices.poc.workflowmgr.mastering.service;

import com.iceservices.poc.workflowmgr.mastering.data.MasterRequest;
import com.iceservices.poc.workflowmgr.statemachine.events.MasteringEvent;
import com.iceservices.poc.workflowmgr.statemachine.states.MasteringState;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DefaultMasteringService implements MasteringService {

    private final StateMachineFactory<MasteringState, MasteringEvent> stateMachineFactory;

    @Async
    @Override
    public void masterWork(MasterRequest masterRequest) {
        StateMachine<MasteringState, MasteringEvent> stateMachine = stateMachineFactory.getStateMachine(masterRequest.requestId());
        stateMachine.sendEvent(Mono.just(
                MessageBuilder
                        .withPayload(MasteringEvent.SAVE_MASTER_COMMAND)
                        .setHeader("workId", masterRequest.workId())
                        .setHeader("requestId", masterRequest.requestId())
                        .build()
        )).blockLast();
    }
}
