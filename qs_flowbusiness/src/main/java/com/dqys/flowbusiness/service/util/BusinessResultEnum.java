package com.dqys.flowbusiness.service.util;

/**
 * Created by yan on 16-12-27.
 */
public enum BusinessResultEnum {
    /**
     * 资产发布业务
     */
    sucesss(100, "操作成功"),
    not_find(1,"业务记录找不到"),
    level_error(2,"流程不在当前阶段"),
    auth_error(3,"用户没有该权限");
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

    BusinessResultEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}
