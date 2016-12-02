package com.dqys.business.orm.constant.flowBusiness;

/**
 * Created by mkfeng on 2016/12/2.
 */
public enum FlowBusinessEnum {
    FLOW_STATUS_NO(0, "平台未同意"),
    FLOW_STATUS_YES(1, "平台同意"),
    FLOW_STATUS_FINISH(100, "完成"),
    FLOW_COMPANY_WAIT_ADD(0, "待添加"),
    FLOW_COMPANY_WAIT_AGREE(1, "待同意"),
    FLOW_COMPANY_AGREE(2, "已同意"),
    FLOW_COMPANY_REFUSE(3, "已拒绝"),
    FLOW_COMPANY_EXIST(-1, "已有该机构");

    private Integer value;
    private String name;

    FlowBusinessEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

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
}
