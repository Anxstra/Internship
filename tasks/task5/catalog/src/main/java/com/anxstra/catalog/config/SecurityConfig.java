package com.anxstra.catalog.config;

import com.anxstra.jwtfilterspringbootstarter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.anxstra.catalog.constants.PathConstants.CATEGORIES_PATH;
import static com.anxstra.catalog.constants.PathConstants.CATEGORY_PATH;
import static com.anxstra.catalog.constants.PathConstants.DESCRIPTIONS_PATH;
import static com.anxstra.catalog.constants.PathConstants.DESCRIPTION_PATH;
import static com.anxstra.catalog.constants.PathConstants.PRODUCTS_PATH;
import static com.anxstra.catalog.constants.PathConstants.PRODUCT_PATH;
import static com.anxstra.catalog.constants.PathConstants.SWAGGER_DOCS_PATH;
import static com.anxstra.catalog.constants.PathConstants.SWAGGER_UI_PATH;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.csrf(AbstractHttpConfigurer::disable)
                   .authorizeHttpRequests(matcher -> {
                       matcher.requestMatchers(SWAGGER_UI_PATH, SWAGGER_DOCS_PATH).permitAll();
                       matcher.requestMatchers(HttpMethod.GET,
                               PRODUCTS_PATH, PRODUCT_PATH,
                               CATEGORIES_PATH, CATEGORY_PATH,
                               DESCRIPTIONS_PATH, DESCRIPTION_PATH).permitAll();
                       matcher.anyRequest().authenticated();
                   })
                   .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.NEVER))
                   .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                   .build();
    }
}
