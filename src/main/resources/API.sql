/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.20-log : Database - mybatisdemo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mybatisdemo` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mybatisdemo`;

/*Table structure for table `account_item` */

DROP TABLE IF EXISTS `account_item`;

CREATE TABLE `account_item` (
  `account_item_id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `api_id` varchar(32) NOT NULL,
  `combo_id` varbinary(32) NOT NULL,
  `record_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  PRIMARY KEY (`account_item_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `consumer` (`consumer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `account_item` */

/*Table structure for table `api` */

DROP TABLE IF EXISTS `api`;

CREATE TABLE `api` (
  `api_id` varchar(32) NOT NULL,
  `sp_id` varchar(32) NOT NULL,
  `api_token` varchar(100) DEFAULT NULL,
  `api_max_in` int(10) DEFAULT NULL COMMENT '访问控制',
  `api_enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT 'api是否可用',
  `api_description` text,
  `api_strip_prefix` int(1) unsigned zerofill DEFAULT NULL COMMENT '是否将前缀加入映射后的路径',
  `api_retryable` int(1) DEFAULT NULL,
  `api_path` varchar(255) NOT NULL COMMENT '访问代理使用的路径',
  `api_name` varchar(255) NOT NULL,
  `api_bill_type` int(2) NOT NULL COMMENT 'api记账类型，按次数：1，按流量M',
  `api_sys_price` float NOT NULL COMMENT 'api单价',
  `api_method` int(1) NOT NULL DEFAULT '0' COMMENT '0：get 1：post，2：两种都有',
  `api_url` varchar(255) NOT NULL,
  `api_return_pattern` varchar(30) NOT NULL,
  `api_normal_return_demo` text,
  `api_error_return_demo` text,
  PRIMARY KEY (`api_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `api` */

insert  into `api`(`api_id`,`sp_id`,`api_token`,`api_max_in`,`api_enabled`,`api_description`,`api_strip_prefix`,`api_retryable`,`api_path`,`api_name`,`api_bill_type`,`api_sys_price`,`api_method`,`api_url`,`api_return_pattern`,`api_normal_return_demo`,`api_error_return_demo`) values ('1a6ffdb51dd742bf9b58b9d4d9f859f1','c430c9776a934ff1a856360185920c5d','111111',11,1,'1',1,1,'/1a6ffdb51dd742bf9b58b9d4d9f859f1/**','add',1,1,0,'http://127.0.0.1:8090/available','0','1','1'),('dcb2e7e79d58470e819a2da6ea2a89d3','c430c9776a934ff1a856360185920c5d','1',11,1,'1',1,1,'/dcb2e7e79d58470e819a2da6ea2a89d3/**','1',1,1,0,'1','0','1','1'),('ef7deaca96d94cfeb21c1985c44525db','c430c9776a934ff1a856360185920c5d','11111',100,1,'简单的加法',1,1,'/ef7deaca96d94cfeb21c1985c44525db/**','加法',1,1,0,'http://127.0.0.1:8090/add','0','2','error');

/*Table structure for table `api_app` */

DROP TABLE IF EXISTS `api_app`;

CREATE TABLE `api_app` (
  `api_id` varchar(32) NOT NULL,
  `app_id` varchar(32) NOT NULL,
  `create_date` datetime NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`api_id`,`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `api_app` */

/*Table structure for table `api_auth` */

DROP TABLE IF EXISTS `api_auth`;

CREATE TABLE `api_auth` (
  `api_id` varchar(32) NOT NULL,
  `app_id` bigint(12) NOT NULL,
  `remain` int(16) DEFAULT NULL,
  PRIMARY KEY (`api_id`,`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `api_auth` */

/*Table structure for table `api_request_param` */

DROP TABLE IF EXISTS `api_request_param`;

CREATE TABLE `api_request_param` (
  `api_id` varchar(32) NOT NULL,
  `api_param` varchar(255) NOT NULL,
  `api_param_demo` varchar(255) NOT NULL,
  `api_param_position` varchar(30) DEFAULT 'path',
  `api_is_must` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `api_request_param` */

insert  into `api_request_param`(`api_id`,`api_param`,`api_param_demo`,`api_param_position`,`api_is_must`) values ('508a6640781b4958a452842d3387ca71','1','2','path',1),('508a6640781b4958a452842d3387ca71','3','4','path',1),('02c79ec86b46432b8382219b45172c9a','1','1','path',1),('02c79ec86b46432b8382219b45172c9a','2','2','path',1),('25b2b9ac290a4b1d8b67396b6be8fa25','1','1','path',1),('ef7deaca96d94cfeb21c1985c44525db','','','path',1),('44bd717027a640b5b795e2352767161b','','','path',1),('c9e6e28621c64d038a4c80b50ae4d261','','','path',1),('ab2c0fab05da48ff959ad8bd92e403fb','','','path',1),('bdd5c611a3e84c4c86a57291482e75fa','','','path',1),('dcb2e7e79d58470e819a2da6ea2a89d3','1','2','path',1),('1a6ffdb51dd742bf9b58b9d4d9f859f1','1','2','path',1);

/*Table structure for table `api_sys_price` */

DROP TABLE IF EXISTS `api_sys_price`;

CREATE TABLE `api_sys_price` (
  `api_id` varchar(32) NOT NULL,
  `unit_price` float NOT NULL,
  `bill_type` int(2) NOT NULL COMMENT '记账方式：1按条，2按流量/M，3按照访问时间/hour',
  `price_id` varchar(32) NOT NULL,
  PRIMARY KEY (`price_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `api_sys_price` */

/*Table structure for table `api_user_price` */

DROP TABLE IF EXISTS `api_user_price`;

CREATE TABLE `api_user_price` (
  `api_id` varchar(32) NOT NULL,
  `unit_price` float NOT NULL,
  `bill_type` int(2) NOT NULL COMMENT '记账方式：1按条，2按流量/M，3按照访问时间/hour',
  `price_id` varchar(32) NOT NULL,
  PRIMARY KEY (`price_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `api_user_price` */

/*Table structure for table `app` */

DROP TABLE IF EXISTS `app`;

CREATE TABLE `app` (
  `appid` bigint(12) NOT NULL AUTO_INCREMENT,
  `appsecret` varchar(32) NOT NULL,
  `app_decription` text NOT NULL,
  `app_name` varchar(128) NOT NULL DEFAULT '"null"',
  PRIMARY KEY (`appid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `app` */

/*Table structure for table `bill_type` */

DROP TABLE IF EXISTS `bill_type`;

CREATE TABLE `bill_type` (
  `bill_type_code` int(1) NOT NULL,
  `type` varchar(30) NOT NULL,
  PRIMARY KEY (`bill_type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bill_type` */

insert  into `bill_type`(`bill_type_code`,`type`) values (0,'按照访问次数计费'),(1,'按流量计费');

/*Table structure for table `consumer` */

DROP TABLE IF EXISTS `consumer`;

CREATE TABLE `consumer` (
  `consumer_id` varchar(32) NOT NULL,
  `consumer_name` varchar(100) NOT NULL,
  `consumer_password` varchar(32) NOT NULL,
  `consumer_email` varchar(50) NOT NULL,
  `consumer_tel` varchar(11) NOT NULL,
  PRIMARY KEY (`consumer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `consumer` */

insert  into `consumer`(`consumer_id`,`consumer_name`,`consumer_password`,`consumer_email`,`consumer_tel`) values ('87de254182574856af64d323869b686d','姬喜洋','woshiyangyang.','1044456468@qq.com','17862700885'),('d6701b9a5b0147f9939016f84aa2e9c0','姬喜洋','1111111','1044456469@qq.com','17862700885');

/*Table structure for table `consumer_business` */

DROP TABLE IF EXISTS `consumer_business`;

CREATE TABLE `consumer_business` (
  `consumer_business_id` varchar(32) NOT NULL,
  `consumer_id` varchar(32) NOT NULL,
  `consumer_combo_id` varchar(32) NOT NULL,
  `consumer_combo_remain` bigint(20) NOT NULL,
  `consumer_business_begin_time` datetime DEFAULT NULL,
  `consumer_business_end_time` datetime DEFAULT NULL,
  `token` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`consumer_business_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `consumer_business` */

/*Table structure for table `consumer_combo` */

DROP TABLE IF EXISTS `consumer_combo`;

CREATE TABLE `consumer_combo` (
  `combo_id` varchar(32) NOT NULL,
  `api_id` varchar(32) NOT NULL,
  `content` bigint(20) NOT NULL DEFAULT '0',
  `combo_price` float NOT NULL,
  `combo_time` int(10) DEFAULT NULL COMMENT '套餐周期，单位为小时',
  PRIMARY KEY (`combo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `consumer_combo` */

/*Table structure for table `gateway_api_define` */

DROP TABLE IF EXISTS `gateway_api_define`;

CREATE TABLE `gateway_api_define` (
  `id` varchar(50) NOT NULL,
  `path` varchar(255) NOT NULL,
  `service_id` varchar(50) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `retryable` tinyint(1) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL,
  `strip_prefix` int(11) DEFAULT NULL,
  `api_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `gateway_api_define` */

insert  into `gateway_api_define`(`id`,`path`,`service_id`,`url`,`retryable`,`enabled`,`strip_prefix`,`api_name`) values ('book1','/book',NULL,'http://localhost:8000',0,1,1,NULL),('myapiplatform','/api/**',NULL,'http://localhost:8082',0,1,1,NULL),('new','/mybatis/**',NULL,'http://localhost:8081',0,1,1,NULL),('pppp','/pppp/**',NULL,'http://localhost:8090/available',0,1,1,NULL);

/*Table structure for table `person` */

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(50) DEFAULT NULL,
  `page` int(11) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `person` */

/*Table structure for table `service_provider` */

DROP TABLE IF EXISTS `service_provider`;

CREATE TABLE `service_provider` (
  `sp_id` varchar(32) NOT NULL COMMENT '外键，组织ID等于userid',
  `sp_org_id` varchar(100) DEFAULT NULL COMMENT '组织ID',
  `sp_description` text COMMENT '组织描述',
  `sp_tel` varchar(20) NOT NULL COMMENT '组织电话',
  `sp_representative` varchar(30) NOT NULL COMMENT '法人代表',
  `sp_email` varchar(50) NOT NULL,
  `sp_name` varchar(255) NOT NULL,
  `sp_password` varchar(255) NOT NULL,
  `sp_representative_id` varchar(19) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `service_provider` */

insert  into `service_provider`(`sp_id`,`sp_org_id`,`sp_description`,`sp_tel`,`sp_representative`,`sp_email`,`sp_name`,`sp_password`,`sp_representative_id`) values ('c430c9776a934ff1a856360185920c5d','123456','修改后的描述','17862700885','ICES','hitices@hitwh.com','ICES','hitices','');

/*Table structure for table `sys_combo` */

DROP TABLE IF EXISTS `sys_combo`;

CREATE TABLE `sys_combo` (
  `sys_combo_id` varchar(32) NOT NULL,
  `api_id` varchar(32) NOT NULL,
  `content` bigint(20) NOT NULL,
  `combo_price` float NOT NULL DEFAULT '0',
  `combo_remain` bigint(20) NOT NULL DEFAULT '0',
  `current_period_begin_time` datetime DEFAULT NULL,
  `current_period_end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`sys_combo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_combo` */

/*Table structure for table `tb_area` */

DROP TABLE IF EXISTS `tb_area`;

CREATE TABLE `tb_area` (
  `area_id` int(2) NOT NULL,
  `area_name` varchar(200) DEFAULT NULL,
  `priority` int(2) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `last_edit_time` date DEFAULT NULL,
  PRIMARY KEY (`area_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_area` */

insert  into `tb_area`(`area_id`,`area_name`,`priority`,`create_time`,`last_edit_time`) values (2,'西',2,'2018-03-19','2018-03-19');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
