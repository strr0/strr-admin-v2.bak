package com.strr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class WebSecurityConfig {
    @Bean
    public SecurityWebFilterChain defaultSecurityWebFilterChain(ServerHttpSecurity http) throws Exception {
        http.authorizeExchange()
                .anyExchange().authenticated()
                .and()
                .oauth2Login()
                .and()
                .csrf().disable();
        return http.build();
    }
}
