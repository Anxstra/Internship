plugins {
    java
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.anxstra"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    mavenLocal()
}

val liquibaseVersion: String by project
val postgresVersion: String by project
val lombokVersion: String by project
val testcontainersVersion: String by project
val springdocVersion: String by project
val filterVersion: String by project
val jedisVersion: String by project

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdocVersion")
    implementation("com.anxstra:jwt-filter-spring-boot-starter:$filterVersion")
    implementation("redis.clients:jedis:$jedisVersion")
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    runtimeOnly("org.postgresql:postgresql:$postgresVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.liquibase:liquibase-core:$liquibaseVersion")
    testImplementation("org.testcontainers:junit-jupiter:$testcontainersVersion")
    testImplementation("org.testcontainers:postgresql:$testcontainersVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
