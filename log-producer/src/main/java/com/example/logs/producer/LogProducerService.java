package com.example.logs.producer;

import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Observed(name = "log.producer.service", contextualName = "log-producer-service")
public class LogProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public LogProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Observed(name = "send.log", contextualName = "send-log")
    public void sendLog(String message) {
        log.info("Enviando log: {}", message);
        kafkaTemplate.send("logs", message);
        log.debug("Log enviado com sucesso: {}", message);
    }
}
