package com.dqys.auth.orm.query;

import com.dqys.core.base.BaseQuery;

import java.util.List;

/**
 * Created by Yvan on 16/6/27.
 */
public class TUserTagQuery extends BaseQuery{

    private Integer userId; // 用户ID
    private Integer userType; // 用户类型<平台方|委托方|处置方|个体用户|c用户>
    private Integer role; // 角色<管理员|负责人|参与人>
    private Integer certified; // 认证

    private List<Integer> userTypes; // 用户类型集合<机构0|个人1>

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

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getCertified() {
        return certified;
    }

    public void setCertified(Integer certified) {
        this.certified = certified;
    }

    public List<Integer> getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(List<Integer> userTypes) {
        this.userTypes = userTypes;
    }
}
