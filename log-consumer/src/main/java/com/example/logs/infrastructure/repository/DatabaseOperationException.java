package com.example.logs.infrastructure.repository;

/**
 * Exceção customizada para erros relacionados ao banco de dados.
 */
public class DatabaseOperationException extends RuntimeException {
    public DatabaseOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}