/*
 Navicat Premium Data Transfer

 Source Server         : 10.31.0.101
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : 10.31.0.101:3306
 Source Schema         : Device_management

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 15/09/2023 19:59:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tablet_borrowed
-- ----------------------------
DROP TABLE IF EXISTS `tablet_borrowed`;
CREATE TABLE `tablet_borrowed`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '序号',
  `personneId` int(0) NULL DEFAULT NULL COMMENT '鍊熺敤浜?',
  `borrowedName` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '借用人',
  `borrowedNamePhone` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '闁稿﹦鍠撻弫銈嗙閾忚鏆╅悹?',
  `quantityBorrowed` int(0) NULL DEFAULT NULL COMMENT '鍊熺敤鏁伴噺',
  `borrowStartTime` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '借用开始时间',
  `borrowEndTime` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '借用结束时间',
  `borrowedState` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '借用状态',
  `returnQuantity` int(0) NULL DEFAULT NULL COMMENT '褰掕繕鏁伴噺',
  `returnTime` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '归还时间',
  `verificationCode` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '濡ょ姴鐭侀惁澶愭儘?',
  `mtName` varchar(124) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会议名称',
  `applyId` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浼氳id',
  `roomId` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浼氳瀹d',
  `customTheme` varchar(124) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `startTime` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会议开始时间',
  `usedNum` int(0) NULL DEFAULT NULL COMMENT '已借用数量',
  `avaiableNum` int(0) NULL DEFAULT NULL COMMENT '可借用数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
