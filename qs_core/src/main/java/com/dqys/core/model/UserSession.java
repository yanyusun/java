package com.dqys.core.model;

/**
 * 用户会话
 *
 * @author by pan on 9/21/15.
 */
public class UserSession {

    private static ThreadLocal<UserSession> sessionHolder = new ThreadLocal<UserSession>();

    private Integer userId;
    private String userType;
    private String roleId;
    private Integer status;
    private String isCertified;

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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIsCertified() {
        return isCertified;
    }

    public void setIsCertified(String isCertified) {
        this.isCertified = isCertified;
    }

}
