/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50539
Source Host           : localhost:3306
Source Database       : management

Target Server Type    : MYSQL
Target Server Version : 50539
File Encoding         : 65001

Date: 2016-09-02 18:48:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `loginname` varchar(255) DEFAULT NULL COMMENT '登录名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `phone` varchar(255) DEFAULT NULL COMMENT 'l联系电话',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'fengyongang', 'fygang0424@gmail.com', '4297f44b13955235245b2497399d7a93', '18366132455', 'fygang0424@gmail.com');
