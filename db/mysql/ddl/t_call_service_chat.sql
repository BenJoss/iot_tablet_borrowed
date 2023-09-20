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
-- Table structure for t_call_service_chat
-- ----------------------------
DROP TABLE IF EXISTS `t_call_service_chat`;
CREATE TABLE `t_call_service_chat`  (
   `ID` bigint(16) AUTO_INCREMENT NOT NULL COMMENT '自增ID',
   `ROOM_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '会议室ID',
   `MEETING_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '会议ID',
   `USER_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ID',
   `CHAT_MSG` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '聊天内容',
   `CHAT_STATE` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '消息状态:1 代表已读、2 代表未读',
   `CREATE_TIME` bigint(11)  NOT NULL COMMENT '消息时间戳',
   `PART_TIME` datetime  NOT NULL COMMENT '分区时间戳',
   `BACK_COL1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '备份字段1',
   `BACK_COL2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '备份字段2',
   `BACK_COL3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '备份字段3',
   `BACK_COL4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '备份字段4',
   `BACK_COL5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '备份字段5',
   `BACK_COL6` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL COMMENT '备份字段6',
   PRIMARY KEY (`ID`,`PART_TIME`) USING BTREE,
   KEY ROOM_TIME_KEY (ROOM_ID,CREATE_TIME),
   KEY MEETING_ID_KEY (MEETING_ID)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '呼叫服务消息记录表'

PARTITION BY RANGE(YEAR(PART_TIME)*100+MONTH(PART_TIME))(
    PARTITION partition1 VALUES LESS THAN (202307),
    PARTITION partition2 VALUES LESS THAN (202308),
    PARTITION partition3 VALUES LESS THAN (202309),
    PARTITION partition4 VALUES LESS THAN (202310),
    PARTITION partition5 VALUES LESS THAN (202311),
    PARTITION partition6 VALUES LESS THAN (202312),
    PARTITION partition7 VALUES LESS THAN (202401)
);



