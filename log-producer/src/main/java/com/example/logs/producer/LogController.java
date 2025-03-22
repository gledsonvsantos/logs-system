package com.example.logs.producer;

import io.micrometer.observation.annotation.Observed;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logs")
@Observed(name = "log.controller", contextualName = "log-controller")
public class LogController {

    private final LogProducerService logProducerService;

    public LogController(LogProducerService logProducerService) {
        this.logProducerService = logProducerService;
    }

    @GetMapping
    @Observed(name = "send.log.endpoint", contextualName = "send-log-endpoint")
    public ResponseEntity<String> sendLog() {
        logProducerService.sendLog("Log gerado via GET");
        return ResponseEntity.ok("Log sent!");
    }

    @PostMapping
    @Observed(name = "send.log.endpoint", contextualName = "send-log-endpoint")
    public ResponseEntity<String> sendLog(@RequestBody LogDTO logDTO) {
        logProducerService.sendLog(logDTO.getMessage());
        return ResponseEntity.ok("Log sent!");
    }
}