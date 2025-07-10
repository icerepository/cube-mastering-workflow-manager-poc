package com.iceservices.poc.workflowmgr.statemachine.utils;


import com.iceservices.poc.workflowmgr.statemachine.events.MasteringEvent;
import com.iceservices.poc.workflowmgr.statemachine.states.MasteringState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class DefaultStateUtil implements StateUtil {

    @Override
    public Action<MasteringState, MasteringEvent> triggerEventOnEntry(MasteringEvent event) {
        return context -> {
            log.info("triggering {} event next!\n\n", event);
            context.getStateMachine().sendEvent(Mono.just(
                    MessageBuilder
                            .withPayload(event)
                            .copyHeaders(context.getMessageHeaders())
                            .build())
            ).blockLast();
        };
    }
}
