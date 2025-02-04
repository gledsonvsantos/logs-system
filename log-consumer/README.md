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
