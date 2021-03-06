package com.dqys.business.service.dto.cases;

/**
 * Created by Yvan on 16/7/26.
 */
public class CaseCourtDTO {

    private Integer id; // 主键
    private Integer caseId; // 案件ID
    private String court; // 法院名称
    private String code; // 法院案号
    private String lawyer; // 法官
    private Integer gender; // 性别
    private String lawyerMemo; // 法官备注
    private String mobile; // 手机
    private String tel; // 座机
    private String other; // 其他联系方式
    private String otherLawyer; // 其他法官

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getLawyerMemo() {
        return lawyerMemo;
    }

    public void setLawyerMemo(String lawyerMemo) {
        this.lawyerMemo = lawyerMemo;
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

    public String getOtherLawyer() {
        return otherLawyer;
    }

    public void setOtherLawyer(String otherLawyer) {
        this.otherLawyer = otherLawyer;
    }
}
