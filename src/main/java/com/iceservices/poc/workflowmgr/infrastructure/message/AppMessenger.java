package com.iceservices.poc.workflowmgr.infrastructure.message;

import com.iceservices.poc.workflowmgr.infrastructure.message.data.AppMessage;

public interface AppMessenger {

    <T> String sendMessage(AppMessage<T> message);
}
