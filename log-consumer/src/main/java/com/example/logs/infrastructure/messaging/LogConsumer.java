package com.example.logs.infrastructure.messaging;

import com.example.logs.application.usecase.SaveLogUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LogConsumer {

    private final SaveLogUseCase saveLogUseCase;

    @KafkaListener(topics = "logs", groupId = "log-consumers")
    public void consume(String message) {
        log.info("[KAFKA] Mensagem recebida do tópico: {}", message);
        saveLogUseCase.saveLog(message);
    }
}