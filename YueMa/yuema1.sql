/*
Navicat MySQL Data Transfer

Source Server         : Graduate  Design
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : yuema1

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2015-03-22 16:15:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `activity_label`
-- ----------------------------
DROP TABLE IF EXISTS `activity_label`;
CREATE TABLE `activity_label` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_id` int(11) NOT NULL,
  `label_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity_label
-- ----------------------------

-- ----------------------------
-- Table structure for `activity_picture`
-- ----------------------------
DROP TABLE IF EXISTS `activity_picture`;
CREATE TABLE `activity_picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_id` int(11) NOT NULL,
  `picture_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity_picture
-- ----------------------------

-- ----------------------------
-- Table structure for `joiner`
-- ----------------------------
DROP TABLE IF EXISTS `joiner`;
CREATE TABLE `joiner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `activity_id` int(11) NOT NULL,
  `join_time` datetime NOT NULL,
  `is_tick_off` int(11) DEFAULT NULL,
  `isAuth` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user` (`user_id`),
  KEY `fk_cctivity` (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of joiner
-- ----------------------------

-- ----------------------------
-- Table structure for `yactivity`
-- ----------------------------
DROP TABLE IF EXISTS `yactivity`;
CREATE TABLE `yactivity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creator_id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `introduce` varchar(100) DEFAULT NULL,
  `activity_address` varchar(100) NOT NULL,
  `activity_ddress_Longitude` varchar(50) DEFAULT NULL,
  `collect_address_latitude` varchar(50) DEFAULT NULL,
  `collect_address` varchar(100) NOT NULL,
  `collect_ddress_Longitude` varchar(50) DEFAULT NULL,
  `collect_ddress_latitude` varchar(50) DEFAULT NULL,
  `max_count` int(11) NOT NULL,
  `current_count` int(11) NOT NULL,
  `per_cost` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yactivity
-- ----------------------------

-- ----------------------------
-- Table structure for `ybulletin`
-- ----------------------------
DROP TABLE IF EXISTS `ybulletin`;
CREATE TABLE `ybulletin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usertype` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `content` text,
  `createtime` datetime NOT NULL,
  `iscancel` int(11) DEFAULT NULL,
  `canceltime` datetime DEFAULT NULL,
  `isdelete` int(11) DEFAULT NULL,
  `deletetime` datetime DEFAULT NULL,
  `priority` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通告表\r\n\r\n编号\r\n标题\r\n内容\r\n发布时间\r\n是否撤销\r\n撤销时间\r\n是否删除\r\n删除时间\r\n优先级\r\n\r\n';

-- ----------------------------
-- Records of ybulletin
-- ----------------------------

-- ----------------------------
-- Table structure for `ybulletinuser`
-- ----------------------------
DROP TABLE IF EXISTS `ybulletinuser`;
CREATE TABLE `ybulletinuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Bulletin_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `isread` int(11) NOT NULL,
  `readtime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通告对象表\r\n\r\n通告编号\r\n(user_id)  标志需要发送的用户的用户编号\r\n是否阅读\r\n阅读时间\r\n';

-- ----------------------------
-- Records of ybulletinuser
-- ----------------------------

-- ----------------------------
-- Table structure for `ycomment`
-- ----------------------------
DROP TABLE IF EXISTS `ycomment`;
CREATE TABLE `ycomment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(100) DEFAULT NULL,
  `creator_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `is_delete` int(11) NOT NULL,
  `father_comment_id` int(11) NOT NULL,
  `activity_id` int(11) NOT NULL,
  `comment_path` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='比如评论表\r\n\r\n评论id  评论主题    评论内容   删除标志  父评论id  评论路径    帖子id\r\n1	aa	aa	0	0	0	111\r\n2	bb	bb	0	1	1	111\r\n3	cc	cc	0	2	1,2	111搜索\r\n\r\n这样查询某个评论可以清楚的知道其评论路径，也可以知道它的帖子i';

-- ----------------------------
-- Records of ycomment
-- ----------------------------

-- ----------------------------
-- Table structure for `ycontact`
-- ----------------------------
DROP TABLE IF EXISTS `ycontact`;
CREATE TABLE `ycontact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hostNum` varchar(50) NOT NULL,
  `friendNum` varchar(50) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `isuser` int(11) NOT NULL,
  `hostName` varchar(50) NOT NULL,
  `friendName` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COMMENT='version: 版本号\r\nisUser 标记是否已经注册\r\n注册为1\r\n没注册为0';

-- ----------------------------
-- Records of ycontact
-- ----------------------------
INSERT INTO `ycontact` VALUES ('48', 'A', 'D', null, '0', '', '');
INSERT INTO `ycontact` VALUES ('51', 'V', 'D', null, '0', '', '');
INSERT INTO `ycontact` VALUES ('52', 'A', 'B', null, '0', '', '');
INSERT INTO `ycontact` VALUES ('53', 'C', 'D', null, '0', '', '');
INSERT INTO `ycontact` VALUES ('54', 'B', 'C', null, '0', '', '');

-- ----------------------------
-- Table structure for `ylabel`
-- ----------------------------
DROP TABLE IF EXISTS `ylabel`;
CREATE TABLE `ylabel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(10) NOT NULL,
  `createor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ylabel
-- ----------------------------

-- ----------------------------
-- Table structure for `ymessage`
-- ----------------------------
DROP TABLE IF EXISTS `ymessage`;
CREATE TABLE `ymessage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `createtime` datetime NOT NULL,
  `creator_id` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ymessage
-- ----------------------------

-- ----------------------------
-- Table structure for `ymessageuser`
-- ----------------------------
DROP TABLE IF EXISTS `ymessageuser`;
CREATE TABLE `ymessageuser` (
  `id` int(11) DEFAULT NULL,
  `messge_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `isread` int(11) NOT NULL,
  `readtime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ymessageuser
-- ----------------------------

-- ----------------------------
-- Table structure for `ypicture`
-- ----------------------------
DROP TABLE IF EXISTS `ypicture`;
CREATE TABLE `ypicture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `upload_time` datetime NOT NULL,
  `url` text NOT NULL,
  `creator_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='如果为0，表示为活动的图片\r\n如果为1，表示为分享的图片';

-- ----------------------------
-- Records of ypicture
-- ----------------------------

-- ----------------------------
-- Table structure for `yrelationsecond`
-- ----------------------------
DROP TABLE IF EXISTS `yrelationsecond`;
CREATE TABLE `yrelationsecond` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hostNum` varchar(50) NOT NULL,
  `friendNum` varchar(50) NOT NULL,
  `middle` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='二度人脉关系必须在当前用户的基础上才有，非用户没二度关系';

-- ----------------------------
-- Records of yrelationsecond
-- ----------------------------
INSERT INTO `yrelationsecond` VALUES ('42', '', '', '');

-- ----------------------------
-- Table structure for `yreminde`
-- ----------------------------
DROP TABLE IF EXISTS `yreminde`;
CREATE TABLE `yreminde` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `createtime` datetime NOT NULL,
  `type` int(11) NOT NULL,
  `creator_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提醒表\r\ncontent ：内容（当有新成员加入和有新的请求添加关系时候）\r\ncreatetime：创建时间';

-- ----------------------------
-- Records of yreminde
-- ----------------------------

-- ----------------------------
-- Table structure for `yreminduser`
-- ----------------------------
DROP TABLE IF EXISTS `yreminduser`;
CREATE TABLE `yreminduser` (
  `id` int(11) DEFAULT NULL,
  `remind_id` int(11) DEFAULT NULL,
  `isread` int(11) DEFAULT NULL,
  `readtime` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通告对象表\r\n\r\n通告编号\r\n用户编号\r\n是否阅读\r\n阅读时间\r\n';

-- ----------------------------
-- Records of yreminduser
-- ----------------------------

-- ----------------------------
-- Table structure for `yuser`
-- ----------------------------
DROP TABLE IF EXISTS `yuser`;
CREATE TABLE `yuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `phonenum` varchar(20) NOT NULL,
  `face` text NOT NULL,
  `introduce` varchar(50) DEFAULT NULL,
  `createtime` datetime NOT NULL,
  `registercode` varchar(4) NOT NULL,
  `lastLoginTime` datetime DEFAULT NULL,
  `lastloginIP` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yuser
-- ----------------------------
