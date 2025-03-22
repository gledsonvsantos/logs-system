package com.example.logs.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataLogRepository extends JpaRepository<LogEntity, Long> {
}