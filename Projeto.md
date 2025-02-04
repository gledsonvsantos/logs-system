2. Sistema de Logs Distribuídos

   •	Descrição: Desenvolva uma aplicação que simula a coleta de logs de diferentes microsserviços.
   •	Fluxo:
   •	Vários producers simulam diferentes microsserviços gerando logs (INFO, ERROR, DEBUG).
   •	Os logs são enviados para um tópico específico no Kafka.
   •	Um serviço consumidor filtra e armazena os logs em um banco de dados.
   •	Um painel (pode ser simples com Spring Boot e Thymeleaf) exibe os logs filtrados por nível.
   •	Tecnologias: Spring Boot, Kafka, ElasticSearch ou um banco relacional.

1. Sistema de Logs Distribuídos (Versão Básica)

   •	Motivo: A lógica é simples, com apenas producers gerando logs e um único consumer armazenando em um banco de dados.
   •	Implementação:
   •	Configure um producer para enviar mensagens de log (INFO, ERROR, etc.) para um tópico no Kafka.
   •	Um único consumer consome os logs e os armazena no banco de dados.
   •	Opcional: Mostre os logs em uma API REST usando Spring Boot.

Aqui está como vamos estruturar o sistema:
•	Producer: Gera logs aleatórios e envia para o Kafka.
•	Consumer: Consome os logs do tópico e armazena no banco de dados.

docker-compose up -d

Criando um Tópico no Kafka
docker exec -it kafka kafka-topics --create --topic logs --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
Listar os Tópicos no Kafka
docker exec -it kafka kafka-topics --list --bootstrap-server localhost:9092

Reduzir os logs (kafka por exemplo)



Recomendação para o Sistema de Logs Distribuídos
1.	Logs: Configure ELK Stack para capturar os logs de todos os serviços.
2.	Métricas: Exponha métricas de cada microsserviço usando Actuator/Prometheus e visualize no Grafana.
3.	Tracing: Use OpenTelemetry com Jaeger para rastreamento distribuído.
4.	Kafka: Instale Kafka UI para monitorar tópicos e consumidores.

Como Escolher as Ferramentas
1.	Para Logs:
      •	Escolha ELK Stack para projetos maiores ou Grafana Loki para simplicidade.
2.	Para Métricas:
      •	Use Prometheus com Grafana para um setup robusto.
3.	Para Tracing:
      •	Comece com Zipkin para simplicidade ou Jaeger para setups mais avançados.
4.	Para Kafka:
      •	Use Kafka UI para monitorar tópicos e mensagens.
5.	Para Infraestrutura:
      •	Combine Node Exporter com Prometheus para monitoramento de servidores.

links:
kibana (logs): http://localhost:5601
prometheus: http://localhost:9090
grafana: http://localhost:3000
jaeger (tracing): http://localhost:16686
kafka ui: http://localhost:8080

foco em obseravabilidade e monitoramento dos microsserviços e kafka

seguir com os proximos passos e colocar no github

-Dspring.output.ansi.enabled=always

Criando um Tópico no Kafka
docker exec -it kafka kafka-topics --create --topic logs --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
Listar os Tópicos no Kafka
docker exec -it kafka kafka-topics --list --bootstrap-server localhost:9092

TODOS
adicionar testes unitários e integrados
melhorar arquitetura de cada microserviço (clean architecture) OK
adicionar mapper com mapstruct OK
adicionar swagger para documentação da API
adicionar observabilidade e monitoramento para todos os microsserviços
testar tracing entre os microsserviços
adicionar documentação
kubernetes ou add no docker-compose para debug local
oq eh devcontainer?
recomendacao para instalar em produção
onde fazer o deploy incluindo todas as ferramentas


plugins {
	id 'java'
	id 'org.springframework.boot' version '3.2.2'
	id 'io.spring.dependency-management' version '1.1.4'
	id "io.freefair.lombok" version "8.11"
}

group = 'com.example'
version = '1.0.0'
sourceCompatibility = '17'

configurations {
	compileOnly {
		extendsFrom annotationProcessor
	}
}

repositories {
	mavenCentral()
}

ext {
	set('springCloudVersion', "2023.0.0")
	set('opentelemetryVersion', "1.32.0")
}

dependencies {
	// Spring Boot
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.kafka:spring-kafka'

	// Database
	runtimeOnly 'org.postgresql:postgresql'

	// Observability
	implementation 'org.springframework.boot:spring-boot-starter-actuator'
	implementation 'io.micrometer:micrometer-registry-prometheus'
	implementation "io.opentelemetry:opentelemetry-api:${opentelemetryVersion}"
	implementation "io.opentelemetry:opentelemetry-sdk:${opentelemetryVersion}"
	implementation "io.opentelemetry:opentelemetry-exporter-otlp:${opentelemetryVersion}"

	// Lombok
	compileOnly 'org.projectlombok:lombok'
	annotationProcessor 'org.projectlombok:lombok'

	// Test
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.springframework.kafka:spring-kafka-test'
}

dependencyManagement {
	imports {
		mavenBom "org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"
	}
}

tasks.named('test') {
	useJUnitPlatform()
}
