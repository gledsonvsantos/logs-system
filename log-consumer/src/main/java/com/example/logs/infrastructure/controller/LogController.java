package com.example.logs.infrastructure.controller;

import com.example.logs.application.usecase.GetLogsUseCase;
import com.example.logs.infrastructure.dto.LogDTO;
import com.example.logs.infrastructure.mapper.LogMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/logs")
public class LogController {

    private final GetLogsUseCase getLogsUseCase;
    private final LogMapper logMapper;

    @GetMapping
    public ResponseEntity<List<LogDTO>> getLogs() {
        log.info("Recebendo requisição para buscar logs.");
        List<LogDTO> logs = getLogsUseCase.getAllLogs().stream()
                .map(logMapper::toDto)
                .collect(Collectors.toList());
        log.info("Retornando {} logs para o cliente.", logs.size());
        return ResponseEntity.ok(logs);
    }
}