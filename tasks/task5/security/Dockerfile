#making layers
FROM eclipse-temurin:17-jre-alpine AS layers
ARG JAR_FILE
WORKDIR /build
COPY $JAR_FILE security-app.jar
RUN java -Djarmode=tools -jar security-app.jar extract --layers --launcher --destination extracted

#booting app
FROM eclipse-temurin:17-jre-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
VOLUME /tmp
WORKDIR /application
COPY --from=layers /build/extracted/dependencies .
COPY --from=layers /build/extracted/spring-boot-loader .
COPY --from=layers /build/extracted/snapshot-dependencies .
COPY --from=layers /build/extracted/application .
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]