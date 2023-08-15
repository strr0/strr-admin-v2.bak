package com.strr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ResourceServerConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                // swagger-ui
                .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs", "/v3/api-docs", "/webjars/**").permitAll()
                .and()
                .securityMatcher("/admin/**")
                .authorizeHttpRequests()
                .requestMatchers("/admin/**").hasAuthority("SCOPE_web")
                .and()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }
}
