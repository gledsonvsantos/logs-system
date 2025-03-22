package com.example.logs.domain;

import java.util.List;

public interface LogRepository {
    void save(Log log);
    List<Log> findAll();
}