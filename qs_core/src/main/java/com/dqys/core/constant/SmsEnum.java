package com.dqys.core.constant;

/**
 * Created by mkfeng on 2016/8/11.
 */
public enum SmsEnum {
    REGISTER(101),//注册
    INVITE_COORDINATOR(102),//任务邀请（协作器）
    INVITE_DISTRIBUTOR(103),//任务邀请（分配器）
    ;
    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    SmsEnum(Integer value) {
        this.value = value;
    }
}
