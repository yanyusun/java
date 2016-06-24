
DROP TABLE if exists t_user_info;
create table t_user_info
(
   id                   int(11) not null comment '用户基础信息表 数据id',
   version              int(11) not null default 0 comment '数据版本',
   create_at            datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   update_at            datetime not null default CURRENT_TIMESTAMP comment '最后一次更新时间',
   stateflag            bigint(22) not null default 0 comment '数据状态：0-可用，非0不可用',
   remark               varchar(255) default NULL comment '备注',
   user_name            varchar(32) default NULL comment '用户名',
   real_name            varchar(32) default NULL comment '真实姓名',
   sex                  tinyint(1) not null default 1 comment '性别：1-男;0-女;',
   mobile               varchar(32) default NULL comment '手机号',
   email                varchar(256) default NULL comment '电子邮箱',
   password             varchar(256) not null comment '密码串',
   salt                 varchar(16) not null comment '盐码',
   identity             varchar(64) default NULL comment '身份证号',
   company_id           int(11) default NULL comment '公司ID',
   status               tinyint(1) not null default 0 comment '帐号状态：1-启用;0-禁用',
   primary key (id),
   unique key i_user_un (user_name, mobile)
)
ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
alter table t_user_info comment '用户基础信息表 ';

ALTER TABLE t_user_info ADD COLUMN `account` VARCHAR(20) COMMENT '账号';