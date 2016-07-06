package com.dqys.business.orm.pojo.company;

import com.dqys.core.base.BaseModel;


/**
 * 组织对象<标签|部门|团队|职业>
 */
public class Organization extends BaseModel {

    private String type; // 组织类型
    private Integer pid; // 组织上级
    private String name; // 组织名称
    private Integer userId; // 组织负责人
    private Integer companyId; // 所属公司

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}