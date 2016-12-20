package com.dqys.auth.orm.pojo;

import java.io.Serializable;

/**
 * Created by mkfeng on 2016/12/20.
 */
public class SaleUserModel implements Serializable {
    private SaleUser saleUser;
    private SaleUserTag saleUserTag;
    private String name;
    private String account;
    private String email;
    private String mobile;
    private String password;
    private Integer sex;
    private Integer province;
    private Integer city;
    private Integer area;
    private String smsCode;//手机短信验证码

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public SaleUser getSaleUser() {
        saleUser.setAccount(account);
        saleUser.setEmail(email);
        saleUser.setMobile(mobile);
        saleUser.setName(name);
        saleUser.setPassword(password);
        saleUser.setSex(sex);
        return saleUser;
    }

    public void setSaleUser(SaleUser saleUser) {
        this.saleUser = saleUser;
    }

    public SaleUserTag getSaleUserTag() {
        saleUserTag.setProvince(province);
        saleUserTag.setCity(city);
        saleUserTag.setArea(area);
        return saleUserTag;
    }

    public void setSaleUserTag(SaleUserTag saleUserTag) {
        this.saleUserTag = saleUserTag;
    }
}
