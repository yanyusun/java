package com.dqys.business.service.constant.ObjectEnum;

/**
 * 资产包
 * Created by mkfeng on 2016/7/7.
 */
public enum AssetPackageEnum {
    delete(102, "删除"),
    update(101, "编辑"),
    add(100, "添加"),

    //列表显示
    VIEW_OPERATION_LOG(1000, "操作日志"),
    VIEw_ACCPET(1001,"同意接收"),
    VIEW_REJECT(1002,"拒绝接收");

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

    AssetPackageEnum(Integer value, String name) {

        this.value = value;
        this.name = name;
    }
}
