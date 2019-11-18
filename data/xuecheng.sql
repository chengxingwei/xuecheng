/*
Navicat MySQL Data Transfer

Source Server         : 106.13.16.1891
Source Server Version : 50727
Source Host           : 106.13.16.189:3306
Source Database       : xuecheng

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2019-11-18 10:53:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for clientdetails
-- ----------------------------
DROP TABLE IF EXISTS `clientdetails`;
CREATE TABLE `clientdetails` (
  `appId` varchar(256) NOT NULL,
  `resourceIds` varchar(256) DEFAULT NULL,
  `appSecret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `grantTypes` varchar(256) DEFAULT NULL,
  `redirectUrl` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additionalInformation` varchar(4096) DEFAULT NULL,
  `autoApproveScopes` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clientdetails
-- ----------------------------

-- ----------------------------
-- Table structure for elastic_job_config
-- ----------------------------
DROP TABLE IF EXISTS `elastic_job_config`;
CREATE TABLE `elastic_job_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(255) NOT NULL COMMENT '作业名称',
  `cron` varchar(50) NOT NULL COMMENT 'cron表达式，用于控制作业触发时间',
  `sharding_total_count` int(3) NOT NULL COMMENT '作业分片总数',
  `sharding_item_parameters` varchar(255) DEFAULT NULL COMMENT '分片序列,列号从0开始,不可大于或等于作业分片总数.如：0=a,1=b,2=c',
  `job_parameter` varchar(255) DEFAULT NULL COMMENT '作业自定义参数',
  `failover` enum('false','true') DEFAULT 'true' COMMENT '是否开启任务执行失效转移',
  `misfire` enum('false','true') DEFAULT 'true' COMMENT '是否开启错过任务重新执行',
  `description` varchar(255) DEFAULT NULL COMMENT '作业描述信息',
  `job_class` varchar(255) NOT NULL COMMENT '作业实现类',
  `streaming_process` enum('false','true') DEFAULT 'true' COMMENT '是否流式处理数据（只有Dataflow类型支持）',
  `job_config` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `jobName` (`job_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of elastic_job_config
-- ----------------------------
INSERT INTO `elastic_job_config` VALUES ('1', 'DemoSimpleJob', '0/3 * * * * ?', '2', '0=a,1=b', '', 'true', 'true', '测试定时任务', 'com.xuecheng.elasticjob.task.DemoSimpleJob', 'true', '');

-- ----------------------------
-- Table structure for elastic_job_config_log
-- ----------------------------
DROP TABLE IF EXISTS `elastic_job_config_log`;
CREATE TABLE `elastic_job_config_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(255) NOT NULL COMMENT '作业名称',
  `cron` varchar(50) NOT NULL COMMENT 'cron表达式，用于控制作业触发时间',
  `sharding_total_count` int(3) NOT NULL COMMENT '作业分片总数',
  `sharding_item_parameters` varchar(255) DEFAULT NULL COMMENT '分片序列,列号从0开始,不可大于或等于作业分片总数.如：0=a,1=b,2=c',
  `job_parameter` varchar(255) DEFAULT NULL COMMENT '作业自定义参数',
  `failover` enum('false','true') DEFAULT 'true' COMMENT '是否开启任务执行失效转移',
  `misfire` enum('false','true') DEFAULT 'true' COMMENT '是否开启错过任务重新执行',
  `description` varchar(255) DEFAULT NULL COMMENT '作业描述信息',
  `job_class` varchar(255) NOT NULL COMMENT '作业实现类',
  `streaming_process` enum('false','true') DEFAULT 'true' COMMENT '是否流式处理数据（只有Dataflow类型支持）',
  `job_config` text,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of elastic_job_config_log
-- ----------------------------

-- ----------------------------
-- Table structure for job_execution_log
-- ----------------------------
DROP TABLE IF EXISTS `job_execution_log`;
CREATE TABLE `job_execution_log` (
  `id` varchar(40) NOT NULL,
  `job_name` varchar(100) NOT NULL,
  `task_id` varchar(255) NOT NULL,
  `hostname` varchar(255) NOT NULL,
  `ip` varchar(50) NOT NULL,
  `sharding_item` int(11) NOT NULL,
  `execution_source` varchar(20) NOT NULL,
  `failure_cause` varchar(4000) DEFAULT NULL,
  `is_success` int(11) NOT NULL,
  `start_time` timestamp NULL DEFAULT NULL,
  `complete_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job_execution_log
-- ----------------------------
INSERT INTO `job_execution_log` VALUES ('000cf55f-2dbb-444a-b18f-ae8db25f06da', 'DemoSimpleJob', 'DemoSimpleJob@-@1@-@READY@-@192.168.100.1@-@13592', 'LAPTOP-VMJC0VKV', '192.168.100.1', '1', 'NORMAL_TRIGGER', null, '1', '2019-10-19 14:23:10', '2019-10-19 14:23:10');

-- ----------------------------
-- Table structure for job_status_trace_log
-- ----------------------------
DROP TABLE IF EXISTS `job_status_trace_log`;
CREATE TABLE `job_status_trace_log` (
  `id` varchar(40) NOT NULL,
  `job_name` varchar(100) NOT NULL,
  `original_task_id` varchar(255) NOT NULL,
  `task_id` varchar(255) NOT NULL,
  `slave_id` varchar(50) NOT NULL,
  `source` varchar(50) NOT NULL,
  `execution_type` varchar(20) NOT NULL,
  `sharding_item` varchar(100) NOT NULL,
  `state` varchar(20) NOT NULL,
  `message` varchar(4000) DEFAULT NULL,
  `creation_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `TASK_ID_STATE_INDEX` (`task_id`,`state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job_status_trace_log
-- ----------------------------
INSERT INTO `job_status_trace_log` VALUES ('00020b84-d3d3-4c63-9e35-d94387c0a5ba', 'DemoSimpleJob', '', 'DemoSimpleJob@-@0@-@READY@-@192.168.100.1@-@5480', '192.168.100.1', 'LITE_EXECUTOR', 'READY', '[0]', 'TASK_RUNNING', '', '2019-10-19 14:18:19');

-- ----------------------------
-- Table structure for me_company
-- ----------------------------
DROP TABLE IF EXISTS `me_company`;
CREATE TABLE `me_company` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ProvinceCode` varchar(255) DEFAULT NULL,
  `CityCode` varchar(255) DEFAULT NULL,
  `DistrictCode` varchar(255) DEFAULT NULL,
  `TownCode` varchar(255) DEFAULT NULL,
  `ProvinceName` varchar(255) DEFAULT NULL,
  `CityName` varchar(255) DEFAULT NULL,
  `DistrictName` varchar(255) DEFAULT NULL,
  `TownName` varchar(255) DEFAULT NULL,
  `VillageCode` varchar(255) DEFAULT NULL,
  `VillageName` varchar(255) DEFAULT NULL,
  `AddressDetail` varchar(255) DEFAULT NULL,
  `CompanyName` varchar(100) DEFAULT NULL COMMENT '企业名称',
  `DutySign` varchar(100) DEFAULT NULL COMMENT '税号',
  `LegalUserName` varchar(50) DEFAULT NULL COMMENT '企业法人',
  `LegalUserMobile` varchar(50) DEFAULT NULL COMMENT '法人电话',
  `Contact` varchar(50) DEFAULT NULL COMMENT '联系人',
  `Mobile` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `Status` int(255) DEFAULT NULL COMMENT '1:新注册 2:通过审核 3:审核失败 4：停用',
  `StatusDesc` varchar(255) DEFAULT NULL COMMENT '状态错误描述',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of me_company
-- ----------------------------

-- ----------------------------
-- Table structure for me_company_auth
-- ----------------------------
DROP TABLE IF EXISTS `me_company_auth`;
CREATE TABLE `me_company_auth` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `AuthTypeId` bigint(20) DEFAULT NULL,
  `AuthTypeCode` varchar(50) DEFAULT NULL,
  `FrontImagePath` varchar(255) DEFAULT NULL,
  `BackImagePath` varchar(255) DEFAULT NULL,
  `FrontImageDesc` varchar(255) DEFAULT NULL,
  `BackImageDesc` varchar(255) DEFAULT NULL,
  `Type` int(11) DEFAULT NULL COMMENT '1:单面 2:正反面',
  `Status` int(11) DEFAULT NULL COMMENT '1:新提交 2：通过 3：不通过',
  `StatusDesc` varchar(255) DEFAULT NULL COMMENT '审核描述',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of me_company_auth
-- ----------------------------

-- ----------------------------
-- Table structure for me_dept
-- ----------------------------
DROP TABLE IF EXISTS `me_dept`;
CREATE TABLE `me_dept` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DeptCode` varchar(100) DEFAULT NULL COMMENT '部门编号',
  `DeptName` varchar(100) DEFAULT NULL COMMENT '部门名称',
  `ParentId` bigint(20) DEFAULT NULL,
  `ParentCode` varchar(100) DEFAULT NULL COMMENT '父部门编号',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of me_dept
-- ----------------------------

-- ----------------------------
-- Table structure for me_member
-- ----------------------------
DROP TABLE IF EXISTS `me_member`;
CREATE TABLE `me_member` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `Status` int(10) DEFAULT NULL COMMENT '1:新注册 2:审核通过 3：审核不通过 4：停用',
  `RealName` varchar(255) DEFAULT NULL,
  `NickName` varchar(255) DEFAULT NULL,
  `Avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `Mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `ProvinceCode` varchar(255) DEFAULT NULL,
  `CityCode` varchar(255) DEFAULT NULL,
  `DistrictCode` varchar(255) DEFAULT NULL,
  `TownCode` varchar(255) DEFAULT NULL,
  `ProvinceName` varchar(255) DEFAULT NULL,
  `CityName` varchar(255) DEFAULT NULL,
  `DistrictName` varchar(255) DEFAULT NULL,
  `TownName` varchar(255) DEFAULT NULL,
  `VillageCode` varchar(255) DEFAULT NULL,
  `VillageName` varchar(255) DEFAULT NULL,
  `AddressDetail` varchar(255) DEFAULT NULL,
  `QQ` varchar(255) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of me_member
-- ----------------------------

-- ----------------------------
-- Table structure for me_member_auth
-- ----------------------------
DROP TABLE IF EXISTS `me_member_auth`;
CREATE TABLE `me_member_auth` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `AuthTypeId` bigint(20) DEFAULT NULL,
  `AuthTypeCode` varchar(50) DEFAULT NULL,
  `FrontImagePath` varchar(255) DEFAULT NULL,
  `BackImagePath` varchar(255) DEFAULT NULL,
  `FrontImageDesc` varchar(255) DEFAULT NULL,
  `BackImageDesc` varchar(255) DEFAULT NULL,
  `Type` int(11) DEFAULT NULL COMMENT '1:单面 2:正反面',
  `Status` int(11) DEFAULT NULL COMMENT '1:新提交 2：通过 3：不通过',
  `StatusDesc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of me_member_auth
-- ----------------------------

-- ----------------------------
-- Table structure for me_member_company
-- ----------------------------
DROP TABLE IF EXISTS `me_member_company`;
CREATE TABLE `me_member_company` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MemberId` bigint(20) DEFAULT NULL,
  `Company` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of me_member_company
-- ----------------------------

-- ----------------------------
-- Table structure for me_member_role
-- ----------------------------
DROP TABLE IF EXISTS `me_member_role`;
CREATE TABLE `me_member_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MemberID` bigint(20) DEFAULT NULL,
  `RoleID` bigint(20) DEFAULT NULL,
  `Company` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of me_member_role
-- ----------------------------
INSERT INTO `me_member_role` VALUES ('1', '1', '1', null);
INSERT INTO `me_member_role` VALUES ('2', '1', '2', null);
INSERT INTO `me_member_role` VALUES ('3', '34', '1', null);
INSERT INTO `me_member_role` VALUES ('4', '34', '2', null);

-- ----------------------------
-- Table structure for me_menu
-- ----------------------------
DROP TABLE IF EXISTS `me_menu`;
CREATE TABLE `me_menu` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MenuName` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `MenuPath` varchar(255) DEFAULT NULL COMMENT '菜单路径',
  `ParentID` bigint(20) DEFAULT NULL COMMENT '父菜单ID',
  `MenuType` int(10) DEFAULT NULL COMMENT '1:菜单 2:按钮',
  `BtnName` varchar(255) DEFAULT NULL,
  `Sort` int(10) DEFAULT NULL,
  `Company` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of me_menu
-- ----------------------------

-- ----------------------------
-- Table structure for me_role
-- ----------------------------
DROP TABLE IF EXISTS `me_role`;
CREATE TABLE `me_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `RoleName` varchar(255) DEFAULT NULL,
  `Status` int(11) DEFAULT NULL COMMENT '1：正常 2：禁用',
  `Company` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of me_role
-- ----------------------------
INSERT INTO `me_role` VALUES ('1', '超级管理员', '1', null);
INSERT INTO `me_role` VALUES ('2', '普通用户', '1', null);

-- ----------------------------
-- Table structure for me_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `me_role_menu`;
CREATE TABLE `me_role_menu` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `RoleID` bigint(20) DEFAULT NULL,
  `MenuID` bigint(20) DEFAULT NULL,
  `Company` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of me_role_menu
-- ----------------------------
INSERT INTO `me_role_menu` VALUES ('9', '2', '1', null);
INSERT INTO `me_role_menu` VALUES ('10', '2', '2', null);

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) DEFAULT NULL,
  `clientId` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `lastModifiedAt` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_approvals
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('client', '', '$2a$10$FJnM8z35fhRFvJGlhNpJZ.zw/GGE4cVTjfCK8uP8o6e4oo5H3s29G', 'all', 'client_credentials,password,refresh_token,authorization_code', null, 'oauth2', null, null, null, null);

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------

-- ----------------------------
-- Table structure for xc_config
-- ----------------------------
DROP TABLE IF EXISTS `xc_config`;
CREATE TABLE `xc_config` (
  `ID` bigint(20) NOT NULL,
  `ConfigKey` varchar(100) DEFAULT NULL,
  `ConfigValue` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xc_config
-- ----------------------------

-- ----------------------------
-- Table structure for xc_dicts
-- ----------------------------
DROP TABLE IF EXISTS `xc_dicts`;
CREATE TABLE `xc_dicts` (
  `ID` bigint(20) NOT NULL,
  `Code` varchar(50) DEFAULT NULL COMMENT '字典编码',
  `Name` varchar(255) DEFAULT NULL COMMENT '字典名称',
  `ParentId` bigint(20) DEFAULT NULL COMMENT '父ID',
  `ParentCode` varchar(50) DEFAULT NULL COMMENT '父级编码',
  `Status` int(11) DEFAULT NULL COMMENT '状态(1:系统默认,2:自定义)',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xc_dicts
-- ----------------------------

-- ----------------------------
-- Table structure for xc_menu
-- ----------------------------
DROP TABLE IF EXISTS `xc_menu`;
CREATE TABLE `xc_menu` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MenuName` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `MenuPath` varchar(255) DEFAULT NULL COMMENT '菜单路径',
  `ParentID` bigint(20) DEFAULT NULL COMMENT '父菜单ID',
  `MenuType` int(10) DEFAULT NULL COMMENT '1:菜单 2:按钮',
  `BtnName` varchar(255) DEFAULT NULL,
  `Sort` int(10) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of xc_menu
-- ----------------------------

-- ----------------------------
-- Table structure for xc_role
-- ----------------------------
DROP TABLE IF EXISTS `xc_role`;
CREATE TABLE `xc_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `RoleName` varchar(255) DEFAULT NULL,
  `Status` int(11) DEFAULT NULL COMMENT '1：正常 2：禁用',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of xc_role
-- ----------------------------
INSERT INTO `xc_role` VALUES ('1', '超级管理员', '1');
INSERT INTO `xc_role` VALUES ('2', '普通用户', '1');

-- ----------------------------
-- Table structure for xc_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `xc_role_menu`;
CREATE TABLE `xc_role_menu` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `RoleID` bigint(20) DEFAULT NULL,
  `MenuID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xc_role_menu
-- ----------------------------
INSERT INTO `xc_role_menu` VALUES ('9', '2', '1');
INSERT INTO `xc_role_menu` VALUES ('10', '2', '2');

-- ----------------------------
-- Table structure for xc_user_role
-- ----------------------------
DROP TABLE IF EXISTS `xc_user_role`;
CREATE TABLE `xc_user_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserInfoID` bigint(20) DEFAULT NULL,
  `RoleID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xc_user_role
-- ----------------------------
INSERT INTO `xc_user_role` VALUES ('1', '1', '1');
INSERT INTO `xc_user_role` VALUES ('2', '1', '2');
INSERT INTO `xc_user_role` VALUES ('3', '34', '1');
INSERT INTO `xc_user_role` VALUES ('4', '34', '2');

-- ----------------------------
-- Table structure for xc_userinfo
-- ----------------------------
DROP TABLE IF EXISTS `xc_userinfo`;
CREATE TABLE `xc_userinfo` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `Mobile` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `Status` int(11) DEFAULT NULL COMMENT '1:正常,2:禁用',
  `UserName` varchar(255) DEFAULT NULL,
  `NickName` varchar(255) DEFAULT NULL COMMENT '昵称',
  `RealName` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of xc_userinfo
-- ----------------------------
INSERT INTO `xc_userinfo` VALUES ('1', '15290881749', '$2a$10$86PGitnzOqe1WuBy9OdV7enZXM6dMKp8MSiqxCwR7RDSyVbP8bqpq', '2', 'admin', null, null);
INSERT INTO `xc_userinfo` VALUES ('2', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('3', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('4', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('5', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('6', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('7', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('8', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('9', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('10', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('11', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('12', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('13', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('14', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('15', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('18', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('19', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('20', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('21', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('22', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('23', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('24', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('25', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('26', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('27', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('28', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('29', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('30', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('31', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('32', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('33', '15290881749', '$2a$10$ZDjxKik2BmnBxdMErtkxke.iLSKqC.WBViyPx1bLDXS80F8UGpLOG', '2', 'qwe', null, null);
INSERT INTO `xc_userinfo` VALUES ('34', '15290881749', '$2a$10$9xIYr0S7gokW8gw.QFVrc.Wo9PWmvFosge2Hy/y3O8j1znvt1tGwW', '1', 'cxw', '', '程行威');
INSERT INTO `xc_userinfo` VALUES ('35', '15290881749', '$2a$10$L3ieO2ToWmssTaESXSjTx.IDfGZYEHwRrASuuUfOZCebbE9S3P1c2', '1', 'cxw', '', '程行威');
INSERT INTO `xc_userinfo` VALUES ('36', '15290881749', '$2a$10$SS2ZQHwbD4QNElRRvTjpFeFuL.NkkD5UkTpvp1jHSHpGp.CzO59ES', '1', 'cxw', '', '程行威');
INSERT INTO `xc_userinfo` VALUES ('37', '15290881749', '$2a$10$ALoBMsIpgG3kxdFeFf0YfOTBt6ZMb9ADHlDZWDGDxFiH9sFUzkvm.', '1', 'cxw', '', '程行威');
