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
-- Table structure for t_iot_device_return
-- ----------------------------
DROP TABLE IF EXISTS `t_iot_device_return`;
CREATE TABLE `t_iot_device_return`  (
   `ID` bigint(16) AUTO_INCREMENT NOT NULL COMMENT '自增ID',
   `VERIFY_CODE` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '借还验证码',
   `RETURN_NUM` int NULL COMMENT '归还数量',
   `RETURN_TIME` datetime  NOT NULL COMMENT '归还时间',
   `CREATE_TIME` datetime  NOT NULL COMMENT '创建时间',
   `UPDATE_TIME` datetime  NOT NULL COMMENT '更新时间',
   `BACK_COL1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段1',
   `BACK_COL2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段2',
   `BACK_COL3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段3',
   `BACK_COL4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段4',
   `BACK_COL5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段5',
   `BACK_COL6` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段6',
   PRIMARY KEY (`ID`,`CREATE_TIME`) USING BTREE,
   KEY VERIFY_KEY (VERIFY_CODE)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设备归还记录表'

PARTITION BY RANGE(YEAR(PART_TIME)*100+MONTH(PART_TIME))(
    PARTITION partition1 VALUES LESS THAN (202307),
    PARTITION partition2 VALUES LESS THAN (202308),
    PARTITION partition3 VALUES LESS THAN (202309),
    PARTITION partition4 VALUES LESS THAN (202310),
    PARTITION partition5 VALUES LESS THAN (202311),
    PARTITION partition6 VALUES LESS THAN (202312),
    PARTITION partition7 VALUES LESS THAN (202401),
    PARTITION partition7 VALUES LESS THAN (202402),
    PARTITION partition7 VALUES LESS THAN (202403),
    PARTITION partition7 VALUES LESS THAN (202404),
    PARTITION partition7 VALUES LESS THAN (202405),
    PARTITION partition7 VALUES LESS THAN (202406),
    PARTITION partition7 VALUES LESS THAN (202407),
    PARTITION partition7 VALUES LESS THAN (202408),
    PARTITION partition7 VALUES LESS THAN (202409),
    PARTITION partition7 VALUES LESS THAN (202410)
);

