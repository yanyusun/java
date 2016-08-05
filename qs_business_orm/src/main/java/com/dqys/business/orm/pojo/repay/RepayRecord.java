package com.dqys.business.orm.pojo.repay;

import java.util.Date;

/**
 * Created by mkfeng on 2016/7/25.
 */
public class RepayRecord {
    private Integer id;//int(11) NOT NULL AUTO_INCREMENT,
    private Integer iouId;//int(11) NOT NULL COMMENT '借据id',
    private Integer repayId;//int(11) DEFAULT NULL COMMENT '还款id',
    private Integer version;//int(11) NOT NULL DEFAULT '0' COMMENT '版本',
    private Date createAt;//datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    private Date updateAt;//datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    private Integer stateflag;//bigint(22) NOT NULL DEFAULT '0' COMMENT '数据状态：0-可用，非0不可用',
    private Double repayPrincipal;//double(10,2) DEFAULT NULL COMMENT '还款本金',
    private Double repayInterest;//double(10,2) DEFAULT NULL COMMENT '还款利息',
    private Double repayFine;//double(10,2) DEFAULT NULL COMMENT '还款罚金',
    private Integer status;//tinyint(4) DEFAULT NULL COMMENT '状态（0正常，2冲正）',

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIouId() {
        return iouId;
    }

    public void setIouId(Integer iouId) {
        this.iouId = iouId;
    }

    public Integer getRepayId() {
        return repayId;
    }

    public void setRepayId(Integer repayId) {
        this.repayId = repayId;
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

    public Integer getStateflag() {
        return stateflag;
    }

    public void setStateflag(Integer stateflag) {
        this.stateflag = stateflag;
    }

    public Double getRepayPrincipal() {
        return repayPrincipal;
    }

    public void setRepayPrincipal(Double repayPrincipal) {
        this.repayPrincipal = repayPrincipal;
    }

    public Double getRepayInterest() {
        return repayInterest;
    }

    public void setRepayInterest(Double repayInterest) {
        this.repayInterest = repayInterest;
    }

    public Double getRepayFine() {
        return repayFine;
    }

    public void setRepayFine(Double repayFine) {
        this.repayFine = repayFine;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
