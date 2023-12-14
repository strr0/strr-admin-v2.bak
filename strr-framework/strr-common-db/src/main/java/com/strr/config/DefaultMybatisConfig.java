package com.strr.config;

import com.strr.config.mybatis.AnnotationReflectorFactory;
import org.apache.ibatis.session.SqlSessionFactory;

public class DefaultMybatisConfig {
    public DefaultMybatisConfig(SqlSessionFactory sqlSessionFactory) {
        sqlSessionFactory.getConfiguration().setReflectorFactory(new AnnotationReflectorFactory());
        // 开启下划线转驼峰
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
    }
}
