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
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `path` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由地址',
  `component` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由组件',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型(0.目录 1.菜单 2.按钮)',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `hide` tinyint(1) NULL DEFAULT NULL COMMENT '隐藏',
  `parent_id` int NULL DEFAULT NULL COMMENT '父菜单',
  `order` int NULL DEFAULT NULL COMMENT '排序',
  `creator` int NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updator` int NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES (1, 'management', '/management', 'basic', '系统管理', '0', 'carbon:cloud-service-management', NULL, 0, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_resource` VALUES (2, 'management_user', '/management/user', 'self', '用户管理', '1', 'ic:round-manage-accounts', NULL, 1, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_resource` VALUES (3, 'management_role', '/management/role', 'self', '角色管理', '1', 'carbon:user-role', NULL, 1, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_resource` VALUES (4, 'management_resource', '/management/resource', 'self', '资源管理', '1', 'material-symbols:route', NULL, 1, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_resource` VALUES (5, 'show', NULL, NULL, '查看', '2', 'el-icon-view', NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (6, 'add', NULL, NULL, '添加', '2', 'el-icon-plus', NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (7, 'edit', NULL, NULL, '修改', '2', 'el-icon-edit', NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (8, 'del', NULL, NULL, '删除', '2', 'el-icon-delete', NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (9, 'alloc', NULL, NULL, '权限', '2', 'el-icon-view', NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (10, 'add', NULL, NULL, '添加', '2', 'el-icon-plus', NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (11, 'edit', NULL, NULL, '修改', '2', 'el-icon-edit', NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (12, 'del', NULL, NULL, '删除', '2', 'el-icon-delete', NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (13, 'show', NULL, NULL, '查看', '2', 'el-icon-view', NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (14, 'add', NULL, NULL, '添加', '2', 'el-icon-plus', NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (15, 'edit', NULL, NULL, '修改', '2', 'el-icon-edit', NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (16, 'del', NULL, NULL, '删除', '2', 'el-icon-delete', NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (17, 'management_properties', '/management/properties', 'self', '配置管理', '1', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (18, 'show', NULL, NULL, '查看', '2', 'el-icon-view', NULL, 17, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (19, 'add', NULL, NULL, '添加', '2', 'el-icon-plus', NULL, 17, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (20, 'del', NULL, NULL, '删除', '2', 'el-icon-delete', NULL, 17, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (21, 'server', '/server', 'self', '服务管理', '1', NULL, NULL, 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (22, 'management_properties_show', '/management/propertiesShow', 'self', '配置详情', '1', 'el-icon-view', 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_rel_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_rel_role_resource`;
CREATE TABLE `sys_rel_role_resource`  (
  `role_id` int NULL DEFAULT NULL COMMENT '角色id',
  `resource_id` int NULL DEFAULT NULL COMMENT '资源id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_rel_role_resource
-- ----------------------------
INSERT INTO `sys_rel_role_resource` VALUES (1, 1);
INSERT INTO `sys_rel_role_resource` VALUES (1, 2);
INSERT INTO `sys_rel_role_resource` VALUES (1, 3);
INSERT INTO `sys_rel_role_resource` VALUES (1, 4);
INSERT INTO `sys_rel_role_resource` VALUES (1, 5);
INSERT INTO `sys_rel_role_resource` VALUES (1, 6);
INSERT INTO `sys_rel_role_resource` VALUES (1, 7);
INSERT INTO `sys_rel_role_resource` VALUES (1, 8);
INSERT INTO `sys_rel_role_resource` VALUES (1, 9);
INSERT INTO `sys_rel_role_resource` VALUES (1, 10);
INSERT INTO `sys_rel_role_resource` VALUES (1, 11);
INSERT INTO `sys_rel_role_resource` VALUES (1, 12);
INSERT INTO `sys_rel_role_resource` VALUES (1, 13);
INSERT INTO `sys_rel_role_resource` VALUES (1, 14);
INSERT INTO `sys_rel_role_resource` VALUES (1, 15);
INSERT INTO `sys_rel_role_resource` VALUES (1, 16);
INSERT INTO `sys_rel_role_resource` VALUES (1, 17);
INSERT INTO `sys_rel_role_resource` VALUES (1, 18);
INSERT INTO `sys_rel_role_resource` VALUES (1, 19);
INSERT INTO `sys_rel_role_resource` VALUES (1, 20);
INSERT INTO `sys_rel_role_resource` VALUES (1, 21);
INSERT INTO `sys_rel_role_resource` VALUES (1, 22);

-- ----------------------------
-- Table structure for sys_rel_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_rel_user_role`;
CREATE TABLE `sys_rel_user_role`  (
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  `role_id` int NULL DEFAULT NULL COMMENT '角色id'
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
INSERT INTO `sys_role` VALUES (1, 'ROLE_ADMIN', '管理员', NULL, NULL, NULL, NULL, 1);

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
  `value` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值',
  PRIMARY KEY (`id`) USING BTREE
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
INSERT INTO `sys_properties` VALUES (13, 'adminservice', NULL, 'master', 'ipaddr', 'ip地址', 'localhost');
INSERT INTO `sys_properties` VALUES (14, 'adminservice', 'dev', 'master', 'springdoc.packagesToScan', 'swagger扫描路径', 'com.strr');
INSERT INTO `sys_properties` VALUES (15, 'adminservice', 'dev', 'master', 'springdoc.swagger-ui.enabled', '开启swagger', 'true');
INSERT INTO `sys_properties` VALUES (16, 'adminservice', NULL, 'master', 'spring.datasource.driver-class-name', '数据库驱动', 'com.mysql.cj.jdbc.Driver');
INSERT INTO `sys_properties` VALUES (17, 'adminservice', NULL, 'master', 'spring.datasource.url', '数据库地址', 'jdbc:mysql://${ipaddr}:3306/strr_admin?serverTimezone=UTC');
INSERT INTO `sys_properties` VALUES (18, 'adminservice', NULL, 'master', 'spring.datasource.username', '数据库账号', 'root');
INSERT INTO `sys_properties` VALUES (19, 'adminservice', NULL, 'master', 'spring.datasource.password', '数据库密码', '{cipher}b508a19f5f714b97916c70bd872da77208e5dca3da9830fefe9a1a50f683de9c');
INSERT INTO `sys_properties` VALUES (20, 'adminservice', NULL, 'master', 'mybatis.mapper-locations', 'mybatis mapper地址', 'classpath:mapper/*.xml');
INSERT INTO `sys_properties` VALUES (22, 'feignservice', NULL, 'master', 'server.port', '端口', '8081');
INSERT INTO `sys_properties` VALUES (23, 'feignservice', NULL, 'master', 'eureka.instance.prefer-ip-address', '以ip地址注册', 'true');
INSERT INTO `sys_properties` VALUES (24, 'feignservice', NULL, 'master', 'eureka.client.register-with-eureka', '注册到eureka', 'true');
INSERT INTO `sys_properties` VALUES (25, 'feignservice', NULL, 'master', 'eureka.client.fetch-registry', '本地缓存', 'true');
INSERT INTO `sys_properties` VALUES (26, 'feignservice', NULL, 'master', 'eureka.client.service-url.defaultZone', '注册中心地址', 'http://localhost:8761/eureka');
INSERT INTO `sys_properties` VALUES (27, 'authservice', NULL, 'master', 'ipaddr', 'ip地址', 'localhost');
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
INSERT INTO `sys_properties` VALUES (50, 'oauth2gatewayservice', NULL, 'master', 'ipaddr', 'ip地址', 'localhost');
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
INSERT INTO `sys_properties` VALUES (68, 'authservice', NULL, 'master', 'url.web', '前端地址', 'http://${ipaddr}:8080');

SET FOREIGN_KEY_CHECKS = 1;