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

create table api
(
	api_id varchar(32) not null
		primary key,
	sp_id varchar(32) not null,
	api_token varchar(100) null comment '删除，放于常量参数',
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
	api_success_response_ratio float default '0' null,
	api_time_algorithm_score float default '0' null,
	api_stable_algorithm_score float default '0' null comment '稳定性算法得分',
	api_cost_algorithm_score float default '0' null,
	constraint api_id
		unique (api_id)
)
;

create index api_sp
	on api (sp_id)
;

create table api_app
(
	api_id varchar(32) not null,
	app_id varchar(32) not null,
	create_date datetime not null,
	enabled tinyint(1) default '0' not null,
	primary key (api_id, app_id),
	constraint auth_api
		foreign key (api_id) references api (api_id)
)
;

create index app_index
	on api_app (app_id)
;

create table api_category
(
	api_category_id varchar(16) not null
		primary key,
	api_category_name varchar(128) not null,
	api_category_desc text null
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
	consumer_id varchar(32) null
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
	request_time float default '999999' null,
	response_code varchar(5) default '404' null,
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
	consumer_tel varchar(11) not null
)
;

create table consumer_business
(
	consumer_business_id varchar(32) not null
		primary key,
	consumer_id varchar(32) not null,
	consumer_combo_id varchar(32) not null,
	consumer_combo_remain bigint not null,
	consumer_business_begin_time datetime null,
	consumer_business_end_time datetime null,
	token varchar(32) null
)
;

create table consumer_combo
(
	combo_id varchar(32) not null
		primary key,
	api_id varchar(32) not null,
	content bigint default '0' not null,
	combo_price float not null,
	combo_time int(10) null comment '套餐周期，单位为小时'
)
;

create table gateway_api_define
(
	id varchar(50) not null
		primary key,
	path varchar(255) not null,
	service_id varchar(50) null,
	url varchar(255) null,
	retryable tinyint(1) null,
	enabled tinyint(1) not null,
	strip_prefix int null,
	api_name varchar(255) null
)
;

create table `order`
(
	order_id varchar(32) not null
		primary key,
	consumer_id varchar(32) not null,
	api_id varchar(32) not null,
	order_create_time timestamp default CURRENT_TIMESTAMP not null,
	order_remain int not null,
	order_total int not null,
	strategy int default '0' null comment '0:成本优先
	1:响应时间优先
	2:稳定性优先',
	constraint order_consumer
		foreign key (consumer_id) references consumer (consumer_id),
	constraint order_api
		foreign key (api_id) references api (api_id)
)
;

create index order_api
	on `order` (api_id)
;

create index order_consumer
	on `order` (consumer_id)
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
	sp_create_time timestamp null
)
;

create index sp_id
	on service_provider (sp_id)
;

alter table api
	add constraint api_sp
		foreign key (sp_id) references service_provider (sp_id)
;

create table sys_combo
(
	sys_combo_id varchar(32) not null
		primary key,
	api_id varchar(32) not null,
	content bigint not null,
	combo_price float default '0' not null,
	combo_remain bigint default '0' not null,
	current_period_begin_time datetime null,
	current_period_end_time datetime null
)
;

