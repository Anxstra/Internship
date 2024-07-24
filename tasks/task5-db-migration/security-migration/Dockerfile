FROM maven:3.9.8-eclipse-temurin-17
LABEL maintainer="Anton Kozichev"
WORKDIR /security-migration
COPY pom.xml .
COPY security-migration ./security-migration/
WORKDIR security-migration

ENTRYPOINT mvn -v &&\
           mvn clean package -DskipTests &&\
           mvn liquibase:status -D liquibase.verbose=true &&\
           mvn liquibase:updateSQL -D liquibase.verbose=true -D liquibase.logging=debug &&\
           mvn liquibase:update -D liquibase.verbose=true -D liquibase.logging=debug