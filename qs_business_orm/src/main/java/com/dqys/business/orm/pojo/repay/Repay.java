package com.dqys.business.orm.pojo.repay;

import java.io.Serializable;
import java.util.Date;

public class Repay implements Serializable{
    private Integer id;

    private Date damageDate;

    private Integer repayType;

    private Integer operUserId;

    private Integer iouId;

    private Double repayM;

    private Integer repayFid;

    private Integer repayWay;

    private String remark;

    private Integer version;

    private Date createAt;

    private Date updateAt;

    private Long stateflag;

    private byte[] repayBills;

    public Repay() {
    }

    public Repay(Integer id, Date damageDate, Integer repayType, Integer operUserId, Integer iouId, Double repayM, Integer repayFid, Integer repayWay, String remark, Integer version, Date createAt, Date updateAt, Long stateflag, byte[] repayBills) {

        this.id = id;
        this.damageDate = damageDate;
        this.repayType = repayType;
        this.operUserId = operUserId;
        this.iouId = iouId;
        this.repayM = repayM;
        this.repayFid = repayFid;
        this.repayWay = repayWay;
        this.remark = remark;
        this.version = version;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.stateflag = stateflag;
        this.repayBills = repayBills;
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

    public Integer getIouId() {
        return iouId;
    }

    public void setIouId(Integer iouId) {
        this.iouId = iouId;
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

    public Integer getRepayWay() {
        return repayWay;
    }

    public void setRepayWay(Integer repayWay) {
        this.repayWay = repayWay;
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

    public byte[] getRepayBills() {
        return repayBills;
    }

    public void setRepayBills(byte[] repayBills) {
        this.repayBills = repayBills;
    }
}