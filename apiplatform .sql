create table account
(
	account_id varchar(32) not null
		primary key,
	api_id varchar(32) not null,
	app_id varchar(32) not null,
	date timestamp default CURRENT_TIMESTAMP null,
	others text null
)
;

create table admin
(
	admin_name varchar(36) not null
		primary key,
	admin_password varchar(32) not null,
	constraint admin_admin_name_uindex
		unique (admin_name)
)
;

create table api
(
	api_id varchar(32) not null
		primary key,
	sp_id varchar(32) not null,
	api_max_in int(10) null comment '访问控制',
	api_enabled tinyint(1) default '1' not null comment 'api是否可用',
	api_description text null,
	api_path varchar(255) not null comment '访问代理使用的路径',
	api_name varchar(255) not null,
	api_bill_type int(2) not null comment 'api记账类型，按次数：1，按流量M',
	api_method int(1) default '0' not null comment '0：get 1：post，2：两种都有',
	api_url varchar(255) not null,
	api_return_pattern varchar(64) not null,
	api_normal_return_demo text null,
	api_error_return_demo text null,
	api_timeout int(6) default '30000' not null comment 'api超时时间',
	api_category_id varchar(16) null comment 'api分类编码，同一类api拥有相同的前缀',
	api_average_response_time float default '99' null comment '近200次访问请求的平均响应时间',
	api_ok_response_times int default '0' null comment 'api在近1000次访问请求中的成功次数',
	api_success_response_ratio float default '0' null comment 'api返回成功的比率',
	api_time_algorithm_score float default '0' null comment 'api按照时间算法得到的分数',
	api_stable_algorithm_score float default '0' null comment '稳定性算法得分',
	api_cost_algorithm_score float default '0' null,
	api_jar_path varchar(512) null,
	api_env int(1) default '0' null comment '0：测试，1：线上',
	api_verify_state int(1) default '0' null comment '0：未通过审核，1:已经通过审核，正在适配，2：已经通过审核，适配完毕',
	api_param_xml text null comment 'api参数的xml描述文档',
	api_adapt_state int(1) null,
	api_create_time timestamp default CURRENT_TIMESTAMP not null,
	api_test_interface_url varchar(255) null,
	constraint api_id
		unique (api_id)
)
;

create index api_sp
	on api (sp_id)
;

create table api_app
(
	api_category_id varchar(32) not null,
	app_id varchar(32) not null,
	create_date datetime not null,
	enabled tinyint(1) default '0' not null,
	primary key (api_category_id, app_id)
)
;

create index app_index
	on api_app (app_id)
;

create table api_category
(
	api_category_id varchar(20) not null
		primary key,
	api_category_name varchar(128) not null,
	api_category_desc text null,
	api_category_path varchar(255) null comment 'api类的路径，在网关处可以通过扫描该路径来确定api类',
	api_category_avg_response_time float default '30' null,
	api_category_comment int default '0' null,
	api_category_total_times mediumtext null,
	api_category_price float null,
	api_category_bill_type int null comment '0：按照访问次数计费，1:按照流量计费',
	api_category_request_type int(1) default '0' null comment 'api请求方式，0：get，1：post',
	api_category_enabled tinyint(1) default '1' not null comment 'api类当前是否可用，只有该类下面所有api都死了，该类才会不能访问',
	api_category_normal_response text null,
	api_category_error_response text null,
	api_category_param_xml text null,
	api_category_create_time timestamp default CURRENT_TIMESTAMP not null
)
;

create table api_param
(
	api_param_id varchar(30) not null
		primary key,
	api_id varchar(32) not null,
	api_pre_param_key varchar(255) default '' not null,
	api_param_value varchar(255) default '' not null,
	api_after_param_key varchar(255) not null,
	api_pre_param_position int(1) not null comment '0:header/1:path/2:body',
	api_after_param_position int(1) not null comment '0:header/1:path/2:body',
	api_param_ismust tinyint(1) default '1' not null,
	api_param_isconstant tinyint(1) not null
)
;

create table api_price
(
	price_id varchar(32) not null
		primary key,
	api_id varchar(32) not null,
	price_type int(2) not null comment '0:内部调用价格，1:外部调用价格,2:第三方结算',
	content float not null comment '单价配额',
	price float not null comment '单价',
	constraint price_api
		foreign key (api_id) references api (api_id)
)
;

create index price_api
	on api_price (api_id)
;

create table app
(
	app_id varchar(30) not null
		primary key,
	app_secret varchar(16) not null,
	app_description text not null,
	app_name varchar(128) default '"null"' not null,
	consumer_id varchar(32) null,
	app_create_time timestamp default CURRENT_TIMESTAMP not null
)
;

alter table api_app
	add constraint auth_app
		foreign key (app_id) references app (app_id)
;

create table bill_item
(
	bill_item_id varchar(30) not null
		primary key,
	create_time timestamp default CURRENT_TIMESTAMP not null,
	api_id varchar(32) not null,
	app_id varchar(30) not null,
	request_time float default '30' null,
	response_code varchar(5) default '404' null,
	response_size double null,
	constraint bill_api
		foreign key (api_id) references api (api_id),
	constraint bill_app
		foreign key (app_id) references app (app_id)
)
;

create index bill_api
	on bill_item (api_id)
;

create index bill_app
	on bill_item (app_id)
;

create table bill_type
(
	bill_type_code int(1) not null
		primary key,
	type varchar(30) not null
)
;

create table consumer
(
	consumer_id varchar(32) not null
		primary key,
	consumer_name varchar(100) not null,
	consumer_password varchar(32) not null,
	consumer_email varchar(50) not null,
	consumer_tel varchar(11) not null,
	consumer_type int(1) not null comment '0:内部用户
	1:外部用户',
	consumer_state int(1) default '0' null comment '用户状态，0未审核，1：已经审核，账户正常，2：账户封禁中',
	consumer_intro varchar(256) default '没有自我介绍' null,
	consumer_card_id varchar(12) null comment '身份证号',
	consumer_create_time timestamp default CURRENT_TIMESTAMP null comment '账户创建时间',
	constraint consumer_consumer_email_uindex
		unique (consumer_email)
)
;

create table outbound_param
(
	outbound_param_id varchar(32) not null comment '接出参数的编码
	'
		primary key,
	outbound_param_key varchar(128) not null,
	outbound_param_type int default '0' null comment '接出参数的参数类型，Integer，Float，Double，String,Date',
	inbound_param_id varchar(32) null,
	outbount_param_value varchar(255) not null comment '接出参数的值',
	outbound_param_position int null comment '0：header
	1:path
	2:body'
)
;

create table service_provider
(
	sp_id varchar(32) not null comment '外键，组织ID等于userid',
	sp_org_id varchar(100) null comment '组织ID',
	sp_description text not null comment '组织描述',
	sp_tel varchar(20) not null comment '组织电话',
	sp_representative varchar(30) not null comment '法人代表',
	sp_email varchar(50) not null,
	sp_name varchar(255) not null,
	sp_password varchar(36) not null,
	sp_representative_id varchar(19) not null,
	sp_create_time timestamp null,
	sp_state int(1) default '0' null comment '用户状态，0未审核，1：已经审核，账户正常，2：账户封禁中'
)
;

create index sp_id
	on service_provider (sp_id)
;

alter table api
	add constraint api_sp
		foreign key (sp_id) references service_provider (sp_id)
;

create table standard_inbound_param
(
	standard_inbound_param_id varchar(32) not null comment '标准接入参数编码'
		primary key,
	standard_inbound_param_key varchar(128) not null comment '标准接入参数key值
	',
	standard_inbound_param_type varchar(36) default 'String' not null comment '标准接入参数类型。包括:Integer,Float,Double,String,Date
	',
	api_category_id varchar(20) not null,
	standard_inbound_param_desc text null,
	standard_inbound_param_position int(1) null comment '0：header
	1:path
	2:body',
	standard_inbound_param_value_demo text null,
	standard_inbound_param_ismust int(1) null comment '0：可选，1：必选'
)
;

