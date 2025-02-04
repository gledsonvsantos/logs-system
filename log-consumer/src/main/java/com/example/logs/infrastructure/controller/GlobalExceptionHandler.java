package com.example.logs.infrastructure.controller;

import com.example.logs.infrastructure.repository.DatabaseOperationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * Trata DatabaseOperationException (erros relacionados ao banco de dados)
     */
    @ExceptionHandler(DatabaseOperationException.class)
    public ResponseEntity<Map<String, Object>> handleDatabaseOperationException(DatabaseOperationException ex) {
        log.error("Erro de operação no banco de dados: {}", ex.getMessage(), ex);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro no banco de dados", ex.getMessage());
    }

    /**
     * Trata IllegalArgumentException (erros de argumentos inválidos)
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.warn("Erro de argumento inválido: {}", ex.getMessage());
        return buildResponse(HttpStatus.BAD_REQUEST, "Argumento inválido", ex.getMessage());
    }

    /**
     * Trata MethodArgumentNotValidException (erros de validação)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        log.warn("Erro de validação: {}", ex.getBindingResult().getFieldError() != null
                ? ex.getBindingResult().getFieldError().getDefaultMessage()
                : "Erro desconhecido");

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Erro de validação");
        body.put("details", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Trata DataIntegrityViolationException (erros de integridade de banco de dados)
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        log.error("Erro de integridade de banco de dados: {}", ex.getMessage(), ex);
        return buildResponse(HttpStatus.CONFLICT, "Violação de integridade", "Um conflito ocorreu ao salvar os dados.");
    }

    /**
     * Trata Exception (erros genéricos não tratados)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        log.error("Erro inesperado: {}", ex.getMessage(), ex);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
    }

    /**
     * Utilitário para criar a estrutura da resposta de erro
     */
    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String error, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", status.value());
        body.put("error", error);
        body.put("message", message);
        body.put("timestamp", System.currentTimeMillis());
        return new ResponseEntity<>(body, status);
    }
}