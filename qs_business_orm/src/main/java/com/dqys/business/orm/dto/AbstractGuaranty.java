package com.dqys.business.orm.dto;

import com.dqys.core.base.BaseDTO;

import java.math.BigDecimal;

/**
 * 抵押物
 *
 * Created by pan on 16-5-17.
 */
public abstract class AbstractGuaranty extends BaseDTO {

    private String guarantyNo;      //编号
    private BigDecimal loanAmount;      //贷款金额 单位:元
    private Integer evaluateExcellent;       //评优
    private Integer evaluateLevel;      //评级
    private Integer type;       //抵押物类型
    private BigDecimal guarantyRate;    //抵押率
    private Integer dealStatus;     //处置状态
    private BigDecimal evaluateAmount;      //抵押物估值 单位:元

    public String getGuarantyNo() {
        return guarantyNo;
    }

    public void setGuarantyNo(String guarantyNo) {
        this.guarantyNo = guarantyNo;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Integer getEvaluateExcellent() {
        return evaluateExcellent;
    }

    public void setEvaluateExcellent(Integer evaluateExcellent) {
        this.evaluateExcellent = evaluateExcellent;
    }

    public Integer getEvaluateLevel() {
        return evaluateLevel;
    }

    public void setEvaluateLevel(Integer evaluateLevel) {
        this.evaluateLevel = evaluateLevel;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getGuarantyRate() {
        return guarantyRate;
    }

    public void setGuarantyRate(BigDecimal guarantyRate) {
        this.guarantyRate = guarantyRate;
    }

    public Integer getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(Integer dealStatus) {
        this.dealStatus = dealStatus;
    }

    public BigDecimal getEvaluateAmount() {
        return evaluateAmount;
    }

    public void setEvaluateAmount(BigDecimal evaluateAmount) {
        this.evaluateAmount = evaluateAmount;
    }
}
