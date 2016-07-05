package com.dqys.business.orm.pojo.operType;

import sun.plugin2.message.Serializer;

import java.io.Serializable;

/**
 * Created by mkfeng on 2016/7/1.
 */
public class OperType implements Serializable {
    private Integer id;
    private Integer operType;//操作类型
    private String operName;//操作名称
    private String userid;//操作名称
    private String roleId;//操作名称
    private String ObjectTpey;//操作名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getObjectTpey() {
        return ObjectTpey;
    }

    public void setObjectTpey(String objectTpey) {
        ObjectTpey = objectTpey;
    }
}
