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