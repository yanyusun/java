package com.dqys.core.model;

/**
 * 用户会话
 *
 * @author by pan on 9/21/15.
 */
public class UserSession {

    private static ThreadLocal<UserSession> sessionHolder = new ThreadLocal<UserSession>();

    private Integer userId;
    private Integer userType;
    private Integer roleId;
    private Boolean status;
    private Boolean isCertified;

    public static UserSession getCurrent() {
        return sessionHolder.get();
    }

    public static void setCurrent(UserSession userSession) {
        sessionHolder.set(userSession);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getCertified() {
        return isCertified;
    }

    public void setCertified(Boolean certified) {
        isCertified = certified;
    }
}
