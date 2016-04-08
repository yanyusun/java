package com.dqys.core.model;

/**
 * 用户会话
 *
 * @author by pan on 9/21/15.
 */
public class MemberSession {

    private static ThreadLocal<MemberSession> sessionHolder = new ThreadLocal<MemberSession>();

    private int userId;
    private int userType;
    private int roleId;

    public static MemberSession getCurrent() {
        return sessionHolder.get();
    }

    public static void setCurrent(MemberSession memberSession) {
        sessionHolder.set(memberSession);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
