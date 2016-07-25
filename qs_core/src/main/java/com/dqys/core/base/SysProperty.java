package com.dqys.core.base;

/**
 * Created by Yvan on 16/7/21.
 *
 * 通用的系统级配置
 */
public class SysProperty {

    // 控制查询搜索不到数据时填充的限制数据
    public static final Integer NULL_DATA_ID = 0;

    // 只存在true|false情况下数据填充
    public static final Integer BOOLEAN_TRUE = 1;
    public static final Integer BOOLEAN_FALSE = 0;

    // 存在init|true|false情况下填充(如:待处理,接收|不接受)
    public static final Integer STATUS_INIT = 0;
    public static final Integer STATUS_TRUE = 1;
    public static final Integer STATUS_FALSE = 2;

    // 官方地址
    public static final String SYS_WEB_DOMAIN = "sys_web_domain"; // 官方网站域名
    public static final String SYS_SOURCE_WEB = "sys_source_domain"; // 资源网站域名


}
