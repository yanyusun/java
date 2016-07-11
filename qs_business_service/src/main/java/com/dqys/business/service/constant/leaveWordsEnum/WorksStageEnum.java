package com.dqys.business.service.constant.leaveWordsEnum;

/**
 * 留言阶段
 * Created by mkfeng on 2016/7/8.
 */
public enum WorksStageEnum {
    ENTRUST_APPLY(0, "委托申请"),
    PLATFORM_CONTROL(1, "平台调控"),
    COLLECTION(2, "催收"),
    JUSTICE_RESOLVE(3, "司法化解"),
    MARKET_DISPOSITION(4, "市场处置"),
    FINISH(5, "已完成"),;
    private Integer value;
    private String name;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    WorksStageEnum(Integer value, String name) {

        this.value = value;
        this.name = name;
    }
}


