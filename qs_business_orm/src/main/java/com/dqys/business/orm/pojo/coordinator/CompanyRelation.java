package com.dqys.business.orm.pojo.coordinator;

import java.util.Date;

public class CompanyRelation {
    private Integer id;

    private Integer companyAId;

    private Integer companyBId;

    private Integer version;

    private Date createAt;

    private Date updateAt;

    private Long stateflag;

    private Integer relationStatus;//合作状态

    private Date teamworkTime;//最后合作时间

    private Integer operUser;//操作用户

    private String aRemark;//a备注
    private String bRemark;//b备注

    public String getaRemark() {
        return aRemark;
    }

    public void setaRemark(String aRemark) {
        this.aRemark = aRemark;
    }

    public String getbRemark() {
        return bRemark;
    }

    public void setbRemark(String bRemark) {
        this.bRemark = bRemark;
    }

    public Integer getOperUser() {
        return operUser;
    }

    public void setOperUser(Integer operUser) {
        this.operUser = operUser;
    }

    public Date getTeamworkTime() {
        return teamworkTime;
    }

    public void setTeamworkTime(Date teamworkTime) {
        this.teamworkTime = teamworkTime;
    }

    public Integer getRelationStatus() {
        return relationStatus;
    }

    public void setRelationStatus(Integer relationStatus) {
        this.relationStatus = relationStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyAId() {
        return companyAId;
    }

    public void setCompanyAId(Integer companyAId) {
        this.companyAId = companyAId;
    }

    public Integer getCompanyBId() {
        return companyBId;
    }

    public void setCompanyBId(Integer companyBId) {
        this.companyBId = companyBId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Long getStateflag() {
        return stateflag;
    }

    public void setStateflag(Long stateflag) {
        this.stateflag = stateflag;
    }
}