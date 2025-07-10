package com.iceservices.poc.workflowmgr.statemachine;

import com.iceservices.poc.workflowmgr.statemachine.events.MasteringEvent;
import com.iceservices.poc.workflowmgr.statemachine.states.MasteringState;
import com.iceservices.poc.workflowmgr.statemachine.utils.StateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.time.Duration;
import java.util.EnumSet;

import static com.iceservices.poc.workflowmgr.statemachine.events.MasteringEvent.MATCH_WORK_COMMAND;
import static com.iceservices.poc.workflowmgr.statemachine.events.MasteringEvent.MERGE_WORK_COMMAND;
import static com.iceservices.poc.workflowmgr.statemachine.events.MasteringEvent.SAVE_MASTER_COMMAND;
import static com.iceservices.poc.workflowmgr.statemachine.states.MasteringState.INITIAL;
import static com.iceservices.poc.workflowmgr.statemachine.states.MasteringState.MATCHED_MASTER_WORK;
import static com.iceservices.poc.workflowmgr.statemachine.states.MasteringState.SAVED_MASTER_WORK;
import static com.iceservices.poc.workflowmgr.utils.UtilityFunctions.tryToSleep;


@Slf4j
@Configuration
@EnableStateMachineFactory
@RequiredArgsConstructor
public class StatemachineFactoryConfig extends EnumStateMachineConfigurerAdapter<MasteringState, MasteringEvent> {

    private final StateUtil stateUtil;

    @Override
    public void configure(StateMachineConfigurationConfigurer<MasteringState, MasteringEvent> config) throws Exception {
        config.withConfiguration()
              .autoStartup(true)
              .listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<MasteringState, MasteringEvent> states) throws Exception {
        states.withStates()
              .initial(INITIAL)
              .states(EnumSet.allOf(MasteringState.class))
              .stateEntry(SAVED_MASTER_WORK, stateUtil.triggerEventOnEntry(MATCH_WORK_COMMAND))
              .stateEntry(MasteringState.MATCHED_MASTER_WORK, stateUtil.triggerEventOnEntry(MERGE_WORK_COMMAND));
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

    @Override
    public void configure(StateMachineTransitionConfigurer<MasteringState, MasteringEvent> transitions) throws Exception {
        // @formatter:off
        transitions
                .withExternal()
                    .source(INITIAL)
                    .event(SAVE_MASTER_COMMAND)
                    .action(saveMasterAction())
                    .target(SAVED_MASTER_WORK)
                    .and()
                .withExternal()
                    .source(SAVED_MASTER_WORK)
                    .event(MATCH_WORK_COMMAND)
                    .action(matchWorksAction())
                    .target(MATCHED_MASTER_WORK)
                    .and()
                .withExternal()
                    .source(MasteringState.MATCHED_MASTER_WORK)
                    .event(MERGE_WORK_COMMAND)
                    .action(mergeWorksAction())
                    .target(MasteringState.MERGED_MASTER_WORK);
        // @formatter:on
    }

    @Bean
    Action<MasteringState, MasteringEvent> saveMasterAction() {
        return context -> {
            log.info("...Simulating save-work-master with context {}", context);
            tryToSleep(Duration.ofSeconds(3));
            log.info("...work will be assumed created for simplicity");
        };
    }

    @Bean
    Action<MasteringState, MasteringEvent> matchWorksAction() {
        return context -> {
            log.info("...Simulating match-works with context {}", context);
            tryToSleep(Duration.ofSeconds(2));
            log.info("...work matched!");
        };
    }

    @Bean
    Action<MasteringState, MasteringEvent> mergeWorksAction() {
        return context -> {
            log.info("...Simulating merge-works with context {}", context);
            tryToSleep(Duration.ofSeconds(2));
            log.info("...work merged!!");
        };
    }
}
