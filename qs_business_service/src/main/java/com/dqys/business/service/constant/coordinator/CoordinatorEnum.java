package com.dqys.business.service.constant.coordinator;

/**
 * Created by mkfeng on 2016/7/15.
 */
public enum CoordinatorEnum {
    disposeMes("处置消息"),
    taskMes("任务消息"),;

    private String name;

    CoordinatorEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
