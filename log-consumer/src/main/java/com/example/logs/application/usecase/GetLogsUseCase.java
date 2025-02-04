package com.example.logs.application.usecase;

import com.example.logs.domain.Log;
import com.example.logs.domain.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class GetLogsUseCase {

    private final LogRepository logRepository;

    public List<Log> getAllLogs() {
        log.info("Buscando todos os logs.");
        return logRepository.findAll();
    }
}