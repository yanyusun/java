package com.dqys.business.service.dto.sourceAuth;

import java.util.List;

/**
 * 资料实勘不可见权限关系
 * Created by yan on 16-10-28.
 */
public class SelectDtoMap {

    private List<SelectDto> userTypeList;

    private List<SelectDto> companyList;

    private List<SelectDto> roleList;

    private List<SelectDto> userList;


    public List<SelectDto> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<SelectDto> companyList) {
        this.companyList = companyList;
    }

    public List<SelectDto> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SelectDto> roleList) {
        this.roleList = roleList;
    }

    public List<SelectDto> getUserList() {
        return userList;
    }

    public void setUserList(List<SelectDto> userList) {
        this.userList = userList;
    }

    public List<SelectDto> getUserTypeList() {
        return userTypeList;
    }

    public void setUserTypeList(List<SelectDto> userTypeList) {
        this.userTypeList = userTypeList;
    }
}
