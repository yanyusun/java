package com.dqys.core.constant;

/**
 * @author by pan on 16-3-16.
 *         <p>
 *         系统配置类型枚举
 */
public enum SysPropertyTypeEnum {
    SYS(1, "系统"),
    API(2, "接口"),
    USER_TYPE(3, "用户类型"),
    ROLE(4, "角色"),
    FILE_BUSINESS_TYPE(5, "文件业务类型"),
    ;


    private Integer value;
    private String desc;

    SysPropertyTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static SysPropertyTypeEnum findByValue(Integer type) {
        for(SysPropertyTypeEnum e : SysPropertyTypeEnum.values()) {
            if(e.getValue().equals(type)) {
                return e;
            }
        }

        return null;
    }
}
