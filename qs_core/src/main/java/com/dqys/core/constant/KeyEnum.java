package com.dqys.core.constant;

/**
 * @author by pan on 16-5-3.
 */
public class KeyEnum {

    /* 系统配置key */
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
    public static final String SYS_TMP_DEL_TIMER_KEY = "sys_file_tmp_del_timer";        //临时文件自动删除时间 hour
    public static final String SYS_AUTH_URL_KEY = "sys_auth_url";       //权限URL


    /* 用户类型key */
    public static final String UTYPE_PLATFORM_KEY = "u_type_platform";

    /* 角色配置key */
    public static final String ROLE_ADMINISTRATOR_KEY = "role_administrator";
    public static final String ROLE_MANAGER_KEY = "role_manager";

    /* api配置key */
    public static final String API_SYS_PROPERTY_KEY = "api_sys_property";
    public static final String API_AREA_LIST = "api_area_list";


}