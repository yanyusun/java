package com.dqys.auth.orm.pojo;

/**
 * Created by mkfeng on 2016/11/3.
 */
public class LoginLog {
    private Integer id;//bigint(20) NOT NULL AUTO_INCREMENT,
    private Integer userId;//int(11) DEFAULT NULL COMMENT '登入用户',
    private String time;//timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '登入时间',
    private String ip;//varchar(50) DEFAULT NULL COMMENT 'ip地址',

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
