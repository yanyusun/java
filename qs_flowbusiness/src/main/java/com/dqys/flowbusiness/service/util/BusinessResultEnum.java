package com.dqys.flowbusiness.service.util;

/**
 * Created by yan on 16-12-27.
 */
public enum BusinessResultEnum {
    /**
     * 资产发布业务
     */
    sucesss(2000, "操作成功"),
    not_find(11001,"业务记录找不到"),
    level_error(11002,"流程不在当前阶段"),
    auth_error(11003,"用户没有该权限"),
    param_error(11004,"缺少参数"),
    flow_re_error(11005,"流转关系错误")
    ;
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
