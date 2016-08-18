package com.dqys.core.constant;

/**
 * @author by pan on 16-5-3.
 */
public class KeyEnum {

    public static final String SYS_PROTOCOL_SALT_KEY = "sys_protocol_salt";     //加密密钥
    public static final String SYS_USER_EXPIRE_DAYS_KEY = "sys_user_login_expire";      //登陆状态保持时间
    public static final String SYS_ACCESS_ORIGIN_KEY = "sys_access_origin";     //允许远程访问的主机
    public static final String SYS_MAIL_HOST = "sys_mail_host";     //发送邮件smtp服务器地址
    public static final String SYS_MAIL_SMTP_PORT = "sys_mail_smtp_port";       //发送邮件smtp短裤
    public static final String SYS_MAIL_SSL_ON = "sys_mail_ssl_on";     //发送邮件SSL开启状态
    public static final String SYS_MAIL_FROM = "sys_mail_from";        //邮件发送者
    public static final String SYS_MAIL_AUTH_USERNAME = "sys_mail_auth_username";       //发送邮件用户名
    public static final String SYS_MAIL_AUTH_PASSWORD = "sys_mail_auth_password";       //发送邮件密码
    public static final String SYS_CAPTCHA_FRE_LIMIT = "sys_captcha_fre_limit";     //图片验证码生成频率
    public static final String SYS_SMS_FRE_LIMIT = "sys_sms_fre_limit";     //短信验证码发送频率
    public static final String SYS_FILE_UPLOAD_PATH_KEY = "sys_uploadPath";     //上传文件路径
    public static final String SYS_FILE_OPEN_PATH_KEY = "sys_file_open_Path";     //上传文件路径(对外开放)
    public static final String SYS_TMP_DEL_TIMER_KEY = "sys_file_tmp_del_timer";        //临时文件自动删除时间 hour
    public static final String SYS_AUTH_URL_KEY = "sys_auth_url";       //权限URL


    /* 用户类型key */
    public static final String U_TYPE_COMMON = "u_type_common_user"; // 普通用户
    public static final String U_TYPE_ENTRUST = "u_type_delegate_account"; // 委托号
    public static final String U_TYPE_URGE = "u_type_arrears_caller"; // 催收
    public static final String U_TYPE_LAW = "u_type_law_office"; // 律所
    public static final String U_TYPE_INTERMEDIARY = "u_type_intermediary"; // 中介
    public static final String U_TYPE_PLATFORM = "u_type_platform"; // 平台

    public static final String UTYPE_PLATFORM_KEY = "u_type_platform";

    /* 角色配置key */
    public static final String ROLE_ADMINISTRATOR_KEY = "role_administrator"; // 管理员
    public static final String ROLE_MANAGER_KEY = "role_manager"; // 管理者
    public static final String ROLE_EMPLOYEE = "role_employee"; // 普通员工

    /* api配置key */
    public static final String API_SYS_PROPERTY_KEY = "api_sys_property";
    public static final String API_AREA_LIST = "api_area_list";

    /* 文件上传格式限制配置 */
    public static final String UPLOAD_PERMIT_IMG = "upload_permit_img"; // 图片格式
    public static final String UPLOAD_PERMIT_OFFICE = "upload_permit_office"; // 办公文件
    public static final String UPLOAD_PERMIT_AUDIO = "upload_permit_sound"; // 音频
    public static final String UPLOAD_PERMIT_VIDEO = "upload_permit_video"; // 视频


    /* 创蓝短信账号配置key */
    public static final String SMS_URL = "http://222.73.117.156/msg/HttpBatchSendSM";
    public static final String SMS_ACCOUNT = "qingsou";
    public static final String SMS_PASSWORD = "Tch123456";



}
