package com.dqys.business.orm.pojo.common;

import java.util.Date;

public class NavUnviewUserType {
    private Integer id;

    private Integer navId;

    private Integer userType;

    private Integer version;

    private Date createAt;

    private Date updateAt;

    private Long stateflag;

    private Integer operUser;
    private Integer object;

    private Integer objectId;

    public Integer getObject() {
        return object;
    }

    public void setObject(Integer object) {
        this.object = object;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNavId() {
        return navId;
    }

    public void setNavId(Integer navId) {
        this.navId = navId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
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

    public Integer getOperUser() {
        return operUser;
    }

    public void setOperUser(Integer operUser) {
        this.operUser = operUser;
    }
}