package com.dqys.business.service.constant.source;

/**
 * 资源类型
 * Created by yan on 17-1-12.
 */
public enum SourceTypeEnum {
    source_folder(10,"资料实勘文件夹"),
    follow_up_folder(11, "跟进文件夹"),

    source_file(20,"资料实勘文件"),
    follow_up_file(21,"跟进文件");

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

    SourceTypeEnum(Integer value, String name) {

        this.value = value;
        this.name = name;
    }
}
