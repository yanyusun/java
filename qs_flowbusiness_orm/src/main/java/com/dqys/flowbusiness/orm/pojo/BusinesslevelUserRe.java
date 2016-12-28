package com.dqys.flowbusiness.orm.pojo;

import java.util.Date;

public class BusinesslevelUserRe {
    private Integer id;

    private Integer businesslevelreId;

    private Integer roleType;

    private Integer status;

    private Date createAt;

    private Date updateAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBusinesslevelreId() {
        return businesslevelreId;
    }

    public void setBusinesslevelreId(Integer businesslevelreId) {
        this.businesslevelreId = businesslevelreId;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}