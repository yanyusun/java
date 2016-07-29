package com.dqys.business.service.dto.cases;

/**
 * @apiDefine CaseCourtDTO
 * @apiSuccessExample {json} CaseCourtDTO:
 * {}
 * git : http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/cases/CaseCourtDTO.java
 */
/**
 * Created by Yvan on 16/7/26.
 * @apiDefine CaseCourt
 * @apiParam {number} [id] 主键
 * @apiParam {string} caseId 案件ID
 * @apiParam {string} court 法院名称
 * @apiParam {string} code 法院案号
 * @apiParam {string} lawyer 法官
 * @apiParam {number} gender 性别(1男性)
 * @apiParam {string} lawyerMemo 备注
 * @apiParam {string} mobile 手机
 * @apiParam {string} tel 座机
 * @apiParam {string} other 其他联系方式
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
}
