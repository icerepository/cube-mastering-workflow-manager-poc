package com.iceservices.poc.workflowmgr.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@UtilityClass
@Slf4j
public class UtilityFunctions {

    public static void tryToSleep(Duration duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            log.error("problem sleeping", e);
            Thread.currentThread().interrupt();
        }
    }
}
