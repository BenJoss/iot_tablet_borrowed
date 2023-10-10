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
-- Table structure for t_iot_device_borrow
-- ----------------------------
DROP TABLE IF EXISTS `t_iot_device_borrow`;
CREATE TABLE `t_iot_device_borrow`  (
   `ID` bigint(16) AUTO_INCREMENT NOT NULL COMMENT '自增ID',
   `ROOM_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '会议室ID',
   `MEET_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '会议ID',
   `VERIFY_CODE` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '借还验证码',
   `BORROW_NUM` int NULL COMMENT '借用数量',
   `USER_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '借出用户ID',
   `USER_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '借出用户名称',
   `BORROW_ORG` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '借出部门',
   `CONNECT_WAY` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '联系方式',
   `BORROWED_STATUS` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '借用状态：1：待借用、2：借用中、3：完结、4：异常、5：取消',
   `ALLDAY_TIME` datetime   NULL COMMENT '借用时间 全天时段',
   `MORN_TIME`  datetime   NULL COMMENT '借用时间 上午时段',
   `AFTER_TIME`    datetime   NULL  COMMENT '借用时间 下午时段',
   `START_TIME` datetime   NULL COMMENT '开始时间',
   `END_TIME` datetime  NULL COMMENT '结束时间',   
   `CREATE_TIME` datetime  NOT NULL COMMENT '创建时间',
   `UPDATE_TIME` datetime  NOT NULL COMMENT '更新时间',
   `BACK_COL1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段1',
   `BACK_COL2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段2',
   `BACK_COL3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段3',
   `BACK_COL4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段4',
   `BACK_COL5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段5',
   `BACK_COL6` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL COMMENT '备份字段6',
   PRIMARY KEY (`ID`,`CREATE_TIME`) USING BTREE,
   KEY VERIFY_KEY (VERIFY_CODE),
   KEY ALLDAY_TIME_KEY (ALLDAY_TIME),
   KEY MORN_TIME_KEY (MORN_TIME),
   KEY AFTER_TIME_KEY (AFTER_TIME)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设备借出记录表'

PARTITION BY RANGE(YEAR(CREATE_TIME)*100+MONTH(CREATE_TIME))(
    PARTITION partition1 VALUES LESS THAN (202307),
    PARTITION partition2 VALUES LESS THAN (202308),
    PARTITION partition3 VALUES LESS THAN (202309),
    PARTITION partition4 VALUES LESS THAN (202310),
    PARTITION partition5 VALUES LESS THAN (202311),
    PARTITION partition6 VALUES LESS THAN (202312),
    PARTITION partition7 VALUES LESS THAN (202401),
    PARTITION partition8 VALUES LESS THAN (202402),
    PARTITION partition9 VALUES LESS THAN (202403),
    PARTITION partition10 VALUES LESS THAN (202404),
    PARTITION partition11 VALUES LESS THAN (202405),
    PARTITION partition12 VALUES LESS THAN (202406),
    PARTITION partition13 VALUES LESS THAN (202407),
    PARTITION partition14 VALUES LESS THAN (202408),
    PARTITION partition15 VALUES LESS THAN (202409),
    PARTITION partition16 VALUES LESS THAN (202410)
);



