package com.dqys.business.service.dto.sourceAuth;

import java.util.List;

/**
 * 不可见关联对象map
 * Created by yan on 16-11-3.
 *
 * @apiDefine UnviewReIdMap
 * @apiParam {string} [companySearchKey] g公司不可见对象查询关键字
 * @apiParam {string} [userSearchKey] 人员不可见对象查询关键字
 * @apiParam {list} [userTypeList] 公司类型不可见对象list
 * @apiParam {list} [companyList] 公司不可见对象list
 * @apiParam {list} [roleList] 角色不可见对象list
 * @apiParam {list} [userList] 用户不可见对象list
 *
 */
public class UnviewReIdMap {
    private String companySearchKey;

    private String userSearchKey;

    private List<Integer> userTypeList;

    private List<Integer> companyList;

    private List<Integer> roleList;

    private List<Integer> userList;

    public List<Integer> getUserTypeList() {
        return userTypeList;
    }

    public void setUserTypeList(List<Integer> userTypeList) {
        this.userTypeList = userTypeList;
    }

    public List<Integer> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Integer> companyList) {
        this.companyList = companyList;
    }

    public List<Integer> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Integer> roleList) {
        this.roleList = roleList;
    }

    public List<Integer> getUserList() {
        return userList;
    }

    public void setUserList(List<Integer> userList) {
        this.userList = userList;
    }

    public String getCompanySearchKey() {
        return companySearchKey;
    }

    public void setCompanySearchKey(String companySearchKey) {
        this.companySearchKey = companySearchKey;
    }

    public String getUserSearchKey() {
        return userSearchKey;
    }

    public void setUserSearchKey(String userSearchKey) {
        this.userSearchKey = userSearchKey;
    }
}
