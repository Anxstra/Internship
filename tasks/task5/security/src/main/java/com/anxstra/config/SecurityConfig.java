package com.anxstra.config;

import com.anxstra.jwtfilterspringbootstarter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.anxstra.constants.PathConstants.LOGIN_PATH;
import static com.anxstra.constants.PathConstants.OAUTH_PATH;
import static com.anxstra.constants.PathConstants.REGISTER_PATH;
import static com.anxstra.constants.PathConstants.SWAGGER_DOCS_PATH;
import static com.anxstra.constants.PathConstants.SWAGGER_UI_PATH;

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
                       matcher.requestMatchers(REGISTER_PATH, SWAGGER_UI_PATH, SWAGGER_DOCS_PATH,
                               LOGIN_PATH, OAUTH_PATH).permitAll();
                       matcher.anyRequest().authenticated();
                   })
                   .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.NEVER))
                   .oauth2Login(Customizer.withDefaults())
                   .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                   .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
