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
-- Table structure for t_iot_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `t_iot_operate_log`;
CREATE TABLE `t_iot_operate_log`  (
   `ID` bigint(16) AUTO_INCREMENT NOT NULL COMMENT '自增ID',
   `OPERATE_ID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作ID',
   `OPERATE_TYPE` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '操作类型',
   `OPERATE_CONT` longtext  NULL COMMENT '操作内容',
   `CREATE_TIME` datetime  NOT NULL COMMENT '创建时间',
   `BACK_COL1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段1',
   `BACK_COL2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段2',
   `BACK_COL3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段3',
   `BACK_COL4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段4',
   `BACK_COL5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段5',
   `BACK_COL6` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段6',
   PRIMARY KEY (`ID`,`CREATE_TIME`) USING BTREE,
   KEY OPERATE_ID_KEY (OPERATE_ID)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设备借还操作日志表'

PARTITION BY RANGE(YEAR(CREATE_TIME)*100+MONTH(CREATE_TIME))(
    PARTITION partition1 VALUES LESS THAN (202307),
    PARTITION partition2 VALUES LESS THAN (202308),
    PARTITION partition3 VALUES LESS THAN (202309),
    PARTITION partition4 VALUES LESS THAN (202310),
    PARTITION partition5 VALUES LESS THAN (202311),
    PARTITION partition6 VALUES LESS THAN (202312),
    PARTITION partition8 VALUES LESS THAN (202401),
    PARTITION partition9 VALUES LESS THAN (202402),
    PARTITION partition10 VALUES LESS THAN (202403),
    PARTITION partition11 VALUES LESS THAN (202404),
    PARTITION partition12 VALUES LESS THAN (202405),
    PARTITION partition13 VALUES LESS THAN (202406),
    PARTITION partition14 VALUES LESS THAN (202407),
    PARTITION partition15 VALUES LESS THAN (202408),
    PARTITION partition16 VALUES LESS THAN (202409),
    PARTITION partition18 VALUES LESS THAN (202410)
);