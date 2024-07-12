package com.anxstra.order;

import com.anxstra.order.config.JedisProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
@EnableConfigurationProperties({JedisProperties.class})
public class Order {

    public static void main(String[] args) {
        SpringApplication.run(Order.class, args);
    }

}