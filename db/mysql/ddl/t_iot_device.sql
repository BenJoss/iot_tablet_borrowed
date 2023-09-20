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
-- Table structure for t_iot_device
-- ----------------------------
DROP TABLE IF EXISTS `t_iot_device`;
CREATE TABLE `t_iot_device`  (
   `ID` bigint(16) AUTO_INCREMENT NOT NULL COMMENT '自增ID',
   `CONT_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容ID',
   `DEVICE_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '展示设备ID',
   `DEVICE_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '展示设备名称',
   `DEVICE_TYPE` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '设备类型 1:移动、2:大屏',
   `DEVICE_MODEL` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '设备型号',
   `DEVICE_ORDER` int NULL COMMENT '设备顺序',
   `CREATE_TIME` datetime  NOT NULL COMMENT '创建时间',
   `UPDATE_TIME` datetime  NULL COMMENT '更新时间',
   `BACK_COL1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '备份字段1',
   `BACK_COL2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '备份字段2',
   `BACK_COL3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '备份字段3',
   `BACK_COL4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '备份字段4',
   `BACK_COL5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '备份字段5',
   `BACK_COL6` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '备份字段6',
   PRIMARY KEY (`ID`) USING BTREE,
   KEY CONT_ID_KEY (CONT_ID),
   KEY DEVICE_ID_KEY (DEVICE_ID)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '物联会议室内容绑定设备信息表';





