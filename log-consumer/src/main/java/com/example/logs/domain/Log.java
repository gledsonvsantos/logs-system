package com.example.logs.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Log {
    private Long id;
    private String message;

    public Log(String message) {
        this.message = message;
    }
}