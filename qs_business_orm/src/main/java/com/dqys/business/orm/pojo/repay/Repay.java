package com.dqys.business.orm.pojo.repay;

import java.io.Serializable;
import java.util.Date;

public class Repay implements Serializable {
    private Integer id;//int(11) NOT NULL AUTO_INCREMENT,
    private Date damageDate;//date DEFAULT NULL 还款时间,
    private Integer repayType;//int(11) DEFAULT NULL 还款类型(0还利息1还本金2还利息加本金),
    private Integer operUserId;//int(11) NOT NULL,
    private Double repayM;//double(10,2) NOT NULL 还款总金额,
    private Integer repayFid;//int(11) DEFAULT NULL COMMENT '还款主体',
    private String repayFidName;// varchar(100) DEFAULT NULL COMMENT '还款主体名称'
    private Integer repayWay;//int(11) DEFAULT NULL COMMENT '还款方式',
    private String repayBills;//longblob COMMENT '还款单据图片',
    private String remark;//varchar(256) DEFAULT NULL COMMENT '备注',
    private Integer version;//int(11) NOT NULL DEFAULT '0' COMMENT '版本',
    private Date createAt;//datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    private Date updateAt;//datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    private Long stateflag;//bigint(22) NOT NULL DEFAULT '0' COMMENT '数据状态：0-可用，非0不可用',
    private Integer repayFidType;//int(5) DEFAULT NULL COMMENT '还款主体类型（借据或抵押物）',
    private Integer status;//'状态（0正常，2冲正）',
    private Integer lenderId;//借款人id

    public Integer getLenderId() {
        return lenderId;
    }

    public void setLenderId(Integer lenderId) {
        this.lenderId = lenderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDamageDate() {
        return damageDate;
    }

    public void setDamageDate(Date damageDate) {
        this.damageDate = damageDate;
    }

    public Integer getRepayType() {
        return repayType;
    }

    public void setRepayType(Integer repayType) {
        this.repayType = repayType;
    }

    public Integer getOperUserId() {
        return operUserId;
    }

    public void setOperUserId(Integer operUserId) {
        this.operUserId = operUserId;
    }

    public Double getRepayM() {
        return repayM;
    }

    public void setRepayM(Double repayM) {
        this.repayM = repayM;
    }

    public Integer getRepayFid() {
        return repayFid;
    }

    public void setRepayFid(Integer repayFid) {
        this.repayFid = repayFid;
    }

    public String getRepayFidName() {
        return repayFidName;
    }

    public void setRepayFidName(String repayFidName) {
        this.repayFidName = repayFidName;
    }

    public Integer getRepayWay() {
        return repayWay;
    }

    public void setRepayWay(Integer repayWay) {
        this.repayWay = repayWay;
    }

    public String getRepayBills() {
        return repayBills;
    }

    public void setRepayBills(String repayBills) {
        this.repayBills = repayBills;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Integer getRepayFidType() {
        return repayFidType;
    }

    public void setRepayFidType(Integer repayFidType) {
        this.repayFidType = repayFidType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}