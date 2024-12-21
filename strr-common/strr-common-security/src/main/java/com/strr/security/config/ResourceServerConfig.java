package com.strr.security.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@AutoConfiguration
public class ResourceServerConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                // swagger-ui
                .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs", "/v3/api-docs", "/webjars/**").permitAll()
                .and()
                .securityMatcher("/**")
                .authorizeHttpRequests()
                .requestMatchers("/**").hasAuthority("SCOPE_web")
                .and()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }
}
