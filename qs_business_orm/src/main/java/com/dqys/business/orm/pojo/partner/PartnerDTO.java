package com.dqys.business.orm.pojo.partner;

import java.io.Serializable;

/**
 * Created by mkfeng on 2016/12/15.
 */
public class PartnerDTO implements Serializable {
    private Integer id;//关系表id
    private Integer relationStatus;//关系状态
    private Integer companyId;//公司id
    private Integer businessType;//公司类型
    private String companyName;//公司名称
    private String credential;//执照号
    private String address;//地址
    private String realName;//联系人
    private String mobile;//联系人手机号
    private String email;//联系人邮箱
    private String teamworkTime;//最后合作时间
    private String account;//帐号
    private Integer operUser;//操作用户id
    private Integer aReamrk;//a备注
    private Integer bReamrk;//b备注

    public Integer getaReamrk() {
        return aReamrk;
    }

    public void setaReamrk(Integer aReamrk) {
        this.aReamrk = aReamrk;
    }

    public Integer getbReamrk() {
        return bReamrk;
    }

    public void setbReamrk(Integer bReamrk) {
        this.bReamrk = bReamrk;
    }

    public Integer getOperUser() {
        return operUser;
    }

    public void setOperUser(Integer operUser) {
        this.operUser = operUser;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTeamworkTime() {
        return teamworkTime;
    }

    public void setTeamworkTime(String teamworkTime) {
        this.teamworkTime = teamworkTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRelationStatus() {
        return relationStatus;
    }

    public void setRelationStatus(Integer relationStatus) {
        this.relationStatus = relationStatus;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
