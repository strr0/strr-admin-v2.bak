package com.strr.feign.config;

import com.strr.feign.interceptor.TokenInterceptor;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class FeignConfig {
    @Bean
    public RequestInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }
}
