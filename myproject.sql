/*
 Navicat Premium Data Transfer

 Source Server         : Centos
 Source Server Type    : MySQL
 Source Server Version : 50644
 Source Host           : 134.175.59.87:3306
 Source Schema         : myproject

 Target Server Type    : MySQL
 Target Server Version : 50644
 File Encoding         : 65001

 Date: 29/06/2019 17:43:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for classroom
-- ----------------------------
DROP TABLE IF EXISTS `classroom`;
CREATE TABLE `classroom`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `schoolarea` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `roomnum` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `health` int(5) NOT NULL DEFAULT 1,
  `is_use` int(2) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `is_use`(`is_use`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1022 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of classroom
-- ----------------------------
INSERT INTO `classroom` VALUES (1001, 'F', 'F104', 1, 1);
INSERT INTO `classroom` VALUES (1002, 'F', 'F105', 1, 1);
INSERT INTO `classroom` VALUES (1003, 'F', 'F208', 1, 0);
INSERT INTO `classroom` VALUES (1004, 'F', 'F305', 1, 0);
INSERT INTO `classroom` VALUES (1005, 'B', 'B101', 1, 1);
INSERT INTO `classroom` VALUES (1006, 'B', 'B301', 1, 1);
INSERT INTO `classroom` VALUES (1007, 'B', 'B103', 1, 1);
INSERT INTO `classroom` VALUES (1008, 'B', 'B202', 1, 1);
INSERT INTO `classroom` VALUES (1009, 'C', 'C102', 1, 1);
INSERT INTO `classroom` VALUES (1010, 'C', 'C105', 1, 1);
INSERT INTO `classroom` VALUES (1011, 'C', 'C201', 1, 1);
INSERT INTO `classroom` VALUES (1012, 'A', 'A206', 1, 1);
INSERT INTO `classroom` VALUES (1013, 'A', 'A301', 1, 1);
INSERT INTO `classroom` VALUES (1014, 'A', 'A103', 1, 0);
INSERT INTO `classroom` VALUES (1015, 'E', 'E202', 0, 0);
INSERT INTO `classroom` VALUES (1016, 'E', 'E104', 1, 1);
INSERT INTO `classroom` VALUES (1017, '外语楼', 'W103', 1, 1);
INSERT INTO `classroom` VALUES (1018, '外语楼', 'W106', 1, 1);
INSERT INTO `classroom` VALUES (1019, '电信院', '电信院509', 1, 1);
INSERT INTO `classroom` VALUES (1020, 'E', 'E203', 1, 1);
INSERT INTO `classroom` VALUES (1021, 'E', 'E101', 1, 1);

-- ----------------------------
-- Table structure for classtable
-- ----------------------------
DROP TABLE IF EXISTS `classtable`;
CREATE TABLE `classtable`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `term` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `weeknum` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `weekday` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `class_order` int(10) NULL DEFAULT NULL,
  `course` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `room` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `room_id`(`room`) USING BTREE,
  INDEX `course_id`(`course`) USING BTREE,
  INDEX `user_id`(`user`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12118 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of classtable
-- ----------------------------
INSERT INTO `classtable` VALUES (12095, '2018-2019-2', '3', '周一', 3, '线性代数', '张燕', 'B101');
INSERT INTO `classtable` VALUES (12096, '2018-2019-2', '3', '周二', 2, '概率论与梳理统计', '章婷芳', 'F105');
INSERT INTO `classtable` VALUES (12097, '2018-2019-2', '3', '周三', 3, '大学物理1', '赵越', 'E202');
INSERT INTO `classtable` VALUES (12098, '2018-2019-2', '3', '周四', 4, '大学物理2', '赵越', 'E203');
INSERT INTO `classtable` VALUES (12099, '2018-2019-2', '3', '周五', 5, 'Java程序设计', '卢冶', '电信院509');
INSERT INTO `classtable` VALUES (12100, '2018-2019-2', '3', '周一', 3, '面向对象程序设计', '刘永良', 'F104');
INSERT INTO `classtable` VALUES (12101, '2018-2019-2', '3', '周三', 2, '大学英语1', '万石建', 'E203');
INSERT INTO `classtable` VALUES (12116, '2018-2019-2', '3', '周一', 3, '数据库', '李雪宝', 'E101');
INSERT INTO `classtable` VALUES (12117, '2018-2019-2', '10', '周三', 2, '概率论', '章婷芳', 'A301');

-- ----------------------------
-- Table structure for error_record
-- ----------------------------
DROP TABLE IF EXISTS `error_record`;
CREATE TABLE `error_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `errorInfo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roomnum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date` datetime(0) NULL DEFAULT NULL,
  `worknum` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `room_id`(`roomnum`) USING BTREE,
  INDEX `user_id`(`worknum`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100027 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of error_record
-- ----------------------------
INSERT INTO `error_record` VALUES (100020, 'bndxasj', 'e103', '2019-04-27 12:39:27', '172219605215');
INSERT INTO `error_record` VALUES (100021, '电脑内存不足', 'e105', '2019-04-27 12:40:05', '17522456245');
INSERT INTO `error_record` VALUES (100024, '电脑内存不足', 'E202', '2019-04-27 15:37:23', '17522456245');

-- ----------------------------
-- Table structure for school_calander
-- ----------------------------
DROP TABLE IF EXISTS `school_calander`;
CREATE TABLE `school_calander`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `weeknum` int(11) NOT NULL,
  `weekday` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `term` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 137 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of school_calander
-- ----------------------------
INSERT INTO `school_calander` VALUES (4, '2019-02-25', 1, '周一', '2018-2019-2');
INSERT INTO `school_calander` VALUES (5, '2019-02-26', 1, '周二', '2018-2019-2');
INSERT INTO `school_calander` VALUES (6, '2019-02-27', 1, '周三', '2018-2019-2');
INSERT INTO `school_calander` VALUES (7, '2019-02-28', 1, '周四', '2018-2019-2');
INSERT INTO `school_calander` VALUES (8, '2019-03-01', 1, '周五', '2018-2019-2');
INSERT INTO `school_calander` VALUES (9, '2019-03-02', 1, '周六', '2018-2019-2');
INSERT INTO `school_calander` VALUES (10, '2019-03-03', 1, '周日', '2018-2019-2');
INSERT INTO `school_calander` VALUES (11, '2019-03-04', 2, '周一', '2018-2019-2');
INSERT INTO `school_calander` VALUES (12, '2019-03-05', 2, '周二', '2018-2019-2');
INSERT INTO `school_calander` VALUES (13, '2019-03-06', 2, '周三', '2018-2019-2');
INSERT INTO `school_calander` VALUES (14, '2019-03-07', 2, '周四', '2018-2019-2');
INSERT INTO `school_calander` VALUES (15, '2019-03-08', 2, '周五', '2018-2019-2');
INSERT INTO `school_calander` VALUES (16, '2019-03-09', 2, '周六', '2018-2019-2');
INSERT INTO `school_calander` VALUES (17, '2019-03-10', 2, '周日', '2018-2019-2');
INSERT INTO `school_calander` VALUES (18, '2019-03-11', 3, '周一', '2018-2019-2');
INSERT INTO `school_calander` VALUES (19, '2019-03-12', 3, '周二', '2018-2019-2');
INSERT INTO `school_calander` VALUES (20, '2019-03-13', 3, '周三', '2018-2019-2');
INSERT INTO `school_calander` VALUES (21, '2019-03-14', 3, '周四', '2018-2019-2');
INSERT INTO `school_calander` VALUES (22, '2019-03-15', 3, '周五', '2018-2019-2');
INSERT INTO `school_calander` VALUES (23, '2019-03-16', 3, '周六', '2018-2019-2');
INSERT INTO `school_calander` VALUES (24, '2019-03-17', 3, '周日', '2018-2019-2');
INSERT INTO `school_calander` VALUES (25, '2019-03-18', 4, '周一', '2018-2019-2');
INSERT INTO `school_calander` VALUES (26, '2019-03-19', 4, '周二', '2018-2019-2');
INSERT INTO `school_calander` VALUES (27, '2019-03-20', 4, '周三', '2018-2019-2');
INSERT INTO `school_calander` VALUES (28, '2019-03-21', 4, '周四', '2018-2019-2');
INSERT INTO `school_calander` VALUES (29, '2019-03-22', 4, '周五', '2018-2019-2');
INSERT INTO `school_calander` VALUES (30, '2019-03-23', 4, '周六', '2018-2019-2');
INSERT INTO `school_calander` VALUES (31, '2019-03-24', 4, '周日', '2018-2019-2');
INSERT INTO `school_calander` VALUES (32, '2019-03-25', 5, '周一', '2018-2019-2');
INSERT INTO `school_calander` VALUES (33, '2019-03-26', 5, '周二', '2018-2019-2');
INSERT INTO `school_calander` VALUES (34, '2019-03-27', 5, '周三', '2018-2019-2');
INSERT INTO `school_calander` VALUES (35, '2019-03-28', 5, '周四', '2018-2019-2');
INSERT INTO `school_calander` VALUES (36, '2019-03-29', 5, '周五', '2018-2019-2');
INSERT INTO `school_calander` VALUES (37, '2019-03-30', 5, '周六', '2018-2019-2');
INSERT INTO `school_calander` VALUES (38, '2019-03-31', 5, '周日', '2018-2019-2');
INSERT INTO `school_calander` VALUES (39, '2019-04-01', 6, '周一', '2018-2019-2');
INSERT INTO `school_calander` VALUES (40, '2019-04-02', 6, '周二', '2018-2019-2');
INSERT INTO `school_calander` VALUES (41, '2019-04-03', 6, '周三', '2018-2019-2');
INSERT INTO `school_calander` VALUES (42, '2019-04-04', 6, '周四', '2018-2019-2');
INSERT INTO `school_calander` VALUES (43, '2019-04-05', 6, '周五', '2018-2019-2');
INSERT INTO `school_calander` VALUES (44, '2019-04-06', 6, '周六', '2018-2019-2');
INSERT INTO `school_calander` VALUES (45, '2019-04-07', 6, '周日', '2018-2019-2');
INSERT INTO `school_calander` VALUES (46, '2019-04-08', 7, '周一', '2018-2019-2');
INSERT INTO `school_calander` VALUES (47, '2019-04-09', 7, '周二', '2018-2019-2');
INSERT INTO `school_calander` VALUES (48, '2019-04-10', 7, '周三', '2018-2019-2');
INSERT INTO `school_calander` VALUES (49, '2019-04-11', 7, '周四', '2018-2019-2');
INSERT INTO `school_calander` VALUES (50, '2019-04-12', 7, '周五', '2018-2019-2');
INSERT INTO `school_calander` VALUES (51, '2019-04-13', 7, '周六', '2018-2019-2');
INSERT INTO `school_calander` VALUES (52, '2019-04-14', 7, '周日', '2018-2019-2');
INSERT INTO `school_calander` VALUES (53, '2019-04-15', 8, '周一', '2018-2019-2');
INSERT INTO `school_calander` VALUES (54, '2019-04-16', 8, '周二', '2018-2019-2');
INSERT INTO `school_calander` VALUES (55, '2019-04-17', 8, '周三', '2018-2019-2');
INSERT INTO `school_calander` VALUES (56, '2019-04-18', 8, '周四', '2018-2019-2');
INSERT INTO `school_calander` VALUES (57, '2019-04-19', 8, '周五', '2018-2019-2');
INSERT INTO `school_calander` VALUES (58, '2019-04-20', 8, '周六', '2018-2019-2');
INSERT INTO `school_calander` VALUES (59, '2019-04-21', 8, '周日', '2018-2019-2');
INSERT INTO `school_calander` VALUES (60, '2019-04-22', 9, '周一', '2018-2019-2');
INSERT INTO `school_calander` VALUES (61, '2019-04-23', 9, '周二', '2018-2019-2');
INSERT INTO `school_calander` VALUES (62, '2019-04-24', 9, '周三', '2018-2019-2');
INSERT INTO `school_calander` VALUES (63, '2019-04-25', 9, '周四', '2018-2019-2');
INSERT INTO `school_calander` VALUES (64, '2019-04-26', 9, '周五', '2018-2019-2');
INSERT INTO `school_calander` VALUES (65, '2019-04-27', 9, '周六', '2018-2019-2');
INSERT INTO `school_calander` VALUES (66, '2019-04-28', 9, '周日', '2018-2019-2');
INSERT INTO `school_calander` VALUES (67, '2019-04-29', 10, '周一', '2018-2019-2');
INSERT INTO `school_calander` VALUES (68, '2019-04-30', 10, '周二', '2018-2019-2');
INSERT INTO `school_calander` VALUES (69, '2019-05-01', 10, '周三', '2018-2019-2');
INSERT INTO `school_calander` VALUES (70, '2019-05-02', 10, '周四', '2018-2019-2');
INSERT INTO `school_calander` VALUES (71, '2019-05-03', 10, '周五', '2018-2019-2');
INSERT INTO `school_calander` VALUES (72, '2019-05-04', 10, '周六', '2018-2019-2');
INSERT INTO `school_calander` VALUES (73, '2019-05-05', 10, '周日', '2018-2019-2');
INSERT INTO `school_calander` VALUES (74, '2019-05-06', 11, '周一', '2018-2019-2');
INSERT INTO `school_calander` VALUES (75, '2019-05-07', 11, '周二', '2018-2019-2');
INSERT INTO `school_calander` VALUES (76, '2019-05-08', 11, '周三', '2018-2019-2');
INSERT INTO `school_calander` VALUES (77, '2019-05-09', 11, '周四', '2018-2019-2');
INSERT INTO `school_calander` VALUES (78, '2019-05-10', 11, '周五', '2018-2019-2');
INSERT INTO `school_calander` VALUES (79, '2019-05-11', 11, '周六', '2018-2019-2');
INSERT INTO `school_calander` VALUES (80, '2019-05-12', 11, '周日', '2018-2019-2');
INSERT INTO `school_calander` VALUES (81, '2019-05-13', 12, '周一', '2018-2019-2');
INSERT INTO `school_calander` VALUES (82, '2019-05-14', 12, '周二', '2018-2019-2');
INSERT INTO `school_calander` VALUES (83, '2019-05-15', 12, '周三', '2018-2019-2');
INSERT INTO `school_calander` VALUES (84, '2019-05-16', 12, '周四', '2018-2019-2');
INSERT INTO `school_calander` VALUES (85, '2019-05-17', 12, '周五', '2018-2019-2');
INSERT INTO `school_calander` VALUES (86, '2019-05-18', 12, '周六', '2018-2019-2');
INSERT INTO `school_calander` VALUES (87, '2019-05-19', 12, '周日', '2018-2019-2');
INSERT INTO `school_calander` VALUES (88, '2019-05-20', 13, '周一', '2018-2019-2');
INSERT INTO `school_calander` VALUES (89, '2019-05-21', 13, '周二', '2018-2019-2');
INSERT INTO `school_calander` VALUES (90, '2019-05-22', 13, '周三', '2018-2019-2');
INSERT INTO `school_calander` VALUES (91, '2019-05-23', 13, '周四', '2018-2019-2');
INSERT INTO `school_calander` VALUES (92, '2019-05-24', 13, '周五', '2018-2019-2');
INSERT INTO `school_calander` VALUES (93, '2019-05-25', 13, '周六', '2018-2019-2');
INSERT INTO `school_calander` VALUES (94, '2019-05-26', 13, '周日', '2018-2019-2');
INSERT INTO `school_calander` VALUES (95, '2019-05-27', 14, '周一', '2018-2019-2');
INSERT INTO `school_calander` VALUES (96, '2019-05-28', 14, '周二', '2018-2019-2');
INSERT INTO `school_calander` VALUES (97, '2019-05-29', 14, '周三', '2018-2019-2');
INSERT INTO `school_calander` VALUES (98, '2019-05-30', 14, '周四', '2018-2019-2');
INSERT INTO `school_calander` VALUES (99, '2019-05-31', 14, '周五', '2018-2019-2');
INSERT INTO `school_calander` VALUES (100, '2019-06-01', 14, '周六', '2018-2019-2');
INSERT INTO `school_calander` VALUES (101, '2019-06-02', 14, '周日', '2018-2019-2');
INSERT INTO `school_calander` VALUES (102, '2019-06-03', 15, '周一', '2018-2019-2');
INSERT INTO `school_calander` VALUES (103, '2019-06-04', 15, '周二', '2018-2019-2');
INSERT INTO `school_calander` VALUES (104, '2019-06-05', 15, '周三', '2018-2019-2');
INSERT INTO `school_calander` VALUES (105, '2019-06-06', 15, '周四', '2018-2019-2');
INSERT INTO `school_calander` VALUES (106, '2019-06-07', 15, '周五', '2018-2019-2');
INSERT INTO `school_calander` VALUES (107, '2019-06-08', 16, '周六', '2018-2019-2');
INSERT INTO `school_calander` VALUES (108, '2019-06-09', 16, '周日', '2018-2019-2');
INSERT INTO `school_calander` VALUES (109, '2019-06-10', 16, '周一', '2018-2019-2');
INSERT INTO `school_calander` VALUES (110, '2019-06-11', 16, '周二', '2018-2019-2');
INSERT INTO `school_calander` VALUES (111, '2019-06-12', 16, '周三', '2018-2019-2');
INSERT INTO `school_calander` VALUES (112, '2019-06-13', 16, '周四', '2018-2019-2');
INSERT INTO `school_calander` VALUES (113, '2019-06-14', 16, '周五', '2018-2019-2');
INSERT INTO `school_calander` VALUES (114, '2019-06-15', 17, '周六', '2018-2019-2');
INSERT INTO `school_calander` VALUES (115, '2019-06-16', 17, '周日', '2018-2019-2');
INSERT INTO `school_calander` VALUES (116, '2019-06-17', 17, '周一', '2018-2019-2');
INSERT INTO `school_calander` VALUES (117, '2019-06-18', 17, '周二', '2018-2019-2');
INSERT INTO `school_calander` VALUES (118, '2019-06-19', 17, '周三', '2018-2019-2');
INSERT INTO `school_calander` VALUES (119, '2019-06-20', 17, '周四', '2018-2019-2');
INSERT INTO `school_calander` VALUES (120, '2019-06-21', 17, '周五', '2018-2019-2');
INSERT INTO `school_calander` VALUES (121, '2019-06-22', 18, '周六', '2018-2019-2');
INSERT INTO `school_calander` VALUES (122, '2019-06-23', 18, '周日', '2018-2019-2');
INSERT INTO `school_calander` VALUES (123, '2019-06-24', 18, '周一', '2018-2019-2');
INSERT INTO `school_calander` VALUES (124, '2019-06-25', 18, '周二', '2018-2019-2');
INSERT INTO `school_calander` VALUES (125, '2019-06-26', 18, '周三', '2018-2019-2');
INSERT INTO `school_calander` VALUES (126, '2019-06-27', 18, '周四', '2018-2019-2');
INSERT INTO `school_calander` VALUES (127, '2019-06-28', 18, '周五', '2018-2019-2');
INSERT INTO `school_calander` VALUES (128, '2019-06-29', 19, '周六', '2018-2019-2');
INSERT INTO `school_calander` VALUES (129, '2019-06-30', 19, '周日', '2018-2019-2');
INSERT INTO `school_calander` VALUES (130, '2019-07-01', 19, '周一', '2018-2019-2');
INSERT INTO `school_calander` VALUES (131, '2019-07-02', 19, '周二', '2018-2019-2');
INSERT INTO `school_calander` VALUES (132, '2019-07-03', 19, '周三', '2018-2019-2');
INSERT INTO `school_calander` VALUES (133, '2019-07-04', 19, '周四', '2018-2019-2');
INSERT INTO `school_calander` VALUES (134, '2019-07-05', 19, '周五', '2018-2019-2');
INSERT INTO `school_calander` VALUES (135, '2019-07-06', 20, '周六', '2018-2019-2');
INSERT INTO `school_calander` VALUES (136, '2019-07-07', 20, '周日', '2018-2019-2');

-- ----------------------------
-- Table structure for use_record
-- ----------------------------
DROP TABLE IF EXISTS `use_record`;
CREATE TABLE `use_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_return` int(2) NULL DEFAULT 0,
  `borrow_time` datetime(0) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `room_id` int(10) NULL DEFAULT NULL,
  `return_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `room_id`(`room_id`) USING BTREE,
  CONSTRAINT `use_record_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `use_record_ibfk_3` FOREIGN KEY (`room_id`) REFERENCES `classroom` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of use_record
-- ----------------------------
INSERT INTO `use_record` VALUES (1, 1, '2019-03-21 20:49:17', 12315, 1004, '2019-03-31 20:00:33');
INSERT INTO `use_record` VALUES (3, 1, '2019-03-21 21:33:17', 12301, 1006, '2019-03-31 20:00:37');
INSERT INTO `use_record` VALUES (4, 1, '2019-03-30 14:19:00', 12313, 1007, '2019-03-31 20:00:40');
INSERT INTO `use_record` VALUES (59, 1, '2019-04-06 15:41:06', 12316, 1015, '2019-04-06 16:03:23');
INSERT INTO `use_record` VALUES (81, 1, '2019-05-02 10:39:54', 12313, 1004, '2019-05-04 10:40:09');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '教师唯一的id',
  `worknum` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '工号',
  `name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nickname` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `department` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_admin` int(1) NOT NULL DEFAULT 0 COMMENT '1表示admin，0表示用户',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `head_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `worknum`) USING BTREE,
  INDEX `id`(`id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12319 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (12301, '1796245', '张本得', '孙悟空', '18269874632', '2095103261@qq.com', '电信学院', 2, '156236', '');
INSERT INTO `user` VALUES (12313, '17522456245', '张伯伦', '至尊宝1232', '185161263232', '2095103261@qq.com', '网络中心', 1, '1546312320', 'E:/Java/JavaProject/myproject/src/main/resources/static/userImage/17522456245_1552117620839.jpg');
INSERT INTO `user` VALUES (12315, '172219605220', '我IC你', '至尊宝', '185161263232', '4161626458@qq.com', '网络中心', 0, '154631232', '');
INSERT INTO `user` VALUES (12316, '172219605219', '鲁班', '小鲁班', '185161263232', '4161626458@qq.com', '网络中心', 0, '123456', '');
INSERT INTO `user` VALUES (12317, '172219605215', '喵小喵', '爱吃鱼', '1266623259', '162316ds@q.com', '冶材院', 0, '123456', NULL);
INSERT INTO `user` VALUES (12318, '17229616231262', '赵云', '小喽啰', '49652323', '45612632312', '电信院', 0, '1234567', '');

-- ----------------------------
-- View structure for Record
-- ----------------------------
DROP VIEW IF EXISTS `Record`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `Record` AS select `user`.`worknum` AS `worknum`,`user`.`name` AS `name`,`classroom`.`schoolarea` AS `schoolarea`,`classroom`.`roomnum` AS `roomnum`,`use_record`.`borrow_time` AS `borrow_time`,`use_record`.`card_return` AS `card_return`,`use_record`.`return_time` AS `return_time` from ((`use_record` join `user` on((`use_record`.`user_id` = `user`.`id`))) join `classroom` on((`use_record`.`room_id` = `classroom`.`id`)));

-- ----------------------------
-- View structure for roomlist
-- ----------------------------
DROP VIEW IF EXISTS `roomlist`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `roomlist` AS select `sc`.`date` AS `date`,`sc`.`weeknum` AS `weeknum`,`ct`.`weekday` AS `weekday`,`ct`.`class_order` AS `class_order`,`cr`.`schoolarea` AS `schoolarea`,`ct`.`room` AS `room`,`ct`.`user` AS `user` from ((`classroom` `cr` join `classtable` `ct` on(((`ct`.`room` = `cr`.`roomnum`) and (`cr`.`is_use` = 1) and (`cr`.`health` = 1)))) join `school_calander` `sc` on(((`sc`.`weeknum` = `ct`.`weeknum`) and (convert(`sc`.`term` using utf8mb4) = convert(`ct`.`term` using utf8mb4)) and (`sc`.`weekday` = `ct`.`weekday`))));

SET FOREIGN_KEY_CHECKS = 1;
