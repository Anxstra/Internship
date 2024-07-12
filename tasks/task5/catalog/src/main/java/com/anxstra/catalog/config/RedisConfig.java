package com.anxstra.catalog.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

import static org.springframework.data.redis.serializer.RedisSerializer.json;

@Configuration
@EnableCaching
public class RedisConfig {

    private static final int CATEGORIES_TTL = 1;

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofDays(CATEGORIES_TTL))
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(json()))
                .disableKeyPrefix()
                .disableCachingNullValues();

        return RedisCacheManager.builder(connectionFactory)
                                .cacheDefaults(config)
                                .build();
    }
}
