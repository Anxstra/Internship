FROM maven:3.9.8-eclipse-temurin-17
LABEL maintainer="Anton Kozichev"
WORKDIR /catalog-migration
COPY pom.xml .
COPY catalog-migration ./catalog-migration/
WORKDIR catalog-migration

ENTRYPOINT mvn -v &&\
           mvn clean package -DskipTests &&\
           mvn liquibase:status -D liquibase.verbose=true &&\
           mvn liquibase:updateSQL -D liquibase.verbose=true -D liquibase.logging=debug &&\
           mvn liquibase:update -D liquibase.verbose=true -D liquibase.logging=debug