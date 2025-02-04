package com.example.logs.infrastructure.config;

import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class TracingConfig {

    @Bean
    public OtlpGrpcSpanExporter otlpHttpSpanExporter(@Value("${management.otlp.tracing.endpoint}") String url) {
        log.info("Configurando OTLP Tracing com URL: {}", url);
        return OtlpGrpcSpanExporter.builder().setEndpoint(url).build();
    }
}