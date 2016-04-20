package com.dqys.auth.orm.pojo;

import com.dqys.core.base.BaseModel;
import java.io.Serializable;

public class TUserTag extends BaseModel implements Serializable {

    private int userId;

    private Byte userType;

    private Byte roleId;

    private Boolean isCertified;

    private static final long serialVersionUID = 1L;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public Byte getRoleId() {
        return roleId;
    }

    public void setRoleId(Byte roleId) {
        this.roleId = roleId;
    }

    public Boolean getIsCertified() {
        return isCertified;
    }

    public void setIsCertified(Boolean isCertified) {
        this.isCertified = isCertified;
    }
}