package com.strr.base.config;

import com.strr.base.config.interceptor.PageInterceptor;
import com.strr.base.config.mybatis.AnnotationReflectorFactory;
import com.strr.base.config.mybatis.CrudMapperFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@MapperScan(basePackages = { "com.strr.*.mapper" }, factoryBean = CrudMapperFactoryBean.class)
public class MybatisConfig {
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return config -> {
            config.setReflectorFactory(new AnnotationReflectorFactory());
            // 开启下划线转驼峰
            config.setMapUnderscoreToCamelCase(true);
            // 分页插件
            config.addInterceptor(new PageInterceptor());
        };
    }
}
