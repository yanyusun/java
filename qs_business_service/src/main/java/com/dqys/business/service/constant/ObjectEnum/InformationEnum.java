package com.dqys.business.service.constant.ObjectEnum;

/**
 * 资料实勘
 * Created by mkfeng on 2016/7/7.
 */
public enum InformationEnum {
    UPLOAD(141, "上传"),
    DELETE(142, "删除"),
    UPDATE(143, "修改"),
    LOOK(144, "查看"),
    ADD_TYPE(145, "添加标签"),
    UPD_TYPE(146, "重命名分类"),
    DEL_TYPE(147, "删除分类"),
    CERTIFICATE_TYPE(0, "证件合同"),
    REAL_TYPE(1, "实勘"),;
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

    InformationEnum(Integer value, String name) {

        this.value = value;
        this.name = name;
    }
}
