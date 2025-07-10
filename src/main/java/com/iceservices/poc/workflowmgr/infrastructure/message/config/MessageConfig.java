package com.iceservices.poc.workflowmgr.infrastructure.message.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.messages")
@Data
public class MessageConfig {

    private AppMessageDestination destination = new AppMessageDestination();

    private StatemachineDestination statemachineDestination = new StatemachineDestination();

    @Data
    public static class AppMessageDestination {

        private String saveMasterWork;

        private String matchWork;

        private String mergeWork;
    }

    @Data
    public static class StatemachineDestination {

        private String masterWorkSaved;
    }
}
