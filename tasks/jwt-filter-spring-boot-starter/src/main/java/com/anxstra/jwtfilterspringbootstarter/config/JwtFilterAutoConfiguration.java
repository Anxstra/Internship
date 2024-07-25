package com.anxstra.jwtfilterspringbootstarter.config;

import com.anxstra.jwtfilterspringbootstarter.BaseJwtService;
import com.anxstra.jwtfilterspringbootstarter.JwtAuthFilter;
import com.anxstra.jwtfilterspringbootstarter.config.properties.JwtConfigurationProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties({JwtConfigurationProperties.class})
public class JwtFilterAutoConfiguration {

    @Bean
    public BaseJwtService baseJwtService(JwtConfigurationProperties jwtConfigurationProperties) {
        return new BaseJwtService(jwtConfigurationProperties);
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter(BaseJwtService jwtService) {
        return new JwtAuthFilter(jwtService);
    }
}
