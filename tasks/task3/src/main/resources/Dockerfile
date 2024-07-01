FROM openjdk:17-jdk-alpine
ARG MAINTAINER_VAR
LABEL maintainer=$MAINTAINER_VAR
WORKDIR /app
COPY maven/hello-world-jar-with-dependencies.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]