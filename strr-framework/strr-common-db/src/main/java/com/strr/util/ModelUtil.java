package com.strr.util;

import com.strr.base.annotation.SColumn;
import com.strr.base.annotation.SId;
import com.strr.base.annotation.STable;
import com.strr.base.exception.KeyNotFoundException;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * 实体类注解工具
 */
public class ModelUtil {
    /**
     * 获取表名注解
     * @param clazz
     * @return
     */
    public static Optional<STable> getSTable(Class<?> clazz) {
        return Optional.ofNullable(clazz.getAnnotation(STable.class));
    }

    /**
     * 获取表名
     * @param clazz
     * @return
     */
    public static String getTable(Class<?> clazz) {
        return getSTable(clazz).map(STable::value).orElse(clazz.getSimpleName());
    }

    /**
     * 获取字段名注解
     * @param field
     * @return
     */
    public static Optional<SColumn> getSColumn(Field field) {
        return Optional.ofNullable(field.getAnnotation(SColumn.class));
    }

    /**
     * 获取字段名
     * @param field
     * @return
     */
    public static String getColumn(Field field) {
        return getSColumn(field).map(SColumn::value).filter(StringUtils::hasLength).orElse(field.getName());
    }

    /**
     * 模糊查询
     * @param field
     * @return
     */
    public static boolean isFuzzy(Field field) {
        return getSColumn(field).map(SColumn::fuzzy).orElse(false);
    }

    /**
     * 是否主键
     * @param field
     * @return
     */
    public static boolean isKey(Field field) {
        return field.isAnnotationPresent(SId.class);
    }

    /**
     * 是否有主键
     * @param fields
     * @return
     */
    public static boolean hasKey(Field[] fields) {
        for (Field field : fields) {
            if (field.isAnnotationPresent(SId.class)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取主键
     * @param fields
     * @return
     */
    public static Field getKey(Field[] fields) throws KeyNotFoundException {
        for (Field field : fields) {
            if (field.isAnnotationPresent(SId.class)) {
                return field;
            }
        }
        throw new KeyNotFoundException();
    }
}
