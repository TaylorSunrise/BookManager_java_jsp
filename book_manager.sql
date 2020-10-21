/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : book_manager

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2020-05-19 13:59:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `aid` int(64) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `sex` varchar(8) DEFAULT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `lastdate` datetime DEFAULT NULL,
  `flag` int(64) DEFAULT NULL,
  `status` int(64) DEFAULT NULL,
  `remarks` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('2', 'admin', 'EAC4108912AF90AE96E858190F4D8AF7', '女', '18774585269', 'admin2@qq.com', '2020-05-19 13:21:49', '1', '1', null);
INSERT INTO `admin` VALUES ('3', 'admin3', '070DA0ABADC870E4D3EB2FEBE7777C48', '女', '187745852615', 'admin2@qq.com', '2020-05-09 22:24:43', null, '1', null);
INSERT INTO `admin` VALUES ('4', 'admin4', 'F33DD51E5646AA99475A4A2C71F9653C', '女', '187745852615', 'admin2@qq.com', '2020-05-09 22:35:51', null, '1', null);
INSERT INTO `admin` VALUES ('29', 'admin5', 'E453EE6F17697474C10116E0F7F3CDBD', '男', '18775466515', 'aa@qq.com', '2020-05-10 23:01:59', '1', '1', '1');
INSERT INTO `admin` VALUES ('30', 'admin7', '5B39C2757E37D0DC4D3895EEA71691E8', '女', '18775466515', 'aa@qq.com', '2020-05-10 23:07:47', '1', '1', '111');
INSERT INTO `admin` VALUES ('31', 'admin6', '4D6027751857D39D016C1F4857F986AA', '男', '18775466515', 'aa@qq.com', '2020-05-10 23:08:38', '2', '1', '1');
INSERT INTO `admin` VALUES ('32', 'admin8', '46A04356601CF0A9B04E4BA037D8C151', '男', '18775466515', 'aa@qq.com', '2020-05-14 17:43:36', '1', '1', '111');
INSERT INTO `admin` VALUES ('35', 'taylor', 'C21904AAF18C3223C761D93892DEDF9E', '男', '18775466515', 'aa@qq.com', '2020-05-19 13:25:00', '1', '1', '111');

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
  `bid` int(64) NOT NULL AUTO_INCREMENT,
  `iid` int(64) DEFAULT NULL,
  `admin_name` varchar(64) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `status` int(32) DEFAULT NULL,
  `note` varchar(64) DEFAULT NULL,
  `imagepath` varchar(128) DEFAULT NULL,
  `author` varchar(64) DEFAULT NULL,
  `publish` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES ('17', '2', 'admin4', 'php', '2020-05-09 17:49:03', '1', 'phphph', './imagepath/1589348943695.jpg', '鲁迅', '工业出版社');
INSERT INTO `books` VALUES ('19', '2', 'admin4', 'php', '2020-05-09 17:52:28', '1', 'hpp', './imagepath/1589348943695.jpg', '张飞', '电子出版社');
INSERT INTO `books` VALUES ('20', '3', 'admin', 'java4', '2020-05-09 22:12:17', '1', 'dsfsfsfsdf', './imagepath/1589859245075.jpg', '李白', '工业出版社');
INSERT INTO `books` VALUES ('22', '2', 'admin3', '和平', '2020-05-09 22:25:16', '2', '32323', './imagepath/1589348943695.jpg', '李白', '工业出版社');
INSERT INTO `books` VALUES ('24', '2', 'admin', 'java1', '2020-05-13 13:49:03', '1', '1111', './imagepath/1589348943695.jpg', '鲁迅', '电子出版社');
INSERT INTO `books` VALUES ('25', '3', 'admin', 'java2', '2020-05-13 15:53:53', '1', '节哀吧v', './imagepath/1589356433148.jpg', 'UC小编', '新华社');
INSERT INTO `books` VALUES ('26', '4', 'admin', 'php2', '2020-05-13 15:54:31', '1', '12', './imagepath/1589356471362.jpg', 'UC小编', '人民日报');
INSERT INTO `books` VALUES ('27', '2', 'admin', 'web2', '2020-05-13 15:55:42', '1', '12', './imagepath/1589356542576.jpg', 'UC小编', '新华');
INSERT INTO `books` VALUES ('31', '5', 'admin', '近代史', '2020-05-14 21:37:13', '1', '11', './imagepath/1589463433436.jpg', 'UC小编', '新华');
INSERT INTO `books` VALUES ('32', '5', 'admin', '图像学', '2020-05-14 21:51:11', '1', '安', './imagepath/1589464271763.jpg', '鲁迅', '新华');
INSERT INTO `books` VALUES ('33', '5', 'admin', '失色6', '2020-05-14 21:53:58', '1', '的方式', './imagepath/1589853847952.jpg', '鲁迅', '新华');
INSERT INTO `books` VALUES ('34', '3', 'admin', 'java5', '2020-05-19 13:22:13', '1', '阿斯蒂芬', './imagepath/1589865733268.jpg', 'UC小编', '工业出版社');

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `iid` int(64) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `note` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`iid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('3', '科学', '撒打发发达');
INSERT INTO `item` VALUES ('5', 'php', '4325453242424242424242424242424242424242424242424242424245');
INSERT INTO `item` VALUES ('6', 'java web', '深刻搭街坊瞬间大幅攀升的覅就ask砥砺奋进手动阀手动阀');

-- ----------------------------
-- Table structure for lenbook
-- ----------------------------
DROP TABLE IF EXISTS `lenbook`;
CREATE TABLE `lenbook` (
  `leid` int(64) NOT NULL AUTO_INCREMENT,
  `bid` int(64) DEFAULT NULL,
  `mid` int(64) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `retdate` datetime DEFAULT NULL,
  `retday` int(64) DEFAULT NULL,
  `retstatus` varchar(64) DEFAULT NULL,
  `creditno` int(64) DEFAULT NULL,
  `remarks` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`leid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lenbook
-- ----------------------------
INSERT INTO `lenbook` VALUES ('2', '0', '1', '2020-05-09 15:25:10', null, '1', null, '0', '21');
INSERT INTO `lenbook` VALUES ('3', '1', '2', '2020-05-09 15:41:37', '2020-05-09 15:41:43', '1', '按时', '0', '1');
INSERT INTO `lenbook` VALUES ('5', '17', '4', '2020-05-09 22:10:28', '2020-05-14 16:45:25', '1', '逾期', '0', '2');
INSERT INTO `lenbook` VALUES ('8', '21', '2', '2020-05-14 08:28:23', '2020-05-14 16:46:54', '2', '按时', '0', '1');
INSERT INTO `lenbook` VALUES ('9', '20', '2', '2020-05-14 08:29:03', '2020-05-14 16:53:25', '1', '按时', '0', '1');
INSERT INTO `lenbook` VALUES ('10', '17', '3', '2020-05-14 08:57:21', '2020-05-14 16:56:09', '1', '按时', '0', '1');
INSERT INTO `lenbook` VALUES ('11', '17', '4', '2020-05-14 08:57:33', '2020-05-14 16:58:30', '1', '按时', '0', '');
INSERT INTO `lenbook` VALUES ('12', '23', '2', '2020-05-14 18:11:32', '2020-05-14 18:11:57', '1', '按时', '0', '');
INSERT INTO `lenbook` VALUES ('13', '21', '2', '2020-05-14 18:56:25', '2020-05-14 18:56:32', '1', '按时', '0', '1');
INSERT INTO `lenbook` VALUES ('14', '23', '2', '2020-05-14 21:28:57', '2020-05-14 21:29:07', '1', '按时', '0', '');
INSERT INTO `lenbook` VALUES ('15', '22', '3', '2020-05-14 21:29:48', '2020-05-17 21:37:26', '1', '逾期', '0', '');
INSERT INTO `lenbook` VALUES ('16', '19', '5', '2020-05-19 13:23:38', '2020-05-19 13:24:09', '1', '按时', '0', '啊');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `mid` int(64) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `age` int(8) DEFAULT NULL,
  `sex` varchar(8) DEFAULT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `creditno` int(64) DEFAULT NULL,
  `num` int(64) DEFAULT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('1', '刘邦', '19', '女', '18775466515', '90', '3');
INSERT INTO `member` VALUES ('2', '李四', '64', '男', '18775466515', '30', '8');
INSERT INTO `member` VALUES ('3', '王五', '19', '女', '18775466515', '0', '4');
INSERT INTO `member` VALUES ('5', '序号六', '19', '女', '18775466515', '0', '3');
INSERT INTO `member` VALUES ('6', '大锤', '43', '男', '18775466515', '90', '3');
