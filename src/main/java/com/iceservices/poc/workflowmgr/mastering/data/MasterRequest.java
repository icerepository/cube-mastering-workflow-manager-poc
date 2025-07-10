package com.iceservices.poc.workflowmgr.mastering.data;

import jakarta.validation.constraints.NotBlank;

public record MasterRequest(@NotBlank String workId, @NotBlank String requestId) {
}
