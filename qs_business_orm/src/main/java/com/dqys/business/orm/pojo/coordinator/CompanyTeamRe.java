package com.dqys.business.orm.pojo.coordinator;

import java.io.Serializable;
import java.util.Date;

public class CompanyTeamRe implements Serializable {
    private Integer id;

    private Integer acceptCompanyId;

    private Integer companyTeamId;

    private Integer status;

    private Integer type;

    private Integer accepterId;

    private Integer version;

    private Date createAt;

    private Date updateAt;

    private Long stateflag;

    private Integer roleType; // 角色类型
    private Integer requesterId; // 请求公司ID


    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public Integer getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Integer requesterId) {
        this.requesterId = requesterId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAcceptCompanyId() {
        return acceptCompanyId;
    }

    public void setAcceptCompanyId(Integer acceptCompanyId) {
        this.acceptCompanyId = acceptCompanyId;
    }

    public Integer getCompanyTeamId() {
        return companyTeamId;
    }

    public void setCompanyTeamId(Integer companyTeamId) {
        this.companyTeamId = companyTeamId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAccepterId() {
        return accepterId;
    }

    public void setAccepterId(Integer accepterId) {
        this.accepterId = accepterId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public Long getStateflag() {
        return stateflag;
    }

    public void setStateflag(Long stateflag) {
        this.stateflag = stateflag;
    }
}