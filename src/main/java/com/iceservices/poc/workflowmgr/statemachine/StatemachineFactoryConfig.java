package com.iceservices.poc.workflowmgr.statemachine;

import com.iceservices.poc.workflowmgr.statemachine.events.MasteringEvent;
import com.iceservices.poc.workflowmgr.statemachine.states.MasteringState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;


@Slf4j
@Configuration
@EnableStateMachineFactory
@RequiredArgsConstructor
public class StatemachineFactoryConfig extends EnumStateMachineConfigurerAdapter<MasteringState, MasteringEvent> {


    @Override
    public void configure(StateMachineConfigurationConfigurer<MasteringState, MasteringEvent> config) throws Exception {
        config.withConfiguration()
              .autoStartup(true)
              .listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<MasteringState, MasteringEvent> states) throws Exception {
        states.withStates()
              .initial(MasteringState.INITIAL)
              .states(EnumSet.allOf(MasteringState.class));
    }

    @Bean
    public StateMachineListener<MasteringState, MasteringEvent> listener() {
        return new StateMachineListenerAdapter<>() {
            @Override
            public void stateChanged(State<MasteringState, MasteringEvent> from,
                                     State<MasteringState, MasteringEvent> to) {
                log.info("State change to {}", to.getId());
            }

            @Override
            public void eventNotAccepted(Message<MasteringEvent> event) {
                log.info("Event not accepted {}", event);
            }
        };
    }
}
