package com.dqys.business.orm.pojo.common;

public class NavUnviewRole {
    private Integer id;

    private Integer navId;

    private Integer roleType;
    private Integer object;

    private Integer objectId;

    private Integer operUser;

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

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public Integer getOperUser() {
        return operUser;
    }

    public void setOperUser(Integer operUser) {
        this.operUser = operUser;
    }
}