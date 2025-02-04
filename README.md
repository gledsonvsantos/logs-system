# Sistema de Logs Distribuídos

Este é um sistema de logs distribuídos que consiste em dois microsserviços Spring Boot que trabalham em conjunto para gerar, processar e visualizar logs de aplicação, com foco em observabilidade e monitoramento.

## Arquitetura

O sistema é composto por:

### Microsserviços
- **Log Producer**: Gera logs simulados (INFO, ERROR, DEBUG) e envia para o Kafka
- **Log Consumer**: Consome logs do Kafka e armazena no PostgreSQL

### Infraestrutura
- **Apache Kafka**: Message broker para comunicação assíncrona
- **PostgreSQL**: Banco de dados para armazenamento dos logs
- **Observabilidade**:
  - ELK Stack (Elasticsearch, Logstash, Kibana) para agregação e visualização de logs
  - Prometheus + Grafana para métricas
  - Jaeger para distributed tracing
  - Kafka UI para monitoramento do Kafka

## Pré-requisitos

- Docker
- Docker Compose
- Java 17+
- Maven

## Configuração e Execução

### 1. Iniciar a Infraestrutura

```bash
# Inicia todos os serviços de infraestrutura
docker-compose up -d
```

### 2. Iniciar os Microsserviços

```bash
# Inicia o consumer
docker-compose -f docker-compose.consumer.yml up -d

# Inicia o producer
docker-compose -f docker-compose.producer.yml up -d
```

### 3. Verificar Status dos Serviços

```bash
# Lista todos os containers em execução
docker ps

# Verifica logs de um serviço específico
docker logs log-producer
docker logs log-consumer
```

## Acessando as Interfaces

### Monitoramento e Observabilidade

1. **Kafka UI**
   - URL: http://localhost:8080
   - Funcionalidades:
     - Visualização de tópicos
     - Monitoramento de producers/consumers
     - Métricas do cluster

2. **Kibana (Logs)**
   - URL: http://localhost:5601
   - Funcionalidades:
     - Visualização de logs agregados
     - Criação de dashboards
     - Análise de logs em tempo real

3. **Grafana (Métricas)**
   - URL: http://localhost:3000
   - Credenciais: admin/admin
   - Funcionalidades:
     - Dashboards de métricas
     - Alertas
     - Visualização de métricas JVM

4. **Prometheus**
   - URL: http://localhost:9090
   - Funcionalidades:
     - Coleta de métricas
     - Queries PromQL
     - Alertas

5. **Jaeger (Tracing)**
   - URL: http://localhost:16686
   - Funcionalidades:
     - Visualização de traces distribuídos
     - Análise de latência
     - Diagnóstico de problemas

## Estrutura do Projeto

```
logs-system/
├── docker-compose.yml          # Infraestrutura compartilhada
├── docker-compose.producer.yml # Configuração do producer
├── docker-compose.consumer.yml # Configuração do consumer
├── log-producer/              # Microsserviço producer
├── log-consumer/              # Microsserviço consumer
└── tmp/                       # Volumes persistentes
    ├── postgres-data/
    └── elasticsearch-data/
```

## Observabilidade

### 1. Logs (ELK Stack)
- Todos os logs dos microsserviços são enviados para o Elasticsearch
- Visualização através do Kibana
- Índices separados por aplicação e ambiente

### 2. Métricas (Prometheus + Grafana)
- Métricas JVM (heap, threads, gc)
- Métricas de negócio (logs gerados/processados)
- Métricas do Kafka (lag, throughput)
- Dashboards pré-configurados no Grafana

### 3. Tracing (OpenTelemetry + Jaeger)
- Rastreamento de requisições entre serviços usando OpenTelemetry
- Instrumentação automática do Spring MVC e Kafka
- Exportação de traces via OTLP para Jaeger
- Configurações principais:
  ```properties
  # OpenTelemetry
  management.tracing.sampling.probability=1.0
  otel.exporter.otlp.endpoint=http://localhost:4317
  otel.resource.attributes.service.name=${spring.application.name}
  otel.traces.exporter=otlp
  otel.metrics.exporter=otlp
  otel.logs.exporter=otlp
  otel.instrumentation.spring-webmvc.enabled=true
  otel.instrumentation.spring-kafka.enabled=true
  ```
- Visualização e análise de traces no Jaeger UI (http://localhost:16686)
- Identificação de gargalos e problemas de performance
- Correlação entre logs, métricas e traces

## Tópicos Kafka

O sistema utiliza os seguintes tópicos:

- `logs`: Tópico principal para envio de logs

Para criar o tópico manualmente:
```bash
docker exec -it kafka kafka-topics --create --topic logs --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```

## Troubleshooting

### Verificar Logs dos Serviços
```bash
# Logs do producer
docker logs -f log-producer

# Logs do consumer
docker logs -f log-consumer

# Logs do Kafka
docker logs -f kafka
```

### Reiniciar Serviços
```bash
# Reiniciar um serviço específico
docker-compose restart kafka

# Reiniciar toda a infraestrutura
docker-compose down
docker-compose up -d
```

## Segurança

- Elasticsearch: Segurança desabilitada para ambiente de desenvolvimento
- Grafana: Credenciais padrão (admin/admin)
- PostgreSQL: Credenciais configuradas no docker-compose

## Próximos Passos

1. Implementar autenticação e autorização
2. Adicionar mais métricas de negócio
3. Configurar alertas no Grafana
4. Implementar rate limiting
5. Adicionar testes de integração
6. Configurar CI/CD

## Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Crie um Pull Request 