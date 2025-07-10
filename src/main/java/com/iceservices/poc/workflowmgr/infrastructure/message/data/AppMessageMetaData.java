package com.iceservices.poc.workflowmgr.infrastructure.message.data;

import java.time.LocalDateTime;

public record AppMessageMetaData(String requestId, String from, String to, String subject, LocalDateTime creationTime) {
}
