-- 资产包信息
DROP TABLE IF EXISTS `t_asset_info`;
CREATE TABLE `t_asset_info`(
  `id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
  `version` INT NOT NULL DEFAULT 0 COMMENT '版本',
  `create_at` DATETIME DEFAULT current_timestamp COMMENT '创建时间',
  `update_at` DATETIME DEFAULT current_timestamp COMMENT '最后操作时间',
  `stateflag` TINYINT(2) NOT NULL DEFAULT 0 COMMENT '数据状态',
  `remark` VARCHAR(255) COMMENT '标签说明',

  `code` VARCHAR(20) COMMENT '编号',
  `type` TINYINT(2) COMMENT '资产包类型',
  `entrust_start_time` DATETIME COMMENT '委托开始时间',
  `entrust_end_time` DATETIME COMMENT '委托结束时间',
  `operator` INT DEFAULT 0 COMMENT '录入员',
  `accrual` DOUBLE(10,2) COMMENT '总利息',
  `loan` DOUBLE(10,2) COMMENT '总贷款',
  `appraisal` DOUBLE(10,2) COMMENT '总评估',
  `name` VARCHAR(50) COMMENT '资源包名称',
  `quality` VARCHAR(10) COMMENT '评优',
  `level` VARCHAR(10) COMMENT '评级',
  `province` VARCHAR(10) COMMENT '省份',
  `city` VARCHAR(10) COMMENT '城市',
  `district` VARCHAR(10) COMMENT '区域',
  `address` VARCHAR(250) COMMENT '地址',
  `loan_organization` VARCHAR(50) COMMENT '贷款机构',
  `loan_organization_district` VARCHAR(10) COMMENT '贷款机构区域',
  `dispose_mode` VARCHAR(255) COMMENT '处置方式',
  `tags` VARCHAR(255) COMMENT '标签',
  `isshow` TINYINT(1) DEFAULT 0 COMMENT '是否展示在外网',
  `memo` VARCHAR(255) COMMENT '备注'

) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 借款人信息
DROP TABLE IF EXISTS `t_lender_info`;
CREATE TABLE `t_lender_info`(
  `id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
  `version` INT NOT NULL DEFAULT 0 COMMENT '版本',
  `create_at` DATETIME DEFAULT current_timestamp COMMENT '创建时间',
  `update_at` DATETIME DEFAULT current_timestamp COMMENT '最后操作时间',
  `stateflag` TINYINT(2) NOT NULL DEFAULT 0 COMMENT '数据状态',
  `remark` VARCHAR(255) COMMENT '标签说明',

  `name` VARCHAR(20) COMMENT '姓名',
  `type` VARCHAR(20) COMMENT '借款人类型(借款人|共同借款人|担保方|银行客户经理|其他)',
  `avg` VARCHAR(50) COMMENT '头像',
  `gender` VARCHAR(10) COMMENT '性别',
  `idcard` VARCHAR(18) COMMENT '身份证',
  `company` VARCHAR(50) COMMENT '所在单位',
  `mobile` VARCHAR(15) COMMENT '手机号',
  `home_tel` VARCHAR(20) COMMENT '住宅电话',
  `office_tel` VARCHAR(20) COMMENT '办公电话',
  `email` VARCHAR(30) COMMENT '邮箱',
  `province` VARCHAR(10) COMMENT '省份',
  `city` VARCHAR(10) COMMENT '城市',
  `district` VARCHAR(10) COMMENT '区域',
  `address` VARCHAR(255) COMMENT '详细地址',
  `memo` VARCHAR(255) COMMENT '备注'

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 借款人关系映射表
DROP TABLE IF EXISTS `t_lender_relation`;
CREATE TABLE `t_lender_relation`(
  `id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
  `version` INT NOT NULL DEFAULT 0 COMMENT '版本',
  `create_at` DATETIME DEFAULT current_timestamp COMMENT '创建时间',
  `update_at` DATETIME DEFAULT current_timestamp COMMENT '最后操作时间',
  `stateflag` TINYINT(2) NOT NULL DEFAULT 0 COMMENT '数据状态',
  `remark` VARCHAR(255) COMMENT '标签说明',

  `lender_id` INT COMMENT '借款人ID',
  `slender_ids` VARCHAR(255) COMMENT '共同借款人IDs',
  `guarantee_ids` VARCHAR(255) COMMENT '担保人IDs',
  `bank_manager_ids` VARCHAR(255) COMMENT '银行客户经理IDs',
  `other_ids` VARCHAR(255) COMMENT '其他IDs',
  `entrust_start_time` DATETIME COMMENT '委托开始时间',
  `entrust_end_time` DATETIME COMMENT '委托结束时间',
  `operator` INT DEFAULT 0 COMMENT '录入员',
  `accrual` DOUBLE(10,2) COMMENT '总利息',
  `loan` DOUBLE(10,2) COMMENT '总贷款',
  `appraisal` DOUBLE(10,2) COMMENT '总评估',
  `loan_type` VARCHAR(50) COMMENT '贷款类型',
  `loan_mode` VARCHAR(50) COMMENT '贷款方式',
  `loan_name` VARCHAR(50) COMMENT '贷款名称',
  `quality` VARCHAR(10) COMMENT '评优',
  `level` VARCHAR(10) COMMENT '评级',
  `dispose_mode` VARCHAR(20) COMMENT '处置方式',
  `tags` VARCHAR(255) COMMENT '添加标签',

  `urge_type` VARCHAR(20) COMMENT '催收阶段类型',
  `entrust_born_type` VARCHAR(20) COMMENT '委托来源类型',
  `entrust_born` VARCHAR(20) COMMENT '委托来源',
  `guarantee_type` VARCHAR(20) COMMENT '担保类型(他人担保|公司担保)',
  `guarantee_mode` VARCHAR(20) COMMENT '担保方式(抵押|质押)',
  `guarantee_source` VARCHAR(20) COMMENT '担保资源',
  `is_guarantee_connection` TINYINT(1) COMMENT '担保人是否能联系',
  `guarentee_economic` VARCHAR(20) COMMENT '担保人经济情况',
  `is_lawsuit` TINYINT(1) COMMENT '诉讼与否',
  `is_decision` TINYINT(1) COMMENT '判决与否',
  `real_urge_time` INT(3) COMMENT '实地催收次数',
  `phone_urge_time` INT(3) COMMENT '电话催收次数',
  `entrust_urge_time` INT(3) COMMENT '委托催收次数',
  `is_connection` INT(1) COMMENT '债务方是否能正常联系',
  `can_pay` TINYINT(1) COMMENT '债务方是否能偿还',
  `is_worth` TINYINT(1) COMMENT '抵押物能否覆盖债务',
  `memo` VARCHAR(255) COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 借款抵押物信息表
DROP TABLE IF EXISTS `t_pawn_info`;
CREATE TABLE `t_pawn_info`(
  `id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
  `version` INT NOT NULL DEFAULT 0 COMMENT '版本',
  `create_at` DATETIME DEFAULT current_timestamp COMMENT '创建时间',
  `update_at` DATETIME DEFAULT current_timestamp COMMENT '最后操作时间',
  `stateflag` TINYINT(2) NOT NULL DEFAULT 0 COMMENT '数据状态',
  `remark` VARCHAR(255) COMMENT '标签说明',

  `lender_id` INT COMMENT '借款人ID',
  `code` VARCHAR(20) COMMENT '编号',
  `amount` DOUBLE(10,2) COMMENT '贷款金额',
  `type` VARCHAR(20) COMMENT '抵押物类型',
  `quality` VARCHAR(10) COMMENT '评优',
  `level` VARCHAR(10) COMMENT '评级',
  `size` VARCHAR(50) COMMENT '规模大小',
  `province` VARCHAR(10) COMMENT '省份',
  `city` VARCHAR(10) COMMENT '城市',
  `district` VARCHAR(10) COMMENT '区域',
  `address` VARCHAR(255) COMMENT '地址',
  `pawn_rate` DOUBLE(10,2) COMMENT '抵押率',
  `dispose_status` VARCHAR(20) COMMENT '处置状态',
  `worth` DOUBLE(10,2) COMMENT '价值',
  `iou_ids` VARCHAR(255) COMMENT '借据IDs',
  `memo` VARCHAR(255) COMMENT '备注'

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 借据信息
DROP TABLE IF EXISTS `t_iou_info`;
CREATE TABLE `t_iou_info`(
  `id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
  `version` INT NOT NULL DEFAULT 0 COMMENT '版本',
  `create_at` DATETIME DEFAULT current_timestamp COMMENT '创建时间',
  `update_at` DATETIME DEFAULT current_timestamp COMMENT '最后操作时间',
  `stateflag` TINYINT(2) NOT NULL DEFAULT 0 COMMENT '数据状态',
  `remark` VARCHAR(255) COMMENT '标签说明',

  `code` VARCHAR(20) COMMENT '编号',
  `lender_id` INT COMMENT '借款人ID',
  `pawnIDs` VARCHAR(255) COMMENT '借据中的抵押物IDs',
  `type` VARCHAR(20) COMMENT '品种',
  `agency` VARCHAR(20) COMMENT '代理机构',
  `iou_code` VARCHAR(20) COMMENT '原始借据号',
  `loan_time` DATETIME COMMENT '放款时间',
  `loan_attime` DATETIME COMMENT '到款日期',
  `amount` DOUBLE(10,2) COMMENT '放款金额',
  `pact_rate` DOUBLE(10,2) COMMENT '合同利率',
  `outtime_multiple` DOUBLE(10, 2) COMMENT '逾期利率倍数',
  `appropriation_multiple` VARCHAR(5) COMMENT '挪用利率倍数',
  `accrual_repay` DOUBLE(10,2) COMMENT '已还利息金额',
  `loan_repay` DOUBLE(10,2) COMMENT '已还贷款金额',
  `level_type` VARCHAR(20) COMMENT '5级分类',
  `out_days` INT(5) COMMENT '逾期天数',
  `less_corpus` DOUBLE(10,2) COMMENT '剩余本金',
  `accrual_arrears` DOUBLE(10,2) COMMENT '拖欠利息',
  `penalty` DOUBLE(10,2) COMMENT '罚息',
  `arrears` DOUBLE(10,2) COMMENT '欠款合计',
  `out_time` DATETIME COMMENT '欠款截止日期',
  `worth` DOUBLE(10,2) COMMENT '抵押物银行估值',
  `advance_corpus` DOUBLE(10,2) COMMENT '提前偿还本金',
  `quality` VARCHAR(10) COMMENT '评优',
  `level` VARCHAR(10) COMMENT '评级',
  `memo` VARCHAR(255) COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 资料实勘
DROP TABLE IF EXISTS `t_source_info`;
CREATE TABLE `t_source_info`(
  `id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
  `version` INT NOT NULL DEFAULT 0 COMMENT '版本',
  `create_at` DATETIME DEFAULT current_timestamp COMMENT '创建时间',
  `update_at` DATETIME DEFAULT current_timestamp COMMENT '最后操作时间',
  `stateflag` TINYINT(2) NOT NULL DEFAULT 0 COMMENT '数据状态',
  `remark` VARCHAR(255) COMMENT '标签说明',

  `type` VARCHAR(20) COMMENT '文件类型',
  `pid` INT COMMENT '文件关联ID',
  `code` VARCHAR(20) COMMENT '文件编号',
  `name` VARCHAR(50) COMMENT '文件名称',
  `file_type` VARCHAR(20) COMMENT '文件类型',
  `path` VARCHAR(255) COMMENT '文件保存路径',
  `tags` VARCHAR(255) COMMENT '文件添加标签',
  `sort` INT DEFAULT 0 COMMENT '文件添加标签',
  `memo` VARCHAR(255) COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;