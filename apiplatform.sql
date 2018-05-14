/*
 Navicat Premium Data Transfer

 Source Server         : reservation
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : mybatisdemo

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 14/05/2018 09:49:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `account_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `api_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `app_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `others` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`account_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1111111', '1111111', '1111111', '2018-04-22 11:23:47', '备注');

-- ----------------------------
-- Table structure for api
-- ----------------------------
DROP TABLE IF EXISTS `api`;
CREATE TABLE `api`  (
  `api_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sp_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `api_token` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除，放于常量参数',
  `api_max_in` int(10) NULL DEFAULT NULL COMMENT '访问控制',
  `api_enabled` tinyint(1) NOT NULL DEFAULT 1 COMMENT 'api是否可用',
  `api_description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `api_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '访问代理使用的路径',
  `api_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `api_bill_type` int(2) NOT NULL COMMENT 'api记账类型，按次数：1，按流量M',
  `api_method` int(1) NOT NULL DEFAULT 0 COMMENT '0：get 1：post，2：两种都有',
  `api_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `api_return_pattern` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `api_normal_return_demo` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `api_error_return_demo` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `api_timeout` int(6) NOT NULL DEFAULT 30000 COMMENT 'api超时时间',
  `api_category_id` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'api分类编码，同一类api拥有相同的前缀',
  `api_average_response_time` float NULL DEFAULT 99 COMMENT '近200次访问请求的平均响应时间',
  `api_ok_response_times` int(11) NULL DEFAULT 0 COMMENT 'api在近1000次访问请求中的成功次数',
  `api_success_response_ratio` float NULL DEFAULT 0,
  `api_time_algorithm_score` float NULL DEFAULT 0,
  `api_stable_algorithm_score` float NULL DEFAULT 0 COMMENT '稳定性算法得分',
  `api_cost_algorithm_score` float NULL DEFAULT 0,
  PRIMARY KEY (`api_id`) USING BTREE,
  UNIQUE INDEX `api_id`(`api_id`) USING BTREE,
  INDEX `api_sp`(`sp_id`) USING BTREE,
  CONSTRAINT `api_sp` FOREIGN KEY (`sp_id`) REFERENCES `service_provider` (`sp_id`) ON DELETE NO ACTION ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of api
-- ----------------------------
INSERT INTO `api` VALUES ('24987fb58daa4f56b4c76669312d3e7b', 'c430c9776a934ff1a856360185920c5d', '111111111', 111, 1, 'ali天气预报', '/24987fb58daa4f56b4c76669312d3e7b/**', '天气预报', 1, 0, 'http://ali-weather.showapi.com/area-to-weather', '0', '', '', 30000, '1', 99, 0, 0, 0, 0, 0);
INSERT INTO `api` VALUES ('e1dd60ed08ff472395167e8b8c61a657', 'c430c9776a934ff1a856360185920c5d', '111111111', 1000, 1, '免费的天气预报接口', '/e1dd60ed08ff472395167e8b8c61a657/**', '天气预报（免费）', 1, 0, 'http://wthrcdn.etouch.cn/weather_mini', '0', '{\r\nzhengchang;\r\n}', '{\r\ncuowu;\r\n}', 30000, '0', 0.111076, 0, 0.993103, 0.908966, 1.01276, 0.986187);
INSERT INTO `api` VALUES ('ef7deaca96d94cfeb21c1985c44525db', 'c430c9776a934ff1a856360185920c5d', '11111', 100, 1, '简单的加法', '/ef7deaca96d94cfeb21c1985c44525db/**', '加法', 1, 0, 'http://127.0.0.1:8090/add', '0', '2', 'error', 30000, '0', 0.0798851, 0, 0.901554, 1.12941, 0.995459, 1.02204);

-- ----------------------------
-- Table structure for api_app
-- ----------------------------
DROP TABLE IF EXISTS `api_app`;
CREATE TABLE `api_app`  (
  `api_category_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `app_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` datetime(0) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`api_category_id`, `app_id`) USING BTREE,
  INDEX `app_index`(`app_id`) USING BTREE,
  CONSTRAINT `auth_app` FOREIGN KEY (`app_id`) REFERENCES `app` (`app_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of api_app
-- ----------------------------
INSERT INTO `api_app` VALUES ('e1dd60ed08ff472395167e8b8c61a657', '4', '2018-04-19 16:32:43', 1);
INSERT INTO `api_app` VALUES ('e1dd60ed08ff472395167e8b8c61a657', '5', '2018-04-19 16:33:04', 1);
INSERT INTO `api_app` VALUES ('ef7deaca96d94cfeb21c1985c44525db', '4', '2018-05-06 17:43:57', 1);

-- ----------------------------
-- Table structure for api_category
-- ----------------------------
DROP TABLE IF EXISTS `api_category`;
CREATE TABLE `api_category`  (
  `api_category_id` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `api_category_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `api_category_desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`api_category_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of api_category
-- ----------------------------
INSERT INTO `api_category` VALUES ('0', '天气预报', '天气预报');
INSERT INTO `api_category` VALUES ('1', '地理交通', '交通');

-- ----------------------------
-- Table structure for api_param
-- ----------------------------
DROP TABLE IF EXISTS `api_param`;
CREATE TABLE `api_param`  (
  `api_param_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `api_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `api_pre_param_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `api_param_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `api_after_param_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `api_pre_param_position` int(1) NOT NULL COMMENT '0:header/1:path/2:body',
  `api_after_param_position` int(1) NOT NULL COMMENT '0:header/1:path/2:body',
  `api_param_ismust` tinyint(1) NOT NULL DEFAULT 1,
  `api_param_isconstant` tinyint(1) NOT NULL,
  PRIMARY KEY (`api_param_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of api_param
-- ----------------------------
INSERT INTO `api_param` VALUES ('1', '24987fb58daa4f56b4c76669312d3e7b', '\"\"', 'APPCODE 955c221306154d6f9528d2d9373a64', 'Authorization', 0, 0, 1, 1);
INSERT INTO `api_param` VALUES ('2', 'ef7deaca96d94cfeb21c1985c44525db', '\"\"', ' testtoken', 'token', 0, 0, 1, 1);

-- ----------------------------
-- Table structure for api_price
-- ----------------------------
DROP TABLE IF EXISTS `api_price`;
CREATE TABLE `api_price`  (
  `price_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `api_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price_type` int(2) NOT NULL COMMENT '0:内部调用价格，1:外部调用价格,2:第三方结算',
  `content` float NOT NULL COMMENT '单价配额',
  `price` float NOT NULL COMMENT '单价',
  PRIMARY KEY (`price_id`) USING BTREE,
  INDEX `price_api`(`api_id`) USING BTREE,
  CONSTRAINT `price_api` FOREIGN KEY (`api_id`) REFERENCES `api` (`api_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of api_price
-- ----------------------------
INSERT INTO `api_price` VALUES ('1', 'ef7deaca96d94cfeb21c1985c44525db', 2, 100, 0.1);
INSERT INTO `api_price` VALUES ('2', 'e1dd60ed08ff472395167e8b8c61a657', 2, 100, 0.1);

-- ----------------------------
-- Table structure for app
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app`  (
  `app_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `app_secret` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `app_description` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '\"null\"',
  `consumer_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `app_create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`app_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of app
-- ----------------------------
INSERT INTO `app` VALUES ('2', 'absudbaskba', '烤面筋', '烤面筋', '11111111111111111', '2018-05-14 08:21:57');
INSERT INTO `app` VALUES ('3', 'ZBla9YwkZfGGhadi', 'aaa', 'aaa', '87de254182574856af64d323869b686d', '2018-05-14 08:21:57');
INSERT INTO `app` VALUES ('4', 'ZPdNWfNoyxiEo7cO', '11', '11', '87de254182574856af64d323869b686d', '2018-05-14 08:21:57');
INSERT INTO `app` VALUES ('5', 'j0FF6DAlFgZGffIw', 'app2', 'app2', '87de254182574856af64d323869b686d', '2018-05-14 08:21:57');
INSERT INTO `app` VALUES ('6', '7QN4Q3hP7RWpLDqk', 'app', '爱屁屁', '87de254182574856af64d323869b686d', '2018-05-14 08:21:57');

-- ----------------------------
-- Table structure for bill_item
-- ----------------------------
DROP TABLE IF EXISTS `bill_item`;
CREATE TABLE `bill_item`  (
  `bill_item_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `api_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `app_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `request_time` float NULL DEFAULT 999999,
  `response_code` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '404',
  PRIMARY KEY (`bill_item_id`) USING BTREE,
  INDEX `bill_api`(`api_id`) USING BTREE,
  INDEX `bill_app`(`app_id`) USING BTREE,
  CONSTRAINT `bill_api` FOREIGN KEY (`api_id`) REFERENCES `api` (`api_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `bill_app` FOREIGN KEY (`app_id`) REFERENCES `app` (`app_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bill_item
-- ----------------------------
INSERT INTO `bill_item` VALUES ('1525861097960fkRTgtBJlz', '2018-05-09 18:18:18', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.012, '200');
INSERT INTO `bill_item` VALUES ('1525861100163LTjUYyDBtn', '2018-05-09 18:18:20', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.02, '200');
INSERT INTO `bill_item` VALUES ('1525861107852ccjEaCm0eF', '2018-05-09 18:18:28', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1525861108165LHPoaPh90i', '2018-05-09 18:18:28', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.008, '200');
INSERT INTO `bill_item` VALUES ('15258611087104faxWFu6pP', '2018-05-09 18:18:28', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.06, '200');
INSERT INTO `bill_item` VALUES ('1525861113583bFsjt8rl5D', '2018-05-09 18:18:33', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1525861114155lXNSLqUVEz', '2018-05-09 18:18:34', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1525861114508KmXbv1EFlR', '2018-05-09 18:18:34', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.118, '200');
INSERT INTO `bill_item` VALUES ('1525861115039BMWL9yrTJ3', '2018-05-09 18:18:35', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.047, '200');
INSERT INTO `bill_item` VALUES ('1525861115567dke8wRRmEB', '2018-05-09 18:18:36', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.014, '200');
INSERT INTO `bill_item` VALUES ('15258611162652EpPQaHt7E', '2018-05-09 18:18:37', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.205, '200');
INSERT INTO `bill_item` VALUES ('1525861120731n9echI4ZP8', '2018-05-09 18:18:41', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.061, '200');
INSERT INTO `bill_item` VALUES ('1525861135422U3w0fF2dy2', '2018-05-09 18:18:55', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('15258611370003XiSsNkj7I', '2018-05-09 18:18:57', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1525861212616hj5tfbLn06', '2018-05-09 18:20:12', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.074, '200');
INSERT INTO `bill_item` VALUES ('15258612242245pyjS2eFp6', '2018-05-09 18:20:24', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.036, '200');
INSERT INTO `bill_item` VALUES ('1525861225203rSiRxwv08O', '2018-05-09 18:20:25', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.038, '200');
INSERT INTO `bill_item` VALUES ('15258612260054Sii3w7P3q', '2018-05-09 18:20:26', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.045, '200');
INSERT INTO `bill_item` VALUES ('1525861272645kSDE7lCUkh', '2018-05-09 18:21:13', 'ef7deaca96d94cfeb21c1985c44525db', '4', 1.028, '500');
INSERT INTO `bill_item` VALUES ('1525861296476lrfY8EB1UP', '2018-05-09 18:21:37', 'ef7deaca96d94cfeb21c1985c44525db', '4', 1.026, '500');
INSERT INTO `bill_item` VALUES ('1525861297851h5MoNNBy9n', '2018-05-09 18:21:38', 'ef7deaca96d94cfeb21c1985c44525db', '4', 1.022, '500');
INSERT INTO `bill_item` VALUES ('1525861299783RPC6bGYKHf', '2018-05-09 18:21:40', 'ef7deaca96d94cfeb21c1985c44525db', '4', 1.01, '500');
INSERT INTO `bill_item` VALUES ('1525861305226hky7QVTGjB', '2018-05-09 18:21:46', 'ef7deaca96d94cfeb21c1985c44525db', '4', 1.016, '500');
INSERT INTO `bill_item` VALUES ('1525867269224YLa6KwGzGq', '2018-05-09 20:01:10', 'ef7deaca96d94cfeb21c1985c44525db', '4', 1.06, '500');
INSERT INTO `bill_item` VALUES ('1525938950091OSIKwz4SjP', '2018-05-10 15:55:50', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.009, '200');
INSERT INTO `bill_item` VALUES ('1525938968198Ada7Df0Z9X', '2018-05-10 15:56:08', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.008, '200');
INSERT INTO `bill_item` VALUES ('1525938968931YbBnLBAY0w', '2018-05-10 15:56:09', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1525938969525T739Y2ZjJx', '2018-05-10 15:56:09', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1525938970077LapjjAQAxe', '2018-05-10 15:56:10', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1525939012630uaKnY1Yuva', '2018-05-10 15:56:53', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1525939015077qKOkLugdze', '2018-05-10 15:56:55', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1525939025386n1BUiBDJxu', '2018-05-10 15:57:05', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1525939029781Jl0q98k8HG', '2018-05-10 15:57:09', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1525939034895UG3gf6IkwM', '2018-05-10 15:57:15', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.008, '200');
INSERT INTO `bill_item` VALUES ('1525939043640dHdSpg9qBL', '2018-05-10 15:57:23', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.078, '200');
INSERT INTO `bill_item` VALUES ('1525939053216YnY0kM1sGj', '2018-05-10 15:57:33', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.073, '200');
INSERT INTO `bill_item` VALUES ('1525939055249slRiubn0EJ', '2018-05-10 15:57:35', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.051, '200');
INSERT INTO `bill_item` VALUES ('1525941035579gJbRrNGjLW', '2018-05-10 16:30:37', 'e1dd60ed08ff472395167e8b8c61a657', '4', 999999, '404');
INSERT INTO `bill_item` VALUES ('1525941188769MAze5UO7Gl', '2018-05-10 16:33:09', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.898, '200');
INSERT INTO `bill_item` VALUES ('1525941818151UVKg2slfnF', '2018-05-10 16:43:38', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.378, '200');
INSERT INTO `bill_item` VALUES ('1525941883245tyYgPJhFbv', '2018-05-10 16:46:30', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.134, '200');
INSERT INTO `bill_item` VALUES ('1525944413387a9wNL3iPvE', '2018-05-10 17:26:54', 'e1dd60ed08ff472395167e8b8c61a657', '4', 1.218, '200');
INSERT INTO `bill_item` VALUES ('1525944426014IVnxwOsoIy', '2018-05-10 17:27:06', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.063, '200');
INSERT INTO `bill_item` VALUES ('15259444301439vZPwOniU9', '2018-05-10 17:27:10', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.144, '200');
INSERT INTO `bill_item` VALUES ('1525944433629OrfiM1o8Vt', '2018-05-10 17:27:13', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.06, '200');
INSERT INTO `bill_item` VALUES ('1525944438942x35MCIYelw', '2018-05-10 17:27:19', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.566, '200');
INSERT INTO `bill_item` VALUES ('1525944443543JLRjRhGdJM', '2018-05-10 17:27:23', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.059, '200');
INSERT INTO `bill_item` VALUES ('1525944449284jaoyGl1Q0W', '2018-05-10 17:27:29', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.036, '200');
INSERT INTO `bill_item` VALUES ('1525944475363jyhAcfyRzr', '2018-05-10 17:27:55', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.06, '200');
INSERT INTO `bill_item` VALUES ('15259444764948Ud43fXXDs', '2018-05-10 17:27:56', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.049, '200');
INSERT INTO `bill_item` VALUES ('1525944478185s8l1P9ZAJO', '2018-05-10 17:27:58', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.072, '200');
INSERT INTO `bill_item` VALUES ('1525944499399nbQsamQpyn', '2018-05-10 17:28:29', 'ef7deaca96d94cfeb21c1985c44525db', '4', 10.403, '500');
INSERT INTO `bill_item` VALUES ('1525944516323Z8wqdB23it', '2018-05-10 17:28:41', 'ef7deaca96d94cfeb21c1985c44525db', '4', 5.546, '200');
INSERT INTO `bill_item` VALUES ('1525944528558iEZX88RiWO', '2018-05-10 17:28:48', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.08, '200');
INSERT INTO `bill_item` VALUES ('15259445299576hUTEJHomg', '2018-05-10 17:28:50', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.281, '200');
INSERT INTO `bill_item` VALUES ('1525944532401BNXKC91842', '2018-05-10 17:28:53', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.361, '200');
INSERT INTO `bill_item` VALUES ('1525944534399YRhUC9dFS2', '2018-05-10 17:28:55', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.125, '200');
INSERT INTO `bill_item` VALUES ('15259445361575u0t1kTDsS', '2018-05-10 17:28:56', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.179, '200');
INSERT INTO `bill_item` VALUES ('15259445383494pAV8OXWqD', '2018-05-10 17:28:58', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.532, '200');
INSERT INTO `bill_item` VALUES ('1525944540246d8uyWcQW9Q', '2018-05-10 17:29:00', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.306, '200');
INSERT INTO `bill_item` VALUES ('15259445413267MwbOWC6D2', '2018-05-10 17:29:01', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.135, '200');
INSERT INTO `bill_item` VALUES ('1525944542564HjVBpfqkJZ', '2018-05-10 17:29:02', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.032, '200');
INSERT INTO `bill_item` VALUES ('1525944543404G9uuGMPkmg', '2018-05-10 17:29:03', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('15259445446406a1U5vMHiQ', '2018-05-10 17:29:04', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.084, '200');
INSERT INTO `bill_item` VALUES ('1525944545665lP9HNZZJ1V', '2018-05-10 17:29:05', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.044, '200');
INSERT INTO `bill_item` VALUES ('1525944546658DbCYIqzNqZ', '2018-05-10 17:29:06', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.044, '200');
INSERT INTO `bill_item` VALUES ('1525944547484cjjZD385FM', '2018-05-10 17:29:07', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1525944728886OPPIiPYJ6c', '2018-05-10 17:32:10', 'ef7deaca96d94cfeb21c1985c44525db', '4', 1.41, '200');
INSERT INTO `bill_item` VALUES ('1525944731702SmdpxuMPxj', '2018-05-10 17:32:11', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.023, '200');
INSERT INTO `bill_item` VALUES ('1525944732809q7lDCifczg', '2018-05-10 17:32:12', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1525944733251i1mDIr7Lsz', '2018-05-10 17:32:13', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1525944734051QNmWe6eSC2', '2018-05-10 17:32:14', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.049, '200');
INSERT INTO `bill_item` VALUES ('1525944736216PV0Yjhzvzm', '2018-05-10 17:32:16', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.094, '200');
INSERT INTO `bill_item` VALUES ('1525944739870ZtVfsOJRW6', '2018-05-10 17:32:19', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1525944758510GrA9X15tLg', '2018-05-10 17:32:39', 'ef7deaca96d94cfeb21c1985c44525db', '4', 1.053, '500');
INSERT INTO `bill_item` VALUES ('1525944761805X0IhZS4AF5', '2018-05-10 17:32:42', 'ef7deaca96d94cfeb21c1985c44525db', '4', 1.021, '500');
INSERT INTO `bill_item` VALUES ('1525944764010XL7Ivm9LRY', '2018-05-10 17:32:45', 'ef7deaca96d94cfeb21c1985c44525db', '4', 1.021, '500');
INSERT INTO `bill_item` VALUES ('1525944766161zPk4n8jb1G', '2018-05-10 17:32:47', 'ef7deaca96d94cfeb21c1985c44525db', '4', 1.015, '500');
INSERT INTO `bill_item` VALUES ('1525944768090T7D0P5zvlO', '2018-05-10 17:32:49', 'ef7deaca96d94cfeb21c1985c44525db', '4', 1.015, '500');
INSERT INTO `bill_item` VALUES ('1525944770080u0gXhD9KWC', '2018-05-10 17:32:51', 'ef7deaca96d94cfeb21c1985c44525db', '4', 1.02, '500');
INSERT INTO `bill_item` VALUES ('1525944772117dkOdM8z3nW', '2018-05-10 17:32:53', 'ef7deaca96d94cfeb21c1985c44525db', '4', 1.023, '500');
INSERT INTO `bill_item` VALUES ('1525944785398EejSDCDDbm', '2018-05-10 17:33:06', 'ef7deaca96d94cfeb21c1985c44525db', '4', 1.015, '500');
INSERT INTO `bill_item` VALUES ('15259447874855QoGQ6wqcS', '2018-05-10 17:33:08', 'ef7deaca96d94cfeb21c1985c44525db', '4', 1.022, '500');
INSERT INTO `bill_item` VALUES ('15259447895159yrJUJx3Bj', '2018-05-10 17:33:10', 'ef7deaca96d94cfeb21c1985c44525db', '4', 1.011, '500');
INSERT INTO `bill_item` VALUES ('1525944791391AM9VXTIgJX', '2018-05-10 17:33:12', 'ef7deaca96d94cfeb21c1985c44525db', '4', 1.02, '500');
INSERT INTO `bill_item` VALUES ('1525944793402ceMzJN6CYx', '2018-05-10 17:33:14', 'ef7deaca96d94cfeb21c1985c44525db', '4', 1.005, '500');
INSERT INTO `bill_item` VALUES ('1525944838077L4JX7eAGCQ', '2018-05-10 17:33:58', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.755, '200');
INSERT INTO `bill_item` VALUES ('1525944840913dTxwDbOptc', '2018-05-10 17:34:00', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1525944843471LJ3m2qTtjI', '2018-05-10 17:34:03', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1525944845086PjjopfmBzh', '2018-05-10 17:34:05', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('15259448462225YDR5z1Ge0', '2018-05-10 17:34:06', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.008, '200');
INSERT INTO `bill_item` VALUES ('1525944847924JMUbJDiZIU', '2018-05-10 17:34:08', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1525944850729Z5geekdbtR', '2018-05-10 17:34:10', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.009, '200');
INSERT INTO `bill_item` VALUES ('1525944851101mCbw1gYtRs', '2018-05-10 17:34:11', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.008, '200');
INSERT INTO `bill_item` VALUES ('1525944851505225lTbX7gi', '2018-05-10 17:34:11', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.009, '200');
INSERT INTO `bill_item` VALUES ('1525944851835UygZQbLEhf', '2018-05-10 17:34:11', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1525944852145TVfeiUegAE', '2018-05-10 17:34:12', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.004, '200');
INSERT INTO `bill_item` VALUES ('1525944853260nRBzVooGVq', '2018-05-10 17:34:13', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.025, '200');
INSERT INTO `bill_item` VALUES ('1525944854169y0AZYJ8OUt', '2018-05-10 17:34:14', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1525944855087M09gaMoToI', '2018-05-10 17:34:15', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1525944855796yjkYOsqmW7', '2018-05-10 17:34:15', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.008, '200');
INSERT INTO `bill_item` VALUES ('1525944856453l4XpP3Ovzr', '2018-05-10 17:34:16', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1525944858432Y24mWsKuiS', '2018-05-10 17:34:18', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1525944859133g4Ah4DiK1o', '2018-05-10 17:34:19', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1525944860188RdmuKnQbqY', '2018-05-10 17:34:20', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('15259448614832n84hWPf01', '2018-05-10 17:34:21', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.004, '200');
INSERT INTO `bill_item` VALUES ('1525944862324XAZHo1WImP', '2018-05-10 17:34:22', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1525944863103tOYHic6tfb', '2018-05-10 17:34:23', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1525944864086RX6U7Ya7sG', '2018-05-10 17:34:24', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1525944865036wzTlx1i695', '2018-05-10 17:34:25', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1525944865976e3Gtqgideo', '2018-05-10 17:34:26', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1525944866572BTwBXM3OsQ', '2018-05-10 17:34:26', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1525944867275ovLzlmYPh5', '2018-05-10 17:34:27', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1525944867921org9fDyLyf', '2018-05-10 17:34:28', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('15259448686068E5XZLgu7m', '2018-05-10 17:34:28', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.021, '200');
INSERT INTO `bill_item` VALUES ('1525944869958Ojwz1DrRPl', '2018-05-10 17:34:30', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.004, '200');
INSERT INTO `bill_item` VALUES ('1525944870672sdo75g7k84', '2018-05-10 17:34:30', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1525944871647D5c55jv46y', '2018-05-10 17:34:31', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1525944872445NttEVmrLnF', '2018-05-10 17:34:32', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.004, '200');
INSERT INTO `bill_item` VALUES ('1525944873034MXBnBt2hnJ', '2018-05-10 17:34:33', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.004, '200');
INSERT INTO `bill_item` VALUES ('1525944874242Ke4AwxxE2n', '2018-05-10 17:34:34', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.004, '200');
INSERT INTO `bill_item` VALUES ('1525944884220bMhrwK1bKk', '2018-05-10 17:34:44', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.004, '200');
INSERT INTO `bill_item` VALUES ('15259448847934gWKCG1iBn', '2018-05-10 17:34:45', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('15259448853702AOhOjUr7B', '2018-05-10 17:34:45', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1525944885896b8giTwSdqI', '2018-05-10 17:34:45', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1525944886309eGarjd47bE', '2018-05-10 17:34:46', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.008, '200');
INSERT INTO `bill_item` VALUES ('1525944886839tJVDag0tRO', '2018-05-10 17:34:47', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1525944887213F3YZnEgQxg', '2018-05-10 17:34:48', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('15259448880434lDNe0Y8NS', '2018-05-10 17:34:49', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.165, '200');
INSERT INTO `bill_item` VALUES ('1525944888802qZg6TEjV8u', '2018-05-10 17:34:49', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1525944889506CxdTLFPO3Q', '2018-05-10 17:34:50', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('15259448899666SAC5LcKj4', '2018-05-10 17:34:51', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.004, '200');
INSERT INTO `bill_item` VALUES ('1525944890535yiG6CGPRba', '2018-05-10 17:34:52', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.008, '200');
INSERT INTO `bill_item` VALUES ('1525944891377yEO9eWUzwI', '2018-05-10 17:34:53', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1525944891697pAANth3hqX', '2018-05-10 17:34:53', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('15259448919722tlbkYbsCz', '2018-05-10 17:34:53', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1525944892464bPY27B2TJi', '2018-05-10 17:34:53', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1525944892764DEsKrCYIkF', '2018-05-10 17:34:54', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.004, '200');
INSERT INTO `bill_item` VALUES ('1525944893361M63Zk1QGC7', '2018-05-10 17:34:54', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1525944893648Y2RI2YNnjO', '2018-05-10 17:34:54', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1525944894120AzbevBrwcC', '2018-05-10 17:34:55', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1525944896041MjQ94BTD6K', '2018-05-10 17:34:56', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1526039760252aEtRFSHElz', '2018-05-11 19:56:00', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.274, '200');
INSERT INTO `bill_item` VALUES ('1526039793004KXo5UesNjW', '2018-05-11 19:56:33', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.042, '200');
INSERT INTO `bill_item` VALUES ('1526039793811zwVRHMFai5', '2018-05-11 19:56:33', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.043, '200');
INSERT INTO `bill_item` VALUES ('1526039794907VRFWxRZw4G', '2018-05-11 19:56:35', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.041, '200');
INSERT INTO `bill_item` VALUES ('1526039796058BzQg7joTki', '2018-05-11 19:56:36', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.041, '200');
INSERT INTO `bill_item` VALUES ('15260397970328DKnhPCooU', '2018-05-11 19:56:37', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.045, '200');
INSERT INTO `bill_item` VALUES ('1526039797866tsHuklpCM9', '2018-05-11 19:56:37', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.04, '200');
INSERT INTO `bill_item` VALUES ('1526039798910Y6udfHUoUR', '2018-05-11 19:56:38', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.035, '200');
INSERT INTO `bill_item` VALUES ('15260397999064QihUO6aKS', '2018-05-11 19:56:39', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.035, '200');
INSERT INTO `bill_item` VALUES ('1526039800910igbxzqnZLX', '2018-05-11 19:56:41', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.034, '200');
INSERT INTO `bill_item` VALUES ('1526039801843Yr73Hua5uV', '2018-05-11 19:56:42', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.034, '200');
INSERT INTO `bill_item` VALUES ('1526039829148EAimiELknG', '2018-05-11 19:57:11', 'e1dd60ed08ff472395167e8b8c61a657', '4', 2.789, '200');
INSERT INTO `bill_item` VALUES ('1526039833421dKnFuWrIz2', '2018-05-11 19:57:13', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.06, '200');
INSERT INTO `bill_item` VALUES ('1526039834504LllTiAh7vm', '2018-05-11 19:57:16', 'e1dd60ed08ff472395167e8b8c61a657', '4', 2.459, '200');
INSERT INTO `bill_item` VALUES ('15260398385321jWRe4U9vJ', '2018-05-11 19:57:19', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.501, '200');
INSERT INTO `bill_item` VALUES ('15260398400921OwLQisSlM', '2018-05-11 19:57:20', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.033, '200');
INSERT INTO `bill_item` VALUES ('1526039841178EoLSsa7Eq4', '2018-05-11 19:57:21', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.04, '200');
INSERT INTO `bill_item` VALUES ('1526039842149QRNys1RUuF', '2018-05-11 19:57:22', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.033, '200');
INSERT INTO `bill_item` VALUES ('1526039843211Tp8SjLOmCv', '2018-05-11 19:57:23', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.057, '200');
INSERT INTO `bill_item` VALUES ('1526039844326VeU2oQfZAz', '2018-05-11 19:57:24', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.042, '200');
INSERT INTO `bill_item` VALUES ('1526039845096UkJHbf6yhX', '2018-05-11 19:57:25', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.043, '200');
INSERT INTO `bill_item` VALUES ('1526039851088kDIE4JNbrW', '2018-05-11 19:57:31', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.063, '200');
INSERT INTO `bill_item` VALUES ('1526039854038Gg6BtEssET', '2018-05-11 19:57:34', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.044, '200');
INSERT INTO `bill_item` VALUES ('152603985826341VF0rSIVb', '2018-05-11 19:57:38', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.046, '200');
INSERT INTO `bill_item` VALUES ('1526039860354CiS0zOoFKj', '2018-05-11 19:57:40', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.056, '200');
INSERT INTO `bill_item` VALUES ('1526039862493fwFI4rXq8I', '2018-05-11 19:57:42', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.037, '200');
INSERT INTO `bill_item` VALUES ('1526039865107D9cAZS7LNk', '2018-05-11 19:57:45', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.038, '200');
INSERT INTO `bill_item` VALUES ('1526039876339xztnK0qbPB', '2018-05-11 19:57:56', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.043, '200');
INSERT INTO `bill_item` VALUES ('1526039877051gTpOUOW4vE', '2018-05-11 19:57:57', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.035, '200');
INSERT INTO `bill_item` VALUES ('1526039877611oZKUeZpgG4', '2018-05-11 19:57:57', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.033, '200');
INSERT INTO `bill_item` VALUES ('1526039882258cxrvpxyOG0', '2018-05-11 19:58:02', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.039, '200');
INSERT INTO `bill_item` VALUES ('1526039882615XqTIbESkIt', '2018-05-11 19:58:02', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.044, '200');
INSERT INTO `bill_item` VALUES ('1526039883093ngMogTfzGN', '2018-05-11 19:58:03', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.037, '200');
INSERT INTO `bill_item` VALUES ('1526039883660pj0hfdxrXU', '2018-05-11 19:58:04', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.046, '200');
INSERT INTO `bill_item` VALUES ('1526039884164rMesfgYPeR', '2018-05-11 19:58:04', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.042, '200');
INSERT INTO `bill_item` VALUES ('1526039884537vtuXbqniM4', '2018-05-11 19:58:05', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.041, '200');
INSERT INTO `bill_item` VALUES ('1526039884963l0wpBUlP1I', '2018-05-11 19:58:05', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.033, '200');
INSERT INTO `bill_item` VALUES ('1526039885345eIykg4mhCq', '2018-05-11 19:58:06', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.034, '200');
INSERT INTO `bill_item` VALUES ('1526039885739vAQ3BsQzXE', '2018-05-11 19:58:06', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.093, '200');
INSERT INTO `bill_item` VALUES ('15260398861731egAJIjqLE', '2018-05-11 19:58:06', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.039, '200');
INSERT INTO `bill_item` VALUES ('1526039886684vy01FtGRZU', '2018-05-11 19:58:07', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.031, '200');
INSERT INTO `bill_item` VALUES ('1526039887011RDrok0IopH', '2018-05-11 19:58:07', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.044, '200');
INSERT INTO `bill_item` VALUES ('1526039887406tPd4krGIgf', '2018-05-11 19:58:07', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.037, '200');
INSERT INTO `bill_item` VALUES ('1526039887908zbgnu1aJTj', '2018-05-11 19:58:07', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.038, '200');
INSERT INTO `bill_item` VALUES ('1526039888356hryCsyWKpg', '2018-05-11 19:58:08', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.04, '200');
INSERT INTO `bill_item` VALUES ('1526039888772oX1bkvsrdB', '2018-05-11 19:58:08', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.035, '200');
INSERT INTO `bill_item` VALUES ('1526039889233pPmHm9cGjX', '2018-05-11 19:58:09', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.034, '200');
INSERT INTO `bill_item` VALUES ('1526039889652boEZSu3h7T', '2018-05-11 19:58:10', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.037, '200');
INSERT INTO `bill_item` VALUES ('1526039890233plv0UMMSDi', '2018-05-11 19:58:10', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.043, '200');
INSERT INTO `bill_item` VALUES ('15260398904458LhcZcmehb', '2018-05-11 19:58:11', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.043, '200');
INSERT INTO `bill_item` VALUES ('1526039891476mGcHJjZAoX', '2018-05-11 19:58:12', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.034, '200');
INSERT INTO `bill_item` VALUES ('1526039891748WzUBXU1rcB', '2018-05-11 19:58:12', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.037, '200');
INSERT INTO `bill_item` VALUES ('1526039892104mwPHYSt9FT', '2018-05-11 19:58:12', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.035, '200');
INSERT INTO `bill_item` VALUES ('1526039892601KzP8DoUpa6', '2018-05-11 19:58:13', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.037, '200');
INSERT INTO `bill_item` VALUES ('1526039893044IdXkeQuKmq', '2018-05-11 19:58:13', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.039, '200');
INSERT INTO `bill_item` VALUES ('15260398934778JLsytF7Wq', '2018-05-11 19:58:14', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.04, '200');
INSERT INTO `bill_item` VALUES ('1526039894152zfNZPRDXPC', '2018-05-11 19:58:14', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.05, '200');
INSERT INTO `bill_item` VALUES ('1526039894346nEzC3MBqFU', '2018-05-11 19:58:14', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.16, '200');
INSERT INTO `bill_item` VALUES ('1526039894842sNLCw4zZ9a', '2018-05-11 19:58:14', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.044, '200');
INSERT INTO `bill_item` VALUES ('15260398952600LUWduFEvD', '2018-05-11 19:58:15', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.041, '200');
INSERT INTO `bill_item` VALUES ('1526039895724336uYacDVV', '2018-05-11 19:58:16', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.035, '200');
INSERT INTO `bill_item` VALUES ('1526039896203y2dcE8ZnZo', '2018-05-11 19:58:17', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.036, '200');
INSERT INTO `bill_item` VALUES ('1526039896682ahcmDutDCd', '2018-05-11 19:58:18', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.033, '200');
INSERT INTO `bill_item` VALUES ('1526039897305K2356JD70A', '2018-05-11 19:58:18', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.035, '200');
INSERT INTO `bill_item` VALUES ('1526039912636blP0UpzsST', '2018-05-11 19:58:32', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.039, '200');
INSERT INTO `bill_item` VALUES ('1526039913131dAwmHPtJI1', '2018-05-11 19:58:33', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.035, '200');
INSERT INTO `bill_item` VALUES ('1526039913633ehoWYMKAbz', '2018-05-11 19:58:33', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.037, '200');
INSERT INTO `bill_item` VALUES ('1526039914049TfIpMIaEXd', '2018-05-11 19:58:34', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.039, '200');
INSERT INTO `bill_item` VALUES ('1526039917986W4DaoT8LwY', '2018-05-11 19:58:38', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.037, '200');
INSERT INTO `bill_item` VALUES ('1526039918501DhivYBQrCZ', '2018-05-11 19:58:38', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.037, '200');
INSERT INTO `bill_item` VALUES ('1526039919082r9L47KIoH3', '2018-05-11 19:58:39', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.04, '200');
INSERT INTO `bill_item` VALUES ('1526039919633aYykKIkbSk', '2018-05-11 19:58:41', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.031, '200');
INSERT INTO `bill_item` VALUES ('1526039924153eFrFvihbXW', '2018-05-11 19:58:44', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.095, '200');
INSERT INTO `bill_item` VALUES ('1526039924575HejhEtAMRm', '2018-05-11 19:58:44', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.135, '200');
INSERT INTO `bill_item` VALUES ('1526039925617cbKYqI2mbS', '2018-05-11 19:58:45', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.042, '200');
INSERT INTO `bill_item` VALUES ('15260399263106YEAN6m1ck', '2018-05-11 19:58:46', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.045, '200');
INSERT INTO `bill_item` VALUES ('15260399270142Hw2NH7sG7', '2018-05-11 19:58:47', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.034, '200');
INSERT INTO `bill_item` VALUES ('15260399276274Aj1cybhVp', '2018-05-11 19:58:47', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.043, '200');
INSERT INTO `bill_item` VALUES ('1526039932373O7HHoVbpER', '2018-05-11 19:58:52', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.033, '200');
INSERT INTO `bill_item` VALUES ('1526039932967EXZ1LBu9LP', '2018-05-11 19:58:53', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.039, '200');
INSERT INTO `bill_item` VALUES ('1526039933640lypObe1Bab', '2018-05-11 19:58:53', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.04, '200');
INSERT INTO `bill_item` VALUES ('1526039934466QTO41A1scw', '2018-05-11 19:58:54', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.039, '200');
INSERT INTO `bill_item` VALUES ('15260399349709AcgeKAAdw', '2018-05-11 19:58:55', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.043, '200');
INSERT INTO `bill_item` VALUES ('1526039935571ivET4U1HUX', '2018-05-11 19:58:55', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.044, '200');
INSERT INTO `bill_item` VALUES ('1526039936186sZAIxpfNZp', '2018-05-11 19:58:56', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.081, '200');
INSERT INTO `bill_item` VALUES ('1526039936858U31jCXLc3o', '2018-05-11 19:58:56', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.045, '200');
INSERT INTO `bill_item` VALUES ('1526039937443f2euebZwEI', '2018-05-11 19:58:57', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.039, '200');
INSERT INTO `bill_item` VALUES ('152603993797198ifiBOWQs', '2018-05-11 19:58:58', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.035, '200');
INSERT INTO `bill_item` VALUES ('1526039944186Ic5hcSq4yB', '2018-05-11 19:59:04', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.134, '200');
INSERT INTO `bill_item` VALUES ('1526039945057rpSiOmmwRX', '2018-05-11 19:59:05', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.035, '200');
INSERT INTO `bill_item` VALUES ('15260399457188iIvJOPEqQ', '2018-05-11 19:59:05', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.035, '200');
INSERT INTO `bill_item` VALUES ('15260399464167uagjk1HQl', '2018-05-11 19:59:06', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.04, '200');
INSERT INTO `bill_item` VALUES ('1526039947128kcLQ4Kjy0o', '2018-05-11 19:59:07', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.043, '200');
INSERT INTO `bill_item` VALUES ('1526039947852LvD3u5b0nz', '2018-05-11 19:59:07', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.041, '200');
INSERT INTO `bill_item` VALUES ('15260399485982aIB7hPdOn', '2018-05-11 19:59:08', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.037, '200');
INSERT INTO `bill_item` VALUES ('1526039949285aPVlEmju5v', '2018-05-11 19:59:09', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.034, '200');
INSERT INTO `bill_item` VALUES ('1526039949935eOF7b1bBBl', '2018-05-11 19:59:10', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.034, '200');
INSERT INTO `bill_item` VALUES ('1526039950699vdYz17wE8C', '2018-05-11 19:59:10', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.036, '200');
INSERT INTO `bill_item` VALUES ('1526039951393ZwV0OLilmt', '2018-05-11 19:59:11', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.037, '200');
INSERT INTO `bill_item` VALUES ('1526039952140xhGSsIIjmZ', '2018-05-11 19:59:12', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.036, '200');
INSERT INTO `bill_item` VALUES ('1526040244552Ka6Q0P0S9P', '2018-05-11 20:04:04', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.064, '200');
INSERT INTO `bill_item` VALUES ('1526040245328IebLMlO0IW', '2018-05-11 20:04:05', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.035, '200');
INSERT INTO `bill_item` VALUES ('1526040246109FdjoaC5LJA', '2018-05-11 20:04:06', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.044, '200');
INSERT INTO `bill_item` VALUES ('1526040246983SvHn834qAG', '2018-05-11 20:04:07', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.038, '200');
INSERT INTO `bill_item` VALUES ('152604024779784Fk1rGdZE', '2018-05-11 20:04:07', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.04, '200');
INSERT INTO `bill_item` VALUES ('1526040248682jhdKMEtRFU', '2018-05-11 20:04:08', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.041, '200');
INSERT INTO `bill_item` VALUES ('1526040249536KufIBXmBRv', '2018-05-11 20:04:09', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.042, '200');
INSERT INTO `bill_item` VALUES ('1526040288751moePCPAvWb', '2018-05-11 20:04:49', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.514, '200');
INSERT INTO `bill_item` VALUES ('15260402978291AB6f7CgEX', '2018-05-11 20:04:58', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.042, '200');
INSERT INTO `bill_item` VALUES ('15260403497303oq2JanTmL', '2018-05-11 20:05:49', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.125, '200');
INSERT INTO `bill_item` VALUES ('152604035306677fnxFhXoT', '2018-05-11 20:05:53', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.033, '200');
INSERT INTO `bill_item` VALUES ('1526040365128Y9SgRO0DyK', '2018-05-11 20:06:05', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.036, '200');
INSERT INTO `bill_item` VALUES ('1526040367136SM1ONYXjjn', '2018-05-11 20:06:07', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.037, '200');
INSERT INTO `bill_item` VALUES ('1526040368710WnbioSNEtm', '2018-05-11 20:06:08', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.042, '200');
INSERT INTO `bill_item` VALUES ('1526040370205LFzRbCHFNj', '2018-05-11 20:06:10', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.036, '200');
INSERT INTO `bill_item` VALUES ('1526040464889GNhLHtGH1D', '2018-05-11 20:07:45', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.037, '200');
INSERT INTO `bill_item` VALUES ('15260404656208I7NOyWAg8', '2018-05-11 20:07:45', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.039, '200');
INSERT INTO `bill_item` VALUES ('1526040475072l7v9BsLqis', '2018-05-11 20:07:55', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.042, '200');
INSERT INTO `bill_item` VALUES ('15260404757970FqTLtz6aq', '2018-05-11 20:07:56', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.038, '200');
INSERT INTO `bill_item` VALUES ('1526040476413iCgTso1GfA', '2018-05-11 20:07:57', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.04, '200');
INSERT INTO `bill_item` VALUES ('1526040477080ZCrwNxPsA7', '2018-05-11 20:07:57', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.103, '200');
INSERT INTO `bill_item` VALUES ('15260404777006bk2SNcR1r', '2018-05-11 20:07:57', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.046, '200');
INSERT INTO `bill_item` VALUES ('1526040478350UqIJrwC6qV', '2018-05-11 20:07:58', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.038, '200');
INSERT INTO `bill_item` VALUES ('1526040478948nAnDH7orma', '2018-05-11 20:07:59', 'e1dd60ed08ff472395167e8b8c61a657', '4', 0.04, '200');
INSERT INTO `bill_item` VALUES ('15260405063001oMIHy5H1L', '2018-05-11 20:08:26', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.238, '200');
INSERT INTO `bill_item` VALUES ('1526040508782IEtaJtSuiq', '2018-05-11 20:08:28', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1526040512680hJA7xDaLC3', '2018-05-11 20:08:32', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.009, '200');
INSERT INTO `bill_item` VALUES ('1526040513309Y9vA1DWpmK', '2018-05-11 20:08:33', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1526040521004Cg1JrP4IFs', '2018-05-11 20:08:41', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.036, '200');
INSERT INTO `bill_item` VALUES ('1526040521733IfmCItiXYc', '2018-05-11 20:08:41', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('15260405222945SJFzWB2vS', '2018-05-11 20:08:42', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1526040524351N5kmqPBVEK', '2018-05-11 20:08:44', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1526040525079JO1oE4D16C', '2018-05-11 20:08:45', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1526040525641ZRqrWFiTmX', '2018-05-11 20:08:45', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1526040526319aMxyFByYax', '2018-05-11 20:08:46', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1526040526854lr7hOVDam5', '2018-05-11 20:08:47', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.017, '200');
INSERT INTO `bill_item` VALUES ('1526040527446bOrleSpTEy', '2018-05-11 20:08:47', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.01, '200');
INSERT INTO `bill_item` VALUES ('1526040528046fPtI5io1OS', '2018-05-11 20:08:48', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1526040528594H50Zsoy2Dc', '2018-05-11 20:08:48', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('152604052913313AsrYUa8z', '2018-05-11 20:08:49', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.01, '200');
INSERT INTO `bill_item` VALUES ('1526040529840d9FuoL3Ffo', '2018-05-11 20:08:49', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1526040530589QwRyTCc8vp', '2018-05-11 20:08:51', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1526040531229iFHSQb7RNF', '2018-05-11 20:08:53', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1526040531862kAzQCeJENj', '2018-05-11 20:08:54', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1526040532391G1RoQyFL06', '2018-05-11 20:08:55', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('15260405330099mRvp1oEuP', '2018-05-11 20:08:56', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1526040533613fK5ZDVVdd6', '2018-05-11 20:08:57', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1526040534268zAIhBp2tNF', '2018-05-11 20:08:58', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1526040534806JqXpAMvebU', '2018-05-11 20:08:58', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1526040539520mmnge12ykv', '2018-05-11 20:08:59', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.008, '200');
INSERT INTO `bill_item` VALUES ('1526040540118tQUcayoPzL', '2018-05-11 20:09:00', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('15260405407352jC6Hzxexa', '2018-05-11 20:09:00', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1526040541295MoJuh0vIYQ', '2018-05-11 20:09:01', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1526040541831vyBLbpCFNs', '2018-05-11 20:09:01', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1526040542279Gp4LqlwwQd', '2018-05-11 20:09:02', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1526040542749zBYgPQLchR', '2018-05-11 20:09:02', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1526040543212zPOdcdQivv', '2018-05-11 20:09:03', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1526040543595yk193zsQDW', '2018-05-11 20:09:03', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1526040543939da2Pcqz7vs', '2018-05-11 20:09:04', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1526040544224iwuvmtYgWq', '2018-05-11 20:09:04', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1526040544500VjRTUIUcbe', '2018-05-11 20:09:04', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.004, '200');
INSERT INTO `bill_item` VALUES ('1526040544773oOzKX6Ee8N', '2018-05-11 20:09:04', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1526040545007G5oj3wWuse', '2018-05-11 20:09:05', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('15260405452338WnBbXEHr5', '2018-05-11 20:09:05', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.009, '200');
INSERT INTO `bill_item` VALUES ('1526040545442V5zbdbev9G', '2018-05-11 20:09:06', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1526040546284AhhO8OFKOh', '2018-05-11 20:09:06', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.108, '200');
INSERT INTO `bill_item` VALUES ('1526040547012557jUr4mTp', '2018-05-11 20:09:07', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1526040547485xz9CBySOQ8', '2018-05-11 20:09:07', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1526040547868uabJWfqXFB', '2018-05-11 20:09:08', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1526040548293aF9hISi05j', '2018-05-11 20:09:08', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1526040549023p4T4ZoAc4m', '2018-05-11 20:09:09', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.008, '200');
INSERT INTO `bill_item` VALUES ('1526040554867aZngTjUtp9', '2018-05-11 20:09:15', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1526040555634oQHnyUIFVh', '2018-05-11 20:09:15', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1526040556151DOOw8nKZDx', '2018-05-11 20:09:16', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1526040556706SAb4F7sScw', '2018-05-11 20:09:16', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1526040557245nmECeBbPF8', '2018-05-11 20:09:17', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1526040557830vPTyNXIlcd', '2018-05-11 20:09:18', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.004, '200');
INSERT INTO `bill_item` VALUES ('15260405582418ZRhrcz7XL', '2018-05-11 20:09:19', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1526040558664C3qP1nTv3F', '2018-05-11 20:09:20', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.004, '200');
INSERT INTO `bill_item` VALUES ('1526040559097mwkefWni5X', '2018-05-11 20:09:21', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1526040559508qeAJQn4Mg5', '2018-05-11 20:09:22', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1526040559856bubfkhXDHU', '2018-05-11 20:09:23', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1526040560098TrO1pk2cxT', '2018-05-11 20:09:24', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('152604057348230iKLVZDU9', '2018-05-11 20:09:33', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1526040574157UpKOpOj5I9', '2018-05-11 20:09:34', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1526040574720zM2U1MSWGO', '2018-05-11 20:09:34', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1526040575333Y1SPJWR2WC', '2018-05-11 20:09:35', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1526040575969BIHhIygzXq', '2018-05-11 20:09:36', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.008, '200');
INSERT INTO `bill_item` VALUES ('1526040583183FLyZT1O59A', '2018-05-11 20:09:43', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1526040583732i9VAIoAdKB', '2018-05-11 20:09:44', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1526040584258eilwT0vg5I', '2018-05-11 20:09:44', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.007, '200');
INSERT INTO `bill_item` VALUES ('1526040726790wc3j84wu68', '2018-05-11 20:12:07', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1526040727577LcFd6diAn8', '2018-05-11 20:12:07', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.006, '200');
INSERT INTO `bill_item` VALUES ('1526040728262suBNpXzEtz', '2018-05-11 20:12:08', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.005, '200');
INSERT INTO `bill_item` VALUES ('1526096725339jcY1hmLfEU', '2018-05-12 11:45:27', 'ef7deaca96d94cfeb21c1985c44525db', '4', 1.823, '200');
INSERT INTO `bill_item` VALUES ('1526096735849Hf12YpyYz5', '2018-05-12 11:45:35', 'ef7deaca96d94cfeb21c1985c44525db', '4', 0.01, '200');

-- ----------------------------
-- Table structure for bill_type
-- ----------------------------
DROP TABLE IF EXISTS `bill_type`;
CREATE TABLE `bill_type`  (
  `bill_type_code` int(1) NOT NULL,
  `type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`bill_type_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bill_type
-- ----------------------------
INSERT INTO `bill_type` VALUES (0, '按照访问次数计费');
INSERT INTO `bill_type` VALUES (1, '按流量计费');

-- ----------------------------
-- Table structure for consumer
-- ----------------------------
DROP TABLE IF EXISTS `consumer`;
CREATE TABLE `consumer`  (
  `consumer_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `consumer_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `consumer_password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `consumer_email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `consumer_tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `consumer_type` int(1) NOT NULL COMMENT '0:内部用户\n1:外部用户',
  PRIMARY KEY (`consumer_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of consumer
-- ----------------------------
INSERT INTO `consumer` VALUES ('2cfae09cfaf04d7cb3911413e45eadcb', 'YANGYANG', 'WOSHIYANGYANG.', '17862700885@163.COM', '17862700885', 0);
INSERT INTO `consumer` VALUES ('46f35005e70a4102a5323da9bc641e2b', 'YANGYANG', 'WOSHIYANGYANG.', '17862700885@163.COM', '17862700885', 0);
INSERT INTO `consumer` VALUES ('5c4b5c0d6fb848d59d5092200f7573eb', 'YANGYANG', '1111111', '17862700885@163.COM', '17862700885', 0);
INSERT INTO `consumer` VALUES ('74547d7737854374977aa940d8cd376c', 'YANGYANG', 'WOSHIYANGYANG.', '17862700885@163.COM', '17862700885', 0);
INSERT INTO `consumer` VALUES ('87c340886b29406eb417deaeb53a53d5', 'YANGYANG', '1111111', '17862700885@163.COM', '17862700885', 0);
INSERT INTO `consumer` VALUES ('87de254182574856af64d323869b686d', '姬喜洋', 'woshiyangyang.', '1044456468@qq.com', '17862700885', 0);
INSERT INTO `consumer` VALUES ('d6701b9a5b0147f9939016f84aa2e9c0', '姬喜洋', '1111111', '1044456469@qq.com', '17862700885', 0);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `consumer_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `api_category_id` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `order_remain` int(11) NOT NULL,
  `order_total` int(11) NOT NULL,
  `strategy` int(11) NULL DEFAULT 0 COMMENT '0:成本优先\n1:响应时间优先\n2:稳定性优先',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `order_api`(`api_category_id`) USING BTREE,
  INDEX `order_consumer`(`consumer_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for outbound_param
-- ----------------------------
DROP TABLE IF EXISTS `outbound_param`;
CREATE TABLE `outbound_param`  (
  `outbound_param_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接出参数的编码\n',
  `outbound_param_key` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `outbound_param_type` int(11) NULL DEFAULT 0 COMMENT '接出参数的参数类型，Integer，Float，Double，String,Date',
  `inbound_param_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `outbount_param_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接出参数的值',
  `outbound_param_position` int(11) NULL DEFAULT NULL COMMENT '0：header\n1:path\n2:body',
  PRIMARY KEY (`outbound_param_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for service_provider
-- ----------------------------
DROP TABLE IF EXISTS `service_provider`;
CREATE TABLE `service_provider`  (
  `sp_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '外键，组织ID等于userid',
  `sp_org_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织ID',
  `sp_description` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组织描述',
  `sp_tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组织电话',
  `sp_representative` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '法人代表',
  `sp_email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sp_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sp_password` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sp_representative_id` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sp_create_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  INDEX `sp_id`(`sp_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of service_provider
-- ----------------------------
INSERT INTO `service_provider` VALUES ('c430c9776a934ff1a856360185920c5d', '123456', '修改后的描述', '17862700885', 'ICES', 'hitices@hitwh.com', 'ICES', 'hitices', '', NULL);
INSERT INTO `service_provider` VALUES ('5275bcd53a9d4da39b960a0db8c9592b', 'yangyang', '', '测试', '17862700885', '姬喜洋', '111111111111111111', '1044456468@qq.com', '1234567', NULL);
INSERT INTO `service_provider` VALUES ('b3d9251d83a845e0b1bb8dcfdeb8de6b', 'yangyang', '1', '1', '1111111', '1111111', '1111111111', '11111@QQ.COM', '1234567', NULL);
INSERT INTO `service_provider` VALUES ('2bd890add14c4bb4a91532f23155d59e', 'yangyang', '1', '1', '1111111', '1111111', '1111111111', '1111122@QQ.COM', '1234567', NULL);
INSERT INTO `service_provider` VALUES ('ed4075c142724b749381ff130c13a76b', '', '111111111', '111111111', 'aSAS', '1111122222@QQ.COM', 'ICES', '1234567', '111111', NULL);

-- ----------------------------
-- Table structure for standard_inbound_param
-- ----------------------------
DROP TABLE IF EXISTS `standard_inbound_param`;
CREATE TABLE `standard_inbound_param`  (
  `standard_inbound_param_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标准接入参数编码',
  `standard_inbound_param_key` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标准接入参数key值\n',
  `standard_inbound_param_type` int(11) NOT NULL DEFAULT 0 COMMENT '标准接入参数类型。包括:Integer,Float,Double,String,Date\n',
  `api_category_id` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `standard_inbound_param_desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`standard_inbound_param_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;