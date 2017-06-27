/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2017-06-27 16:29:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ftpuser
-- ----------------------------
DROP TABLE IF EXISTS `ftpuser`;
CREATE TABLE `ftpuser` (
  `userid` varchar(64) NOT NULL,
  `userpassword` varchar(64) DEFAULT NULL,
  `homedirectory` varchar(128) NOT NULL,
  `enableflag` tinyint(1) DEFAULT '1',
  `writepermission` tinyint(1) DEFAULT '0',
  `idletime` int(11) DEFAULT '0',
  `uploadrate` int(11) DEFAULT '0',
  `downloadrate` int(11) DEFAULT '0',
  `maxloginnumber` int(11) DEFAULT '0',
  `maxloginperip` int(11) DEFAULT '0',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ftpuser
-- ----------------------------
INSERT INTO `ftpuser` VALUES ('1', 'C4CA4238A0B923820DCC509A6F75849B', 'C:\\ftpServer', '1', '1', '0', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('傻子', '123', '1');
