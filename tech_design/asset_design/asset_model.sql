

-- 关系表删除
DROP TABLE IF EXISTS `bt_pi_relation`;
DROP TABLE IF EXISTS `bt_ci_relation`;


-- 资产包信息
DROP TABLE IF EXISTS `bt_asset`;
CREATE TABLE `bt_asset`(
  `id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
  `version` INT NOT NULL DEFAULT 0 COMMENT '版本',
  `create_at` DATETIME DEFAULT current_timestamp COMMENT '创建时间',
  `update_at` DATETIME DEFAULT current_timestamp COMMENT '最后操作时间',
  `stateflag` BIGINT NOT NULL DEFAULT 0 COMMENT '数据状态',
  `remark` VARCHAR(255) COMMENT '标签说明',

  `asset_no` VARCHAR(20) COMMENT '编号',
  `type` TINYINT(2) COMMENT '资产包类型',
  `start_at` DATETIME COMMENT '委托开始时间',
  `end_at` DATETIME COMMENT '委托结束时间',
  `operator` INT DEFAULT 0 COMMENT '录入员',
  `accrual` DOUBLE(10,2) COMMENT '总利息',
  `loan` DOUBLE(10,2) COMMENT '总贷款',
  `appraisal` DOUBLE(10,2) COMMENT '总评估',
  `name` VARCHAR(50) COMMENT '资源包名称',
  `evaluate_excellent` VARCHAR(10) COMMENT '评优',
  `evaluate_level` VARCHAR(10) COMMENT '评级',
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


-- 联系人信息
DROP TABLE IF EXISTS `bt_contact`;
CREATE TABLE `bt_contact`(
  `id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
  `version` INT NOT NULL DEFAULT 0 COMMENT '版本',
  `create_at` DATETIME DEFAULT current_timestamp COMMENT '创建时间',
  `update_at` DATETIME DEFAULT current_timestamp COMMENT '最后操作时间',
  `stateflag` BIGINT NOT NULL DEFAULT 0 COMMENT '数据状态',
  `remark` VARCHAR(255) COMMENT '标签说明',

  `mode` VARCHAR(20) COMMENT '联系人模块',
  `mode_id` INT COMMENT '联系人模块ID',
  `name` VARCHAR(20) COMMENT '姓名',
  `code` VARCHAR(20) COMMENT '编号',
  `type` TINYINT(1) COMMENT '借款人类型(借款人|共同借款人|担保方|银行客户经理|其他)',
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
  `memo` VARCHAR(255) COMMENT '备注',

  INDEX (`mode`, `mode_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 借款人基础信息表
DROP TABLE IF EXISTS `bt_lender`;
CREATE TABLE `bt_lender`(
  `id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
  `version` INT NOT NULL DEFAULT 0 COMMENT '版本',
  `create_at` DATETIME DEFAULT current_timestamp COMMENT '创建时间',
  `update_at` DATETIME DEFAULT current_timestamp COMMENT '最后操作时间',
  `stateflag` BIGINT NOT NULL DEFAULT 0 COMMENT '数据状态',
  `remark` VARCHAR(255) COMMENT '标签说明',

  `contact_id` INT COMMENT '借款人ID',
  `start_at` DATETIME COMMENT '委托开始时间',
  `end_at` DATETIME COMMENT '委托结束时间',
  `operator` INT DEFAULT 0 COMMENT '录入员',
  `accrual` DOUBLE(10,2) COMMENT '总利息',
  `loan` DOUBLE(10,2) COMMENT '总贷款',
  `appraisal` DOUBLE(10,2) COMMENT '总评估',
  `loan_type` VARCHAR(50) COMMENT '贷款类型',
  `loan_mode` VARCHAR(50) COMMENT '贷款方式',
  `loan_name` VARCHAR(50) COMMENT '贷款名称',
  `evaluate_excellent` VARCHAR(10) COMMENT '评优',
  `evaluate_level` VARCHAR(10) COMMENT '评级',
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
  `can_contact` INT(1) COMMENT '债务方是否能正常联系',
  `can_pay` TINYINT(1) COMMENT '债务方是否能偿还',
  `is_worth` TINYINT(1) COMMENT '抵押物能否覆盖债务',
  `memo` VARCHAR(255) COMMENT '备注',

  INDEX(`contact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 借款抵押物信息表
DROP TABLE IF EXISTS `bt_pawn`;
CREATE TABLE `bt_pawn`(
  `id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
  `version` INT NOT NULL DEFAULT 0 COMMENT '版本',
  `create_at` DATETIME DEFAULT current_timestamp COMMENT '创建时间',
  `update_at` DATETIME DEFAULT current_timestamp COMMENT '最后操作时间',
  `stateflag` BIGINT NOT NULL DEFAULT 0 COMMENT '数据状态',
  `remark` VARCHAR(255) COMMENT '标签说明',

  `lender_id` INT NOT NULL COMMENT '借款人ID',
  `pawn_no` VARCHAR(20) COMMENT '编号',
  `amount` DOUBLE(10,2) COMMENT '贷款金额',
  `type` VARCHAR(20) COMMENT '抵押物类型',
  `evaluate_excellent` VARCHAR(10) COMMENT '评优',
  `evaluate_level` VARCHAR(10) COMMENT '评级',
  `size` VARCHAR(50) COMMENT '规模大小',
  `province` VARCHAR(10) COMMENT '省份',
  `city` VARCHAR(10) COMMENT '城市',
  `district` VARCHAR(10) COMMENT '区域',
  `address` VARCHAR(255) COMMENT '地址',
  `pawn_rate` DOUBLE(10,2) COMMENT '抵押率',
  `dispose_status` VARCHAR(20) COMMENT '处置状态',
  `worth` DOUBLE(10,2) COMMENT '价值',
  `memo` VARCHAR(255) COMMENT '备注',

  INDEX (`lender_id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 借据信息
DROP TABLE IF EXISTS `bt_iou`;
CREATE TABLE `bt_iou`(
  `id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
  `version` INT NOT NULL DEFAULT 0 COMMENT '版本',
  `create_at` DATETIME DEFAULT current_timestamp COMMENT '创建时间',
  `update_at` DATETIME DEFAULT current_timestamp COMMENT '最后操作时间',
  `stateflag` BIGINT NOT NULL DEFAULT 0 COMMENT '数据状态',
  `remark` VARCHAR(255) COMMENT '标签说明',

  `iou_no` VARCHAR(20) COMMENT '编号',
  `lender_id` INT NOT NULL COMMENT '借款人ID',
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
  `end_at` DATETIME COMMENT '欠款截止日期',
  `worth` DOUBLE(10,2) COMMENT '抵押物银行估值',
  `advance_corpus` DOUBLE(10,2) COMMENT '提前偿还本金',
  `evaluate_excellent` VARCHAR(10) COMMENT '评优',
  `evaluate_level` VARCHAR(10) COMMENT '评级',
  `memo` VARCHAR(255) COMMENT '备注',

  INDEX (`lender_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 资料实勘
DROP TABLE IF EXISTS `bt_source`;
CREATE TABLE `bt_source`(
  `id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
  `version` INT NOT NULL DEFAULT 0 COMMENT '版本',
  `create_at` DATETIME DEFAULT current_timestamp COMMENT '创建时间',
  `update_at` DATETIME DEFAULT current_timestamp COMMENT '最后操作时间',
  `stateflag` BIGINT NOT NULL DEFAULT 0 COMMENT '数据状态',
  `remark` VARCHAR(255) COMMENT '标签说明',

  `mode` VARCHAR(20) COMMENT '资源模块',
  `mode_id` INT COMMENT '资源模块Id',
  `type` VARCHAR(20) COMMENT '资源类型',
  `code` VARCHAR(20) COMMENT '文件编号',
  `name` VARCHAR(50) COMMENT '文件名称',
  `file_type` VARCHAR(20) COMMENT '文件类型',
  `path` VARCHAR(255) COMMENT '文件保存路径',
  `tags` VARCHAR(255) COMMENT '文件添加标签',
  `sort` INT DEFAULT 0 COMMENT '文件添加标签',
  `memo` VARCHAR(255) COMMENT '备注',

  INDEX(`mode`, `mode_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 案件信息
DROP TABLE IF EXISTS `bt_case`;
CREATE TABLE `bt_case`(
  `id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
  `version` INT NOT NULL DEFAULT 0 COMMENT '版本',
  `create_at` DATETIME DEFAULT current_timestamp COMMENT '创建时间',
  `update_at` DATETIME DEFAULT current_timestamp COMMENT '最后操作时间',
  `stateflag` BIGINT NOT NULL DEFAULT 0 COMMENT '数据状态',
  `remark` VARCHAR(255) COMMENT '标签说明',

  `case_no` VARCHAR(20) COMMENT '编码',
  `type` VARCHAR(20) DEFAULT 0 COMMENT '案件类型(母案件,子案件)',
  `pid` INT DEFAULT 0 COMMENT '父级ID',
  `pawn_id` INT COMMENT '抵押物ID',
  `plaintiff` VARCHAR(20) COMMENT '原告',
  `defendant` VARCHAR(20) COMMENT '被告',
  `spouse` VARCHAR(20) COMMENT '配偶',
  `guarantor_id` VARCHAR(255) COMMENT '保证人',
  `mortgagor` VARCHAR(255) COMMENT '抵押人',
  `mortgage_time` VARCHAR(255) COMMENT '抵押次数',

  `lawsuit_amount` DOUBLE(10,2) COMMENT '诉讼标金额',
  `lawsuit_corpus` DOUBLE(10,2) COMMENT '诉讼标本金',
  `lawsuit_accrual` DOUBLE(10,2) COMMENT '诉讼标利息',
  `lawsuit_memo` text COMMENT '诉讼备注',

  `is_attachment` TINYINT(1) COMMENT '是否查封',
  `attachment_time` TINYINT(1) COMMENT '查封次数',
  `attachment_code` VARCHAR(50) COMMENT '查封案号',
  `attachment_court` VARCHAR(50) COMMENT '查封法院',
  `attachment_date` DATETIME COMMENT '查封法院',
  `attachment_memo` TEXT COMMENT '查封备注',
  `is_preservation` TINYINT(1) COMMENT '是否保全',
  `preservation_start` DATETIME COMMENT '保全开始时间',
  `preservation_end` DATETIME COMMENT '保全结束时间',
  `preservation_memo` TEXT COMMENT '保全情况',

  `memo` VARCHAR(255) COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 抵押物&借据关系表
DROP TABLE IF EXISTS `bt_pi_relation`;
CREATE TABLE `bt_pi_relation`(
  `id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
  `version` INT NOT NULL DEFAULT 0 COMMENT '版本',
  `create_at` DATETIME DEFAULT current_timestamp COMMENT '创建时间',
  `update_at` DATETIME DEFAULT current_timestamp COMMENT '最后操作时间',
  `stateflag` BIGINT NOT NULL DEFAULT 0 COMMENT '数据状态',
  `remark` VARCHAR(255) COMMENT '标签说明',

  `pawn_id` INT COMMENT '抵押物ID',
  `iou_id` INT COMMENT '借据id',

  FOREIGN KEY pi_pawn_key(`pawn_id`) REFERENCES bt_pawn(`id`),
  FOREIGN KEY pi_iou_key(`iou_id`) REFERENCES bt_iou(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 借据&案件信息关系表
DROP TABLE IF EXISTS `bt_ci_relation`;
CREATE TABLE `bt_ci_relation`(
  `id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
  `version` INT NOT NULL DEFAULT 0 COMMENT '版本',
  `create_at` DATETIME DEFAULT current_timestamp COMMENT '创建时间',
  `update_at` DATETIME DEFAULT current_timestamp COMMENT '最后操作时间',
  `stateflag` BIGINT NOT NULL DEFAULT 0 COMMENT '数据状态',
  `remark` VARCHAR(255) COMMENT '标签说明',

  `case_id` INT COMMENT '案件ID',
  `iou_id` INT COMMENT '借据id',

  FOREIGN KEY ic_case_key(`case_id`) REFERENCES bt_pawn(`id`),
  FOREIGN KEY ic_iou_key(`iou_id`) REFERENCES bt_iou(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

