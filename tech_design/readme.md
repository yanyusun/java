# 基础模块
    对象:
        属性:id - 主键,
            version - 修改版本, 
            create_at - 创建时间,
            update_at - 最后操作时间,
            stateflag - 数据状态(0正常,删除时为删除时间的时间戳),
            remark - 说明标签
            
            
# 通用数据状态
    当存在 true|flase 情况下,0表示否定,1表示肯定 

# 项目模块
    auth  -- 授权模块,存在权限信息的都将存放在这个模块当中
    business  --  
    captcha  --  验证码模块
    core  --  核心基础模块,存放基础工具类
    resource  --  资源模块
    wms  --  短信模块
    

    




