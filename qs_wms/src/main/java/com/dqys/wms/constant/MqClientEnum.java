package com.dqys.wms.constant;

/**
 * Created by mkfeng on 2016/8/29.
 */
public enum MqClientEnum {
    EMAIL_URL("http://rs.iqingsou.com/typeChoose/");//邮箱确认网址
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    MqClientEnum(String name) {

        this.name = name;
    }
}
