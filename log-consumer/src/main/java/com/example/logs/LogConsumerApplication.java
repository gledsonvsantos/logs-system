package com.example.logs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.SpringVersion;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@Slf4j
@SpringBootApplication(scanBasePackages = "com.example.logs")
public class LogConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogConsumerApplication.class, args);
	}

	@Bean
	public CommandLineRunner logActiveProfiles(Environment environment) {
		return args -> {
			log.info("Aplicação inicializada com sucesso.");
			log.info("Spring Boot versão: {}, Spring Framework versão: {}", SpringVersion.getVersion(), SpringBootVersion.getVersion());
			log.info("Perfis ativos: {}", Arrays.toString(environment.getActiveProfiles()));
		};
	}
}