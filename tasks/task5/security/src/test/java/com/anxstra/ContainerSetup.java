package com.anxstra;

import com.redis.testcontainers.RedisContainer;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ContainerSetup {

    private static final String POSTGRES_IMAGE = "postgres:16.3-alpine3.20";

    private static final String REDIS_IMAGE = "redis:7.4-rc-alpine";

    private static final String DB_NAME = "security-test";

    private static final String USERNAME = "test";

    private static final String PASSWORD = "test";

    private static final String URL_ENV_NAME = "DB_URL";

    private static final String USER_ENV_NAME = "DB_USER";

    private static final String PASSWORD_ENV_NAME = "DB_PASSWORD";

    private static final String PORT_BINDING = "15432:5432";

    @SuppressWarnings("resource")
    @Container
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(DockerImageName.parse(POSTGRES_IMAGE))
            .withDatabaseName(DB_NAME)
            .withUsername(USERNAME)
            .withPassword(PASSWORD);

    @Container
    private static final RedisContainer redisContainer = new RedisContainer(DockerImageName.parse(REDIS_IMAGE));

    static {
        postgresContainer.setPortBindings(List.of(PORT_BINDING));
    }

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private SpringLiquibase liquibase;

    @DynamicPropertySource
    static void datasourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
    }

    @DynamicPropertySource
    static void redisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", redisContainer::getHost);
        registry.add("spring.data.redis.port", redisContainer::getFirstMappedPort);
    }

    @BeforeAll
    public static void initialSetup() {
        postgresContainer.start();

        Map<String, String> env = new HashMap<>();
        env.put(URL_ENV_NAME, postgresContainer.getJdbcUrl());
        env.put(USER_ENV_NAME, postgresContainer.getUsername());
        env.put(PASSWORD_ENV_NAME, postgresContainer.getPassword());

        postgresContainer.withEnv(env);
    }

    @AfterAll
    public static void cleanup() {
        postgresContainer.stop();
        postgresContainer.close();
    }

    @BeforeEach
    public void setup() throws LiquibaseException {
        liquibase.afterPropertiesSet();
    }
}
