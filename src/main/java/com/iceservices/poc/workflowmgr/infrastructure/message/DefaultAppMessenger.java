package com.iceservices.poc.workflowmgr.infrastructure.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iceservices.poc.workflowmgr.infrastructure.message.data.AppMessage;
import io.awspring.cloud.sqs.operations.SendResult;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
@Slf4j
public class DefaultAppMessenger implements AppMessenger {

    private final SqsTemplate sqsTemplate;

    private final ObjectMapper objectMapper;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy HH:mm:ss");


    @Override
    public <T> String sendMessage(AppMessage<T> message) {
        SendResult<Object> result = sqsTemplate.send(
                to -> to.queue(message.metaData().to())
                        .payload(convertToString(message))
                        .header("request-id", message.metaData().requestId())
                        .header("from", message.metaData().from())
                        .header("to", message.metaData().to())
                        .header("subject", message.metaData().subject())
                        .header("created", getCreationTime(message))
                        .delaySeconds(2));
        log.info("Matched work Complete. result: {}\n\n", result);
        return result.messageId().toString();
    }

    private <T> String getCreationTime(AppMessage<T> message) {
        return message.metaData().creationTime().format(formatter);
    }

    private <T> String convertToString(AppMessage<T> appMessage) {
        try {
            return objectMapper.writeValueAsString(appMessage.message());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "No Message";
    }
}
