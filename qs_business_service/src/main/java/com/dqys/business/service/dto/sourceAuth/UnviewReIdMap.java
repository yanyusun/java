package com.dqys.business.service.dto.sourceAuth;

import java.util.List;

/**
 * 不可见关联对象map
 * Created by yan on 16-11-3.
 *
 * @apiDefine UnviewReIdMap
 * @apiParam {list} [userTypeList] 资料实勘分类id
 * @apiParam {list} [companyList] 对象id
 * @apiParam {list} [roleList] 对象类型(借款人,资产源)
 * @apiParam {list} [userList] 对象类型(借款人,资产源)
 *
 */
public class UnviewReIdMap {
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
}
