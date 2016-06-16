# 资产包模块
    ## 资产包
        
        
        
        
    ## 借款人
    
    ## 抵押物
    
    ## 借据
    
    ## 案件
    
# 表结构
    2个外键关联关系表 
        bt_pi_relation  -- 抵押物&借据
        bt_ci_relation  -- 案件&借据
    6个基础信息表
        bt_asset  -- 资产包表
        bt_contact  -- 联系人表
        bt_lender  -- 借款人基础信息表
        bt_pawn  -- 抵押物表
        bt_iou  -- 借据表
        bt_case  -- 案件表
    1个公共表
        bt_source  -- 资源表