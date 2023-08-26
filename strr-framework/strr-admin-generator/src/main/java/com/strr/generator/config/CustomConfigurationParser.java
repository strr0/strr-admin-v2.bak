package com.strr.generator.config;

import org.mybatis.generator.config.*;
import org.yaml.snakeyaml.Yaml;

public class CustomConfigurationParser {
    public Configuration parseConfiguration(String filename) {
        Yaml yaml = new Yaml();
        CustomProperties properties = yaml.loadAs(this.getClass().getClassLoader().getResourceAsStream(filename), CustomProperties.class);
        return parseMyBatisGeneratorConfiguration(properties);
    }

    private Configuration parseMyBatisGeneratorConfiguration(CustomProperties properties) {
        Configuration config = new Configuration();
        parseContext(config, properties);
        return config;
    }

    /**
     * 上下文配置
     */
    private void parseContext(Configuration config, CustomProperties properties) {
        CustomContext context = new CustomContext(null);
        parseCommentGenerator(context);
        parseJdbcConnection(context, properties);
        parseJavaModelGenerator(context, properties);
        parseSqlMapGenerator(context, properties);
        parseTable(context, properties);
        config.addContext(context);
    }

    /**
     * 注释配置
     */
    private void parseCommentGenerator(CustomContext context) {
        CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
        commentGeneratorConfiguration.setConfigurationType("com.strr.generator.config.CustomCommentGenerator");
        context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);
    }

    /**
     * jdbc配置
     */
    private void parseJdbcConnection(CustomContext context, CustomProperties properties) {
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setConnectionURL(properties.getUrl());
        jdbcConnectionConfiguration.setDriverClass(properties.getDriver());
        jdbcConnectionConfiguration.setUserId(properties.getUser());
        jdbcConnectionConfiguration.setPassword(properties.getPassword());
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);
    }

    /**
     * java配置
     */
    private void parseJavaModelGenerator(CustomContext context, CustomProperties properties) {
        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetPackage(properties.getTargetPackage());
        javaModelGeneratorConfiguration.setTargetProject(properties.getTargetProject());
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
        context.setTargetPackage(properties.getTargetPackage());
        context.setTargetProject(properties.getTargetProject());
    }

    /**
     * xml配置
     */
    private void parseSqlMapGenerator(CustomContext context, CustomProperties properties) {
        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetProject(properties.getClientProject());
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);
        context.setClientProject(properties.getClientProject());
    }

    /**
     * table配置
     */
    private void parseTable(CustomContext context, CustomProperties properties) {
        for (String table : properties.getTables()) {
            TableConfiguration tableConfiguration = new TableConfiguration(context);
            tableConfiguration.setTableName(table);
            context.addTableConfiguration(tableConfiguration);
        }
    }
}
