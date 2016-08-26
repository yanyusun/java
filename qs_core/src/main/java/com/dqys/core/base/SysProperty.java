package com.dqys.core.base;

/**
 * Created by Yvan on 16/7/21.
 *
 * 通用的系统级配置
 */
public class SysProperty {
    // 默认的分页数据
    public static final Integer DEFAULT_PAGE = 1;
    public static final Integer DEFAULT_PAGE_SIZE = 20;

    // 控制查询搜索不到数据时填充的限制数据
    public static final Integer NULL_DATA_ID = 0;

    // 只存在true|false情况下数据填充
    public static final Integer BOOLEAN_TRUE = 1;
    public static final Integer BOOLEAN_FALSE = 0;

    // 数据状态
    public static final Integer DEFAULT = 0;

    // 存在init|true|false情况下填充(如:待处理,接收|不接受)
    public static final Integer STATUS_INIT = 0;
    public static final Integer STATUS_TRUE = 1;
    public static final Integer STATUS_FALSE = 2;


    // 短信模板
    public static final Integer SMS_VERIFICATION_CODE = 101; // 验证码

    public static final Integer SMS_DISTRIBUTION_INVITE_CODE = 102; // 分配器邀请加入
    public static final Integer SMS_DISTRIBUTION_PASS_CODE = 114; // 分配器接收邀请
    public static final Integer SMS_DISTRIBUTION_REFUSE_CODE = 115; // 分配器拒绝邀请
    public static final Integer SMS_DISTRIBUTION_JOIN_CODE = 117; // 分配器申请加入
    public static final Integer SMS_DISTRIBUTION_JOIN_REFUSE_CODE = 118; // 分配器拒绝申请
    public static final Integer SMS_DISTRIBUTION_JOIN_PASS_CODE = 119; // 分配器同意申请

    public static final Integer SMS_COORDINATOR_INVITE_CODE = 103; // 协作器邀请加入
    public static final Integer SMS_COORDINATOR_PASS_CODE = 112; // 协作器接收邀请
    public static final Integer SMS_COORDINATOR_REFUSE_CODE = 113; // 协作器拒绝邀请
    public static final Integer SMS_COORDINATOR_JOIN_CODE = 104; // 协作器申请加入
    public static final Integer SMS_COORDINATOR_JOIN_REFUSE_CODE = 120; // 协作器拒绝申请
    public static final Integer SMS_COORDINATOR_JOIN_PASS_CODE = 121; // 协作器同意申请

    public static final Integer SMS_CHECK_PASS_CODE = 105; // 平台审核通过
    public static final Integer SMS_CHECK_REFUSE_CODE = 106; // 平台审核不通过

    public static final Integer SMS_STOP_CODE = 107; // 公司暂停清收工作
    public static final Integer SMS_START_CODE = 108; // 公司开启清收工作
    public static final Integer SMS_OUT_CODE = 116; // 移除工作

    public static final Integer SMS_GET_LATE_CODE = 109; // 申请延期
    public static final Integer SMS_PASS_LATE_CODE = 110; // 通过延期
    public static final Integer SMS_REFUSE_LATE_CODE = 111; // 驳回延期


    // 官方地址
    public static final String SYS_WEB_DOMAIN = "sys_web_domain"; // 官方网站域名
    public static final String SYS_SOURCE_WEB = "sys_source_domain"; // 资源网站域名


}
