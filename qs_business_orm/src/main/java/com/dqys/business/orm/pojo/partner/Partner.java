package com.dqys.business.orm.pojo.partner;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by mkfeng on 2016/12/14.
 */
public class Partner implements Serializable {
    private Integer id;//int(11) NOT NULL AUTO_INCREMENT,
    private Integer userId;//int(11) DEFAULT NULL,
    private Integer companyId;//int(11) DEFAULT NULL,
    private Integer partnerUserId;//int(11) DEFAULT NULL,
    private Integer partnerCompanyId;//int(11) DEFAULT NULL,
    private Integer relationStatus;//tinyint(4) DEFAULT '0' COMMENT '合作状态（0待合作，1建立合作，2拒绝合作，3终止合作）',
    private Date createAt;//datetime DEFAULT NULL,
    private Date updateAt;//datetime DEFAULT NULL,
    private String remark;//varchar(255) DEFAULT NULL COMMENT '备注'

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getPartnerUserId() {
        return partnerUserId;
    }

    public void setPartnerUserId(Integer partnerUserId) {
        this.partnerUserId = partnerUserId;
    }

    public Integer getPartnerCompanyId() {
        return partnerCompanyId;
    }

    public void setPartnerCompanyId(Integer partnerCompanyId) {
        this.partnerCompanyId = partnerCompanyId;
    }

    public Integer getRelationStatus() {
        return relationStatus;
    }

    public void setRelationStatus(Integer relationStatus) {
        this.relationStatus = relationStatus;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
