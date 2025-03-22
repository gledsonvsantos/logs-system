package com.example.logs.application.usecase;

import com.example.logs.domain.Log;
import com.example.logs.domain.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class SaveLogUseCase {

    private final LogRepository logRepository;

    public void saveLog(String message) {
        log.debug("Validando mensagem: {}", message);
        if (message == null || message.isBlank()) {
            log.warn("Mensagem inválida recebida: {}", message);
            throw new IllegalArgumentException("Mensagem não pode ser nula ou vazia.");
        }

        log.info("Salvando log com mensagem: {}", message);
        logRepository.save(new Log(message));
        log.info("Log salvo com sucesso.");
    }
}