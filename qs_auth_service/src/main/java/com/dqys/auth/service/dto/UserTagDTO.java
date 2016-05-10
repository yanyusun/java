package com.dqys.auth.service.dto;

import com.dqys.core.base.BaseDTO;

/**
 * @author by pan on 16-5-9.
 */
public class UserTagDTO extends BaseDTO {

    private Byte userType;

    private Byte roleId;

    private Boolean isCertified;

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

    public Boolean getCertified() {
        return isCertified;
    }

    public void setCertified(Boolean certified) {
        isCertified = certified;
    }
}
