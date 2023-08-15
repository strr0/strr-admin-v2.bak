/*
 Navicat Premium Data Transfer
 Source Server         : mysql-local
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : strr_admin
 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001
 Date: 29/07/2023 16:46:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_authority`;
CREATE TABLE `sys_authority`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求路径',
  `path` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `color` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '颜色',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `parent_id` int NULL DEFAULT NULL COMMENT '父菜单',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '按钮类型',
  `seq` int NULL DEFAULT NULL COMMENT '排序',
  `sys` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '默认',
  `creator` int NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updator` int NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_authority
-- ----------------------------
INSERT INTO `sys_authority` VALUES (1, NULL, '/home', 'Home', '后台管理', NULL, NULL, 0, 0, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (2, '', '/admin', 'Admin', '系统管理', NULL, NULL, 1, 0, NULL, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_authority` VALUES (3, '/admin/user', '/user', 'User', '用户管理', NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_authority` VALUES (4, '/admin/role', '/role', 'Role', '角色管理', NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_authority` VALUES (5, '/admin/authority', '/authority', 'Authority', '权限管理', NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_authority` VALUES (6, NULL, NULL, 'show', '查看', 'primary', 'el-icon-view', 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (7, NULL, NULL, 'add', '添加', 'success', 'el-icon-plus', 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (8, NULL, NULL, 'edit', '修改', 'warning', 'el-icon-edit', 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (9, NULL, NULL, 'del', '删除', 'danger', 'el-icon-delete', 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (10, NULL, NULL, 'alloc', '权限', 'primary', 'el-icon-view', 4, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (11, NULL, NULL, 'add', '添加', 'success', 'el-icon-plus', 4, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (12, NULL, NULL, 'edit', '修改', 'warning', 'el-icon-edit', 4, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (13, NULL, NULL, 'del', '删除', 'danger', 'el-icon-delete', 4, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (14, NULL, NULL, 'show', '查看', 'primary', 'el-icon-view', 5, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (15, NULL, NULL, 'add', '添加', 'success', 'el-icon-plus', 5, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (16, NULL, NULL, 'edit', '修改', 'warning', 'el-icon-edit', 5, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (17, NULL, NULL, 'del', '删除', 'danger', 'el-icon-delete', 5, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (18, '/admin/properties', '/properties', 'Properties', '配置管理', NULL, NULL, 1, 1, 1, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (19, '/admin/properties/components/show', '/propertiesShow', 'show', '配置详情', 'primary', 'el-icon-view', 18, '3', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (20, NULL, NULL, 'add', '添加', 'success', 'el-icon-plus', 18, '2', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (21, NULL, NULL, 'del', '删除', 'danger', 'el-icon-delete', 18, '2', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_authority` VALUES (22, '/admin/server', '/server', 'Server', '服务管理', NULL, NULL, 1, '1', 1, '1', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_rel_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_rel_role_authority`;
CREATE TABLE `sys_rel_role_authority`  (
  `rid` int NULL DEFAULT NULL COMMENT '角色id',
  `aid` int NULL DEFAULT NULL COMMENT '权限id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_rel_role_authority
-- ----------------------------
INSERT INTO `sys_rel_role_authority` VALUES (1, 1);
INSERT INTO `sys_rel_role_authority` VALUES (1, 2);
INSERT INTO `sys_rel_role_authority` VALUES (1, 3);
INSERT INTO `sys_rel_role_authority` VALUES (1, 4);
INSERT INTO `sys_rel_role_authority` VALUES (1, 5);
INSERT INTO `sys_rel_role_authority` VALUES (1, 6);
INSERT INTO `sys_rel_role_authority` VALUES (1, 7);
INSERT INTO `sys_rel_role_authority` VALUES (1, 8);
INSERT INTO `sys_rel_role_authority` VALUES (1, 9);
INSERT INTO `sys_rel_role_authority` VALUES (1, 10);
INSERT INTO `sys_rel_role_authority` VALUES (1, 11);
INSERT INTO `sys_rel_role_authority` VALUES (1, 12);
INSERT INTO `sys_rel_role_authority` VALUES (1, 13);
INSERT INTO `sys_rel_role_authority` VALUES (1, 14);
INSERT INTO `sys_rel_role_authority` VALUES (1, 15);
INSERT INTO `sys_rel_role_authority` VALUES (1, 16);
INSERT INTO `sys_rel_role_authority` VALUES (1, 17);
INSERT INTO `sys_rel_role_authority` VALUES (1, 18);
INSERT INTO `sys_rel_role_authority` VALUES (1, 19);
INSERT INTO `sys_rel_role_authority` VALUES (1, 20);
INSERT INTO `sys_rel_role_authority` VALUES (1, 21);
INSERT INTO `sys_rel_role_authority` VALUES (1, 22);

-- ----------------------------
-- Table structure for sys_rel_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_rel_user_role`;
CREATE TABLE `sys_rel_user_role`  (
  `uid` int NULL DEFAULT NULL COMMENT '用户id',
  `rid` int NULL DEFAULT NULL COMMENT '角色id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_rel_user_role
-- ----------------------------
INSERT INTO `sys_rel_user_role` VALUES (1, 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `seq` tinyint NULL DEFAULT NULL COMMENT '排序',
  `creator` int NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updator` int NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `status` tinyint NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'ROLE_ADMIN', '管理员', NULL, NULL, NULL, NULL, NULL, 1);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `nickname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `login_time` datetime NULL DEFAULT NULL COMMENT '登录时间',
  `creator` int NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updator` int NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$HLpbo23NoKfxTKuv5UAaB.KMCNvXoCPXDXUKlnZUBQlmhrihU.b2S', '管理员', 'admin@example.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);

-- ----------------------------
-- Table structure for sys_properties
-- ----------------------------
DROP TABLE IF EXISTS `sys_properties`;
CREATE TABLE `sys_properties`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `application` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用',
  `profile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '环境',
  `label` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签',
  `key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '键名称',
  `value` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值'
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_properties
-- ----------------------------
INSERT INTO `sys_properties` VALUES (1, 'adminservice', NULL, 'master', 'server.port', '端口', '8001');
INSERT INTO `sys_properties` VALUES (2, 'authservice', NULL, 'master', 'spring.datasource.driver-class-name', '数据库驱动', 'com.mysql.cj.jdbc.Driver');
INSERT INTO `sys_properties` VALUES (3, 'authservice', NULL, 'master', 'spring.datasource.url', '数据库地址', 'jdbc:mysql://${ipaddr}:3306/strr_admin?serverTimezone=UTC');
INSERT INTO `sys_properties` VALUES (4, 'authservice', NULL, 'master', 'spring.datasource.username', '数据库账号', 'root');
INSERT INTO `sys_properties` VALUES (5, 'authservice', NULL, 'master', 'spring.datasource.password', '数据库密码', '{cipher}b508a19f5f714b97916c70bd872da77208e5dca3da9830fefe9a1a50f683de9c');
INSERT INTO `sys_properties` VALUES (6, 'adminservice', NULL, 'master', 'spring.security.oauth2.resourceserver.jwt.jwk-set-uri', '授权中心地址', 'http://${ipaddr}:9000/oauth2/jwks');
INSERT INTO `sys_properties` VALUES (7, 'authservice', NULL, 'master', 'mybatis.mapper-locations', 'mybatis mapper地址', 'classpath:mapper/*.xml');
INSERT INTO `sys_properties` VALUES (8, 'authservice', NULL, 'master', 'mybatis.configuration.map-underscore-to-camel-case', '开启驼峰映射', 'true');
INSERT INTO `sys_properties` VALUES (9, 'adminservice', NULL, 'master', 'eureka.instance.prefer-ip-address', '以ip地址注册', 'true');
INSERT INTO `sys_properties` VALUES (10, 'adminservice', NULL, 'master', 'eureka.client.register-with-eureka', '注册到eureka', 'true');
INSERT INTO `sys_properties` VALUES (11, 'adminservice', NULL, 'master', 'eureka.client.fetch-registry', '本地缓存', 'true');
INSERT INTO `sys_properties` VALUES (12, 'adminservice', NULL, 'master', 'eureka.client.service-url.defaultZone', '注册中心地址', 'http://localhost:8761/eureka');
INSERT INTO `sys_properties` VALUES (13, 'adminservice', NULL, 'master', 'ipaddr', 'ip地址', '127.0.0.1');
INSERT INTO `sys_properties` VALUES (14, 'adminservice', 'dev', 'master', 'springdoc.packagesToScan', 'swagger扫描路径', 'com.strr');
INSERT INTO `sys_properties` VALUES (15, 'adminservice', 'dev', 'master', 'springdoc.swagger-ui.enabled', '开启swagger', 'true');
INSERT INTO `sys_properties` VALUES (16, 'adminservice', NULL, 'master', 'spring.datasource.driver-class-name', '数据库驱动', 'com.mysql.cj.jdbc.Driver');
INSERT INTO `sys_properties` VALUES (17, 'adminservice', NULL, 'master', 'spring.datasource.url', '数据库地址', 'jdbc:mysql://${ipaddr}:3306/strr_admin?serverTimezone=UTC');
INSERT INTO `sys_properties` VALUES (18, 'adminservice', NULL, 'master', 'spring.datasource.username', '数据库账号', 'root');
INSERT INTO `sys_properties` VALUES (19, 'adminservice', NULL, 'master', 'spring.datasource.password', '数据库密码', '{cipher}b508a19f5f714b97916c70bd872da77208e5dca3da9830fefe9a1a50f683de9c');
INSERT INTO `sys_properties` VALUES (20, 'adminservice', NULL, 'master', 'mybatis.mapper-locations', 'mybatis mapper地址', 'classpath:mapper/*.xml');
INSERT INTO `sys_properties` VALUES (21, 'adminservice', NULL, 'master', 'mybatis.configuration.map-underscore-to-camel-case', '开启驼峰映射', 'true');
INSERT INTO `sys_properties` VALUES (22, 'feignservice', NULL, 'master', 'server.port', '端口', '8081');
INSERT INTO `sys_properties` VALUES (23, 'feignservice', NULL, 'master', 'eureka.instance.prefer-ip-address', '以ip地址注册', 'true');
INSERT INTO `sys_properties` VALUES (24, 'feignservice', NULL, 'master', 'eureka.client.register-with-eureka', '注册到eureka', 'true');
INSERT INTO `sys_properties` VALUES (25, 'feignservice', NULL, 'master', 'eureka.client.fetch-registry', '本地缓存', 'true');
INSERT INTO `sys_properties` VALUES (26, 'feignservice', NULL, 'master', 'eureka.client.service-url.defaultZone', '注册中心地址', 'http://localhost:8761/eureka');
INSERT INTO `sys_properties` VALUES (27, 'authservice', NULL, 'master', 'ipaddr', 'ip地址', '127.0.0.1');
INSERT INTO `sys_properties` VALUES (28, 'authservice', NULL, 'master', 'server.port', '端口', '9000');
INSERT INTO `sys_properties` VALUES (29, 'authservice', NULL, 'master', 'url.gateway', '网关地址', 'http://${ipaddr}:8000');
INSERT INTO `sys_properties` VALUES (30, 'oauth2gatewayservice', NULL, 'master', 'server.port', '端口', '8000');
INSERT INTO `sys_properties` VALUES (31, 'oauth2gatewayservice', NULL, 'master', 'spring.cloud.gateway.discovery.locator.enabled', '注册中心加载', 'true');
INSERT INTO `sys_properties` VALUES (32, 'oauth2gatewayservice', NULL, 'master', 'spring.cloud.gateway.discovery.locator.lower-case-service-id', '服务id小写', 'true');
INSERT INTO `sys_properties` VALUES (33, 'oauth2gatewayservice', NULL, 'master', 'spring.cloud.gateway.default-filters', '默认过滤器', 'TokenRelay');
INSERT INTO `sys_properties` VALUES (34, 'oauth2gatewayservice', NULL, 'master', 'spring.security.oauth2.client.registration.gateway-client.provider', 'oauth client配置', 'spring');
INSERT INTO `sys_properties` VALUES (35, 'oauth2gatewayservice', NULL, 'master', 'spring.security.oauth2.client.registration.gateway-client.client-id', 'client id', 'GATEWAY_CLIENT');
INSERT INTO `sys_properties` VALUES (36, 'oauth2gatewayservice', NULL, 'master', 'spring.security.oauth2.client.registration.gateway-client.client-secret', 'client secret', 'GATEWAY_SECRET');
INSERT INTO `sys_properties` VALUES (37, 'oauth2gatewayservice', NULL, 'master', 'spring.security.oauth2.client.registration.gateway-client.client-authentication-method', '授权method', 'client_secret_basic');
INSERT INTO `sys_properties` VALUES (38, 'oauth2gatewayservice', NULL, 'master', 'spring.security.oauth2.client.registration.gateway-client.authorization-grant-type', '授权方式', 'authorization_code');
INSERT INTO `sys_properties` VALUES (39, 'oauth2gatewayservice', NULL, 'master', 'spring.security.oauth2.client.registration.gateway-client.redirect-uri', '重定向地址', '{baseUrl}/login/oauth2/code/{registrationId}');
INSERT INTO `sys_properties` VALUES (40, 'oauth2gatewayservice', NULL, 'master', 'spring.security.oauth2.client.registration.gateway-client.scope', '域', 'openid, web');
INSERT INTO `sys_properties` VALUES (41, 'oauth2gatewayservice', NULL, 'master', 'spring.security.oauth2.client.registration.gateway-client.client-name', 'client name', 'Spring');
INSERT INTO `sys_properties` VALUES (42, 'oauth2gatewayservice', NULL, 'master', 'spring.security.oauth2.client.provider.spring.issuer-uri', '授权中心地址', 'http://${ipaddr}:9000');
INSERT INTO `sys_properties` VALUES (43, 'oauth2gatewayservice', NULL, 'master', 'eureka.instance.prefer-ip-address', '以ip地址注册', 'true');
INSERT INTO `sys_properties` VALUES (44, 'oauth2gatewayservice', NULL, 'master', 'eureka.client.register-with-eureka', '注册到eureka', 'true');
INSERT INTO `sys_properties` VALUES (45, 'oauth2gatewayservice', NULL, 'master', 'eureka.client.fetch-registry', '本地缓存', 'true');
INSERT INTO `sys_properties` VALUES (46, 'oauth2gatewayservice', NULL, 'master', 'eureka.client.service-url.defaultZone', '注册中心地址', 'http://localhost:8761/eureka');
INSERT INTO `sys_properties` VALUES (47, 'oauth2gatewayservice', NULL, 'master', 'management.endpoint.gateway.enabled', 'actuator', 'true');
INSERT INTO `sys_properties` VALUES (48, 'oauth2gatewayservice', NULL, 'master', 'management.endpoints.web.exposure.include', 'actuator', 'gateway');
INSERT INTO `sys_properties` VALUES (49, 'oauth2gatewayservice', NULL, 'master', 'url.web', '前端地址', 'http://${ipaddr}:8080');
INSERT INTO `sys_properties` VALUES (50, 'oauth2gatewayservice', NULL, 'master', 'ipaddr', 'ip地址', '127.0.0.1');
INSERT INTO `sys_properties` VALUES (51, 'eurekaservice', NULL, 'master', 'server.port', '端口', '8761');
INSERT INTO `sys_properties` VALUES (52, 'eurekaservice', NULL, 'master', 'eureka.client.register-with-eureka', '注册到eureka', 'false');
INSERT INTO `sys_properties` VALUES (53, 'eurekaservice', NULL, 'master', 'eureka.client.fetch-registry', '本地缓存', 'false');
INSERT INTO `sys_properties` VALUES (54, 'eurekaservice', NULL, 'master', 'eureka.server.wait-time-in-ms-when-sync-empty', '等待时间', '5');
INSERT INTO `sys_properties` VALUES (55, 'gatewayservice', NULL, 'master', 'server.port', '端口', '8000');
INSERT INTO `sys_properties` VALUES (56, 'gatewayservice', NULL, 'master', 'spring.cloud.gateway.discovery.locator.enabled', '注册中心加载', 'true');
INSERT INTO `sys_properties` VALUES (57, 'gatewayservice', NULL, 'master', 'spring.cloud.gateway.discovery.locator.lower-case-service-id', '服务id小写', 'true');
INSERT INTO `sys_properties` VALUES (58, 'gatewayservice', NULL, 'master', 'eureka.instance.prefer-ip-address', '以ip地址注册', 'true');
INSERT INTO `sys_properties` VALUES (59, 'gatewayservice', NULL, 'master', 'eureka.client.register-with-eureka', '注册到eureka', 'true');
INSERT INTO `sys_properties` VALUES (60, 'gatewayservice', NULL, 'master', 'eureka.client.fetch-registry', '本地缓存', 'true');
INSERT INTO `sys_properties` VALUES (61, 'gatewayservice', NULL, 'master', 'eureka.client.service-url.defaultZone', '注册中心地址', 'http://localhost:8761/eureka');
INSERT INTO `sys_properties` VALUES (62, 'gatewayservice', NULL, 'master', 'management.endpoint.gateway.enabled', 'actuator', 'true');
INSERT INTO `sys_properties` VALUES (63, 'gatewayservice', NULL, 'master', 'management.endpoints.web.exposure.include', 'actuator', 'gateway');
INSERT INTO `sys_properties` VALUES (64, 'authservice', NULL, 'master', 'eureka.instance.prefer-ip-address', '以ip地址注册', 'true');
INSERT INTO `sys_properties` VALUES (65, 'authservice', NULL, 'master', 'eureka.client.register-with-eureka', '注册到eureka', 'true');
INSERT INTO `sys_properties` VALUES (66, 'authservice', NULL, 'master', 'eureka.client.fetch-registry', '本地缓存', 'true');
INSERT INTO `sys_properties` VALUES (67, 'authservice', NULL, 'master', 'eureka.client.service-url.defaultZone', '注册中心地址', 'http://localhost:8761/eureka');

SET FOREIGN_KEY_CHECKS = 1;