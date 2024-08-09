package com.anxstra.jwtfilterspringbootstarter.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.anxstra.jwt")
public record JwtConfigurationProperties(String secret) {

}
