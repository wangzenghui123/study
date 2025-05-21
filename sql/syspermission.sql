/*
 Navicat Premium Dump SQL

 Source Server         : demo
 Source Server Type    : MySQL
 Source Server Version : 80040 (8.0.40)
 Source Host           : localhost:3306
 Source Schema         : study

 Target Server Type    : MySQL
 Target Server Version : 80040 (8.0.40)
 File Encoding         : 65001

 Date: 21/05/2025 17:37:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for syspermission
-- ----------------------------
DROP TABLE IF EXISTS `syspermission`;
CREATE TABLE `syspermission`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `perms` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `orderNum` int NULL DEFAULT NULL,
  `type` int NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `createTime` datetime NULL DEFAULT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  `deleted` int NULL DEFAULT NULL,
  `pidName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of syspermission
-- ----------------------------
INSERT INTO `syspermission` VALUES ('1', '', '系统管理', NULL, NULL, NULL, '0', 2, 1, 1, '2025-05-21 17:24:51', '2025-05-21 17:24:51', 0, '根目录');
INSERT INTO `syspermission` VALUES ('10', NULL, '用户查询', NULL, NULL, NULL, '4', 1, 3, 1, '2025-05-21 17:24:51', '2025-05-21 17:24:51', 0, '用户管理');
INSERT INTO `syspermission` VALUES ('11', NULL, '用户新增', NULL, NULL, NULL, '4', 2, 3, 1, '2025-05-21 17:24:51', '2025-05-21 17:24:51', 0, '用户管理');
INSERT INTO `syspermission` VALUES ('12', NULL, '用户修改', NULL, NULL, NULL, '4', 3, 3, 1, '2025-05-21 17:24:51', '2025-05-21 17:24:51', 0, '用户管理');
INSERT INTO `syspermission` VALUES ('13', NULL, '用户删除', NULL, NULL, NULL, '4', 4, 3, 1, '2025-05-21 17:24:51', '2025-05-21 17:24:51', 0, '用户管理');
INSERT INTO `syspermission` VALUES ('14', NULL, '用户导出', NULL, NULL, NULL, '4', 5, 3, 1, '2025-05-21 17:24:51', '2025-05-21 17:24:51', 0, '用户管理');
INSERT INTO `syspermission` VALUES ('15', NULL, '用户导入', NULL, NULL, NULL, '4', 6, 3, 1, '2025-05-21 17:24:51', '2025-05-21 17:24:51', 0, '用户管理');
INSERT INTO `syspermission` VALUES ('16', NULL, '重置密码', NULL, NULL, NULL, '4', 7, 3, 1, '2025-05-21 17:24:51', '2025-05-21 17:24:51', 0, '用户管理');
INSERT INTO `syspermission` VALUES ('2', '', '系统监控', NULL, NULL, NULL, '0', 3, 1, 1, '2025-05-21 17:24:51', '2025-05-21 17:24:55', 0, '根目录');
INSERT INTO `syspermission` VALUES ('3', '', '系统工具', NULL, NULL, NULL, '0', 4, 1, 1, '2025-05-21 17:24:51', '2025-05-21 17:24:51', 0, '根目录');
INSERT INTO `syspermission` VALUES ('4', '', '用户管理', NULL, NULL, NULL, '1', 1, 2, 1, '2025-05-21 17:24:51', '2025-05-21 17:24:51', 0, '系统管理');
INSERT INTO `syspermission` VALUES ('5', '', '角色管理', NULL, NULL, NULL, '1', 2, 2, 1, '2025-05-21 17:24:51', '2025-05-21 17:24:51', 0, '系统管理');
INSERT INTO `syspermission` VALUES ('6', '', '菜单管理', NULL, NULL, NULL, '1', 3, 2, 1, '2025-05-21 17:24:51', '2025-05-21 17:24:51', 0, '系统管理');
INSERT INTO `syspermission` VALUES ('7', '', '在线用户', NULL, NULL, NULL, '2', 1, 2, 1, '2025-05-21 17:24:51', '2025-05-21 17:24:51', 0, '系统监控');
INSERT INTO `syspermission` VALUES ('8', '', '表单构成', NULL, NULL, NULL, '3', 1, 2, 1, '2025-05-21 17:24:51', '2025-05-21 17:24:51', 0, '系统工具');
INSERT INTO `syspermission` VALUES ('9', '', '首页', NULL, NULL, NULL, '0', 1, 1, 1, '2025-05-21 17:24:51', '2025-05-21 17:24:51', 0, '根目录');

SET FOREIGN_KEY_CHECKS = 1;
