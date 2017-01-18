package com.dqys.auth.orm.pojo;

import java.io.Serializable;

/**
 * Created by mkfeng on 2016/11/18.
 */
public class UserDetail implements Serializable {
    private Integer id;//用户id
    private String avg;//头像
    private String realName;//用户的真实姓名
    private String userName;//用户名（昵称）
    private String mobile;//用户的手机号
    private Integer rold;//用户的角色
    private Integer userType;//用户的帐号类型
    private String companyName;//用户所在的公司名称
    private Integer companyType;//用户所在公司的公司类型
    private String email;//用户邮箱
    private String wechat;//用户微信帐号
    private String QQ;//用户微信帐号
    private Integer companyId;//用户所在公司id
    private String account;//用户帐号
    private String accountCode;//清搜号(公司的)
    private Integer sex;//性别（1男0女）
    private Integer province;//公司所在省份
    private Integer city;//公司所在城市
    private Integer area;//公司所在区县
    private String address;//公司所在具体地址
    private String licence;//营业执照扫描件

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

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
