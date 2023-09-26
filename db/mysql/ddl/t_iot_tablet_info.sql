/*
 Navicat Premium Data Transfer

 Source Server         : 10.31.0.101
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : 10.31.0.101:3306
 Source Schema         : iot_meeting

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001
TABLET
 Date: 11/08/2023 14:51:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_iot_tablet_info
-- ----------------------------
DROP TABLE IF EXISTS `t_iot_tablet_info`;
CREATE TABLE `t_iot_tablet_info`  (
   `ID` bigint(16) AUTO_INCREMENT NOT NULL COMMENT '自增ID',
   `TABLET_ID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '平板ID',
   `TABLET_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '平板名称',
   `TABLET_MODEL` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '平板型号',
   `TABLET_BRAND` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '平板品牌',
   `TABLET_IP` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '平板IP',
   `TABLET_PORT` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '平板端口',
   `TABLET_STATE` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '平板状态：1：启用、2：禁用',
   `BORROWED_STATUS` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '平板借用状态：1：使用中、2：空闲',
   `TABLET_ORDER` int NULL COMMENT '平板顺序',
   `CREATE_TIME` datetime  NOT NULL COMMENT '创建时间',
   `UPDATE_TIME` datetime  NULL COMMENT '更新时间',
   `BACK_COL1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段1',
   `BACK_COL2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段2',
   `BACK_COL3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段3',
   `BACK_COL4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段4',
   `BACK_COL5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段5',
   `BACK_COL6` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段6',
   PRIMARY KEY (`ID`) USING BTREE,
   KEY TABLET_NAME_KEY (TABLET_NAME)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '平板基本信息表';