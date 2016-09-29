package com.dqys.business.orm.pojo.cases;

import com.dqys.core.base.BaseModel;

import java.io.Serializable;

public class CaseCourt extends BaseModel {

    private Integer caseId; // 案件ID
    private String court; // 法院名称
    private String code; // 案号
    private String lawyer; // 法官名称
    private Integer gender; // 性别
    private String mobile; // 手机号
    private String tel; // 座机
    private String other; // 其他联系方式
    private String memo; // 备注
    private String otherLawyer; // 其他法官

    public String toCheckObject(){
        String s = "{";
        s += "#id:" + caseId + "#法院:" + court + "#案号:" + code + "#法官:" + lawyer + "#性别:" + gender
                + "#手机:" + mobile + "#座机:" + tel + "#其他:" + other + "#备注:" + memo
                + "#其他法官:" + otherLawyer;
        s += "}";
        return s;
    }


    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLawyer() {
        return lawyer;
    }

    public void setLawyer(String lawyer) {
        this.lawyer = lawyer;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getOtherLawyer() {
        return otherLawyer;
    }

    public void setOtherLawyer(String otherLawyer) {
        this.otherLawyer = otherLawyer;
    }
}