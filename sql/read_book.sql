/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : readworld

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-06-05 19:05:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for read_book
-- ----------------------------
DROP TABLE IF EXISTS `read_book`;
CREATE TABLE `read_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(255) DEFAULT NULL COMMENT '书名',
  `book_writer` varchar(100) DEFAULT NULL COMMENT '作者',
  `book_publisher` varchar(100) DEFAULT NULL COMMENT '出版社',
  `release_date` varchar(30) DEFAULT NULL COMMENT '出版日期',
  `isbn` varchar(50) DEFAULT NULL COMMENT 'ISBN号',
  `topic` varchar(100) DEFAULT NULL COMMENT '主题',
  `book_description` varchar(2000) DEFAULT NULL COMMENT '内容概述',
  `publisher_resource` varchar(255) DEFAULT NULL,
  `about_publisher` varchar(1000) DEFAULT NULL COMMENT '关于作者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `state` varchar(10) DEFAULT NULL COMMENT '状态',
  `img_url` varchar(255) DEFAULT NULL COMMENT '图片链接',
  `down_state` varchar(10) DEFAULT NULL COMMENT '下载状态01:允许下载',
  `read_state` varchar(10) DEFAULT NULL COMMENT '阅读状态01:允许阅读,02不允许',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
