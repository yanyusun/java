package com.dqys.business.service.constant.ObjectEnum;

/**
 * 资产包
 * Created by mkfeng on 2016/7/7.
 */
public enum AssetPackageEnum implements ObjectEnumBase{
    delete(1010, "删除"),
    update(101, "编辑"),
    add(100, "添加"),
    OPERATION_LOG(102, "操作日志"),
    AUDIT_YES(103, "审核通过"),
    AUDIT_NO(104, "审核不通过"),
    PAUSE(105, "暂停"),
    RESTART_APPLY(106, "重新发布"),//重新申请
    INVALID_SET(107, "设置为无效"),
    INVALID_SET_RECOVER(108, "无效恢复"),
    PAUSE_RECOVER(109, "暂停恢复");

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
