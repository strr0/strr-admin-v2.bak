package com.strr.base.config.mybatis;

import com.strr.base.annotation.Column;
import com.strr.base.util.ModelUtil;
import org.apache.ibatis.reflection.Reflector;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/**
 * 注解反射器
 */
public class AnnotationReflector extends Reflector {
    private final Map<String, String> annotationPropertyMap = new HashMap<>();

    public AnnotationReflector(Class<?> clazz) {
        super(clazz);
        this.addAnnotationField(clazz);
    }

    private void addAnnotationField(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 下划线转驼峰
            String column = ModelUtil.getColumnOptional(field).map(Column::value).map(value -> value.replace("_", ""))
                    .orElse(field.getName());
            annotationPropertyMap.put(column.toUpperCase(Locale.ENGLISH), field.getName());
        }
    }

    @Override
    public String findPropertyName(String name) {
        return Optional.ofNullable(annotationPropertyMap.get(name.toUpperCase(Locale.ENGLISH)))
                .orElse(super.findPropertyName(name));
    }
}
