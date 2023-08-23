package com.strr.config.mybatis;

import com.strr.base.exception.BuilderException;
import com.strr.base.exception.KeyNotFoundException;
import com.strr.util.EntityUtil;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.RawSqlSource;
import org.apache.ibatis.scripting.xmltags.*;
import org.apache.ibatis.session.Configuration;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CrudSqlSource {
    private Configuration configuration;
    private Class<?> clazz;
    private Field[] fields;
    private String table;
    private SqlNode applyWhere;

    private CrudSqlSource() {}

    public SqlSource countByParamSqlSource() {
        SqlNode sqlNode = countSqlNode();
        return new DynamicSqlSource(configuration, new MixedSqlNode(Arrays.asList(sqlNode, applyWhere)));
    }

    public SqlSource listByParamSqlSource() {
        SqlNode sqlNode = listSqlNode();
        return new DynamicSqlSource(configuration, new MixedSqlNode(Arrays.asList(sqlNode, applyWhere)));
    }

    public SqlSource saveSqlSource() {
        SqlNode sqlNode = saveSqlNode();
        return new RawSqlSource(configuration, sqlNode, clazz);
    }

    public SqlSource updateSqlSource() throws BuilderException {
        SqlNode updateSqlNode = updateSqlNode();
        SqlNode updateSetSqlNode = updateSetSqlNode();
        SqlNode updateWhereSqlNode = updateWhereSqlNode();
        return new DynamicSqlSource(configuration, new MixedSqlNode(Arrays.asList(updateSqlNode, updateSetSqlNode, updateWhereSqlNode)));
    }

    public SqlSource removeSqlSource() throws BuilderException {
        SqlNode sqlNode = removeSqlNode();
        return new RawSqlSource(configuration, sqlNode, clazz);
    }

    public SqlSource getSqlSource() throws BuilderException {
        SqlNode sqlNode = getSqlNode();
        return new RawSqlSource(configuration, sqlNode, clazz);
    }

    private SqlNode countSqlNode() {
        SQL sql = new SQL();
        sql.SELECT("count(1)");
        sql.FROM(table);
        return new StaticTextSqlNode(sql.toString());
    }

    private SqlNode listSqlNode() {
        SQL sql = new SQL();
        for (Field field : fields) {
            sql.SELECT(EntityUtil.getColumn(field));
        }
        sql.FROM(table);
        return new StaticTextSqlNode(sql.toString());
    }

    private SqlNode saveSqlNode() {
        SQL sql = new SQL();
        sql.INSERT_INTO(table);
        for (Field field : fields) {
            if (EntityUtil.isKey(field)) {
                continue;
            }
            sql.VALUES(EntityUtil.getColumn(field), String.format("#{%s}", field.getName()));
        }
        return new StaticTextSqlNode(sql.toString());
    }

    private SqlNode updateSqlNode() {
        return new StaticTextSqlNode(String.format("UPDATE %s", table));
    }

    private SqlNode updateSetSqlNode() {
        List<SqlNode> ifNodes = new ArrayList<>();
        for (Field field : fields) {
            if (EntityUtil.isKey(field)) {
                continue;
            }
            String property = field.getName();
            ifNodes.add(new IfSqlNode(new TextSqlNode(String.format("%s = #{%s},", EntityUtil.getColumn(field), property)),
                    String.format("%s != null && %s != ''", property, property)));
        }
        return new TrimSqlNode(configuration, new MixedSqlNode(ifNodes), "SET", null, null, ",");
    }

    private SqlNode updateWhereSqlNode() throws KeyNotFoundException {
        Field key = EntityUtil.getKey(fields);
        String keyColumn = EntityUtil.getColumn(key);
        String keyProperty = key.getName();
        return new StaticTextSqlNode(String.format("WHERE %s = #{%s}", keyColumn, keyProperty));
    }

    private SqlNode removeSqlNode() throws KeyNotFoundException {
        Field key = EntityUtil.getKey(fields);
        String keyColumn = EntityUtil.getColumn(key);
        String keyProperty = key.getName();
        SQL sql = new SQL();
        sql.DELETE_FROM(table);
        sql.WHERE(String.format("%s = #{%s}", keyColumn, keyProperty));
        return new StaticTextSqlNode(sql.toString());
    }

    private SqlNode getSqlNode() throws KeyNotFoundException {
        Field key = EntityUtil.getKey(fields);
        String keyColumn = EntityUtil.getColumn(key);
        String keyProperty = key.getName();
        SQL sql = new SQL();
        for (Field field : fields) {
            sql.SELECT(EntityUtil.getColumn(field));
        }
        sql.FROM(table);
        sql.WHERE(String.format("%s = #{%s}", keyColumn, keyProperty));
        return new StaticTextSqlNode(sql.toString());
    }

    public static class Builder {
        private final CrudSqlSource crudSqlSource = new CrudSqlSource();

        public Builder(Configuration configuration, Class<?> clazz) {
            this.crudSqlSource.configuration = configuration;
            this.crudSqlSource.clazz = clazz;
        }

        private void preBuild() {
            Field[] fields = this.crudSqlSource.clazz.getDeclaredFields();
            this.crudSqlSource.fields = fields;
            // 表名
            this.crudSqlSource.table = EntityUtil.getTable(this.crudSqlSource.clazz);
            // 查询条件
            List<SqlNode> ifNodes = new ArrayList<>();
            for (Field field : fields) {
                String property = field.getName();
                String column = EntityUtil.getColumn(field);
                String condition = EntityUtil.isFuzzy(field) ? String.format(" and %s like \"%%\"#{%s}\"%%\"", column, property) :
                        String.format(" and %s = #{%s} ", column, property);
                ifNodes.add(new IfSqlNode(new TextSqlNode(condition), String.format("%s != null && %s != ''", property, property)));
            }
            this.crudSqlSource.applyWhere = new WhereSqlNode(this.crudSqlSource.configuration, new MixedSqlNode(ifNodes));
        }

        public CrudSqlSource build() {
            this.preBuild();
            return crudSqlSource;
        }
    }
}
