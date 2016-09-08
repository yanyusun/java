package com.dqys.business.orm.pojo.coordinator;

import java.util.Date;

public class UserTeam {
    private Integer id;

    private Date createAt;

    private String remark;

    private Integer companyId;

    private Integer mangerId;

    private Integer status;

    private Integer ctreaterId;

    private Integer objectId;

    private Integer objectType;

    private Integer version;

    private Date updateAt;

    private Long stateflag;

    private Integer objectOperStatus;

    public Integer getObjectOperStatus() {
        return objectOperStatus;
    }

    public void setObjectOperStatus(Integer objectOperStatus) {
        this.objectOperStatus = objectOperStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getMangerId() {
        return mangerId;
    }

    public void setMangerId(Integer mangerId) {
        this.mangerId = mangerId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCtreaterId() {
        return ctreaterId;
    }

    public void setCtreaterId(Integer ctreaterId) {
        this.ctreaterId = ctreaterId;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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