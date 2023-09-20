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

 Date: 11/08/2023 14:51:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_iot_room_classify
-- ----------------------------
DROP TABLE IF EXISTS `t_iot_room_classify`;
CREATE TABLE `t_iot_room_classify`  (
   `ID` bigint(16) AUTO_INCREMENT NOT NULL COMMENT '自增ID',
   `ROOM_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '会议室ID',
   `CLASS_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类ID',
   `CLASS_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名称',
   `CLASS_TYPE` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类型 1:分类',
   `CLASS_AUTH` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '分类权限',
   `CLASS_ORDER` int NULL COMMENT '分类顺序',
   `CREATE_TIME` datetime  NOT NULL COMMENT '创建时间',
   `UPDATE_TIME` datetime  NULL COMMENT '更新时间',
   `BACK_COL1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '备份字段1',
   `BACK_COL2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '备份字段2',
   `BACK_COL3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '备份字段3',
   `BACK_COL4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '备份字段4',
   `BACK_COL5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '备份字段5',
   `BACK_COL6` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '备份字段6',
   PRIMARY KEY (`ID`) USING BTREE,
   KEY ROOM_ID_KEY (ROOM_ID),
   KEY CLASS_ID_KEY (CLASS_ID)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '物联会议室内容分类表';





