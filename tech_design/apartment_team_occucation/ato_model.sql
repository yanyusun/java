
DROP TABLE IF EXISTS `t_ato_info`;
CREATE TABLE `t_ato_info`(
  `id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
  `version` INT NOT NULL DEFAULT 0 COMMENT '版本',
  `create_at` DATETIME DEFAULT current_timestamp COMMENT '创建时间',
  `update_at` DATETIME DEFAULT current_timestamp COMMENT '最后操作时间',
  `stateflag` BIGINT(2) NOT NULL DEFAULT 0 COMMENT '数据状态',
  `remark` VARCHAR(255) COMMENT '标签说明',
  `type` TINYINT(2) COMMENT '信息类型(1:部门信息;2:团队信息;3:职位信息)',
  `name` VARCHAR(30) COMMENT '名称',
  `pid` INT COMMENT '所属ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;