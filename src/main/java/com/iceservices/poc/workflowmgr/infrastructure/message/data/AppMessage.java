package com.iceservices.poc.workflowmgr.infrastructure.message.data;

import java.util.UUID;

import static java.time.LocalDateTime.now;

public record AppMessage<T>(T message, AppMessageMetaData metaData) {

    public AppMessage(T message, String to, String from) {
        this(message, new AppMessageMetaData(
                UUID.randomUUID().toString(),
                from,
                to,
                "no-subject",
                now()
        ));
    }
}
