package com.anxstra.order.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "com.anxstra.jedis")
public record JedisProperties(@DefaultValue("localhost") String host,
                              @DefaultValue("6379") Integer port) {

}
