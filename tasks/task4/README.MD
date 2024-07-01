# Задание #4 JDBC + Migration + Docker – Date: 27 июня 2024 22:00
### Для работы с бд используется образ *postgres:16.3-alpine3.20* и *flyway* для миграции
### To build docker image run
```mvn clean package  -D docker.ignore=false```
### Docker image will be outputted to
*target\docker\repository*
### Env variables must be specified using file .env in project root with following variables:
-**DB_USER**  
-**DB_PASSWORD**  
-**DB_NAME**
### To run docker compose *(uses external network bridge with name task4-bridge-network)* 
```docker compose up -d```
### To create and run docker container from image *(with access to network)*
```docker run --rm --network task4-bridge-network repository/crud-app-image:1.0-SNAPSHOT```