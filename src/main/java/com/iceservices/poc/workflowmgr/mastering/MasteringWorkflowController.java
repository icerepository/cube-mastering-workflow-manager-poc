package com.iceservices.poc.workflowmgr.mastering;

import com.iceservices.poc.workflowmgr.mastering.data.MasterRequest;
import com.iceservices.poc.workflowmgr.mastering.service.MasteringService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mastering-saga")
@RequiredArgsConstructor
public class MasteringWorkflowController {

    private final MasteringService masteringService;

    @PostMapping
    public ResponseEntity<String> masterWork(@Valid @RequestBody MasterRequest request) {
        masteringService.masterWork(request);
        return ResponseEntity.ok().body(request.requestId());
    }
}
