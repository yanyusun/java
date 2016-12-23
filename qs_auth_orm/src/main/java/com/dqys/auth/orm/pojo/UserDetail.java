package com.dqys.auth.orm.pojo;

import java.io.Serializable;

/**
 * Created by mkfeng on 2016/11/18.
 */
public class UserDetail implements Serializable {
    private Integer id;//用户id
    private String realName;//用户的真实姓名
    private String mobile;//用户的手机号
    private Integer rold;//用户的角色
    private Integer userType;//用户的帐号类型
    private String companyName;//用户所在的公司名称
    private Integer companyType;//用户所在公司的公司类型
    private String email;//用户邮箱
    private Integer companyId;//用户所在公司id
    private String account;//用户帐号
    private String accountCode;//清搜号

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getRold() {
        return rold;
    }

    public void setRold(Integer rold) {
        this.rold = rold;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
