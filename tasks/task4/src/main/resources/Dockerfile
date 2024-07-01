FROM openjdk:17-jdk-alpine
ARG MAINTAINER_VAR
LABEL maintainer=$MAINTAINER_VAR
WORKDIR /app
COPY maven/crud-app-jar-with-dependencies.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]