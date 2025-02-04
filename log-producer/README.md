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


