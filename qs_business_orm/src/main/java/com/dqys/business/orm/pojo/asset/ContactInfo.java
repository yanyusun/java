package com.dqys.business.orm.pojo.asset;

import com.dqys.core.base.BaseModel;

import java.io.Serializable;

/**
 * 联系人
 */
public class ContactInfo extends BaseModel implements Serializable{

    private String mode;  // 模块名称
    private Integer modeId;  // 模块ID
    private String name;  // 姓名
    private Integer type;  // 借款类型
    private String avg;  // 头像地址
    private Integer gender;  // 性别
    private String idcard;  // 身份证
    private String company;  // 公司
    private String mobile;  // 手机号
    private String homeTel;  // 家庭电话
    private String officeTel;  // 办公电话
    private String email;  // 电子邮件
    private String province;  // 省
    private String city;  // 市
    private String district;  // 区
    private String address;  // 详细地址
    private String code; // 工号
    private String otherAddress; // 其他地址

    public String toString(){
        String string = "lenderInfo:[";
        string += "id:" + super.getId() + ",name:" + name + ",type:" + type
                + ",avg:" + avg + ",idcard:" + idcard + ",gender:" + gender
                + ",company:" + company + ",mobile:" + mobile + ",homeTel:" + homeTel
                + ",officeTel:" + officeTel + ",email:" + email + ",province:" + province
                + ",city:" + city + ",district:" + district + ",address:" + address
                + ",code:" + code + ",mode:" + mode + ",modeId:" + modeId;
        string += "]";
        return string;
    }


    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Integer getModeId() {
        return modeId;
    }

    public void setModeId(Integer modeId) {
        this.modeId = modeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHomeTel() {
        return homeTel;
    }

    public void setHomeTel(String homeTel) {
        this.homeTel = homeTel;
    }

    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOtherAddress() {
        return otherAddress;
    }

    public void setOtherAddress(String otherAddress) {
        this.otherAddress = otherAddress;
    }
}