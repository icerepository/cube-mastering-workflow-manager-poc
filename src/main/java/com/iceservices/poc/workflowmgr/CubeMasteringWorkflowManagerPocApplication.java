package com.iceservices.poc.workflowmgr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class CubeMasteringWorkflowManagerPocApplication {

    public static void main(String[] args) {
        SpringApplication.run(CubeMasteringWorkflowManagerPocApplication.class, args);
    }

}
