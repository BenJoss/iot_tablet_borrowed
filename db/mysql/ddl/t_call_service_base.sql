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
-- Table structure for t_call_service_meet
-- ----------------------------
DROP TABLE IF EXISTS `t_call_service_meet`;
CREATE TABLE `t_call_service_meet`  (
   `ID` bigint(11) AUTO_INCREMENT NOT NULL COMMENT '自增ID',
   `ROOM_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '会议室ID',
   `MEET_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '会议ID',
   `MEET_NAME` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '会议名称',
   `MEET_CONTENT` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '会议内容',
   `NOTICES` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '通知',
   `MEET_APPLY` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '会议申请人',
   `MEET_ADDR` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '会议地址',
   `MEET_STRTIME` datetime   NULL COMMENT '会议开始时间',
   `MEET_ENDTIME` datetime  NULL COMMENT '会议结束时间',
   `MEET_TIME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL  NULL COMMENT '会议时间',
   `USER_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '用户ID',  
   `CREATE_TIME` datetime  NOT NULL COMMENT '创建时间',
   `UPDATE_TIME` datetime  NOT NULL COMMENT '更新时间',
   `PART_TIME` datetime  NOT NULL COMMENT '分区时间戳',
   `BACK_COL1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '备份字段1',
   `BACK_COL2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '备份字段2',
   `BACK_COL3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '备份字段3',
   `BACK_COL4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '备份字段4',
   `BACK_COL5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '备份字段5',
   `BACK_COL6` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '备份字段6',
   PRIMARY KEY (`ID`,`PART_TIME`) USING BTREE,
   KEY ROOM_MEET_KEY (`ROOM_ID`,`MEET_ID`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '呼叫服务会议信息表'

PARTITION BY RANGE(YEAR(PART_TIME))(
    PARTITION partition1 VALUES LESS THAN (2024),
    PARTITION partition2 VALUES LESS THAN (2025),
    PARTITION partition3 VALUES LESS THAN (2026),
    PARTITION partition4 VALUES LESS THAN (2027),
    PARTITION partition5 VALUES LESS THAN (2028),
    PARTITION partition6 VALUES LESS THAN (2029),
    PARTITION partition7 VALUES LESS THAN (2030)
);