package com.dqys.auth.orm.pojo.asset;

import com.dqys.core.base.BaseModel;

import java.io.Serializable;
import java.util.Date;

public class IOUInfo extends BaseModel implements Serializable{

    private String code;  // 编号

    private Integer lenderId;  // 借款人ID

//    private Integer lenderName; // 借款人姓名

    private String pawnids;  // 抵押物IDS

    private String type;  // 借据类型

    private String agency;  // 代理机构

    private String iouCode;  // 原始借据号

    private Date loanTime;  // 放款时间

    private Date loanAttime;  // 到款时间

    private Double amount;  // 金额

    private Double pactRate;  // 合同利率

    private Double outtimeMultiple;  // 逾期倍数

    private String appropriationMultiple;  // 挪用倍数

    private Double accrualRepay;  // 已还利息金额

    private Double loanRepay;  // 已还贷款金额

    private String levelType;  // 5级分类

    private Integer outDays;  // 逾期天数

    private Double lessCorpus;  // 剩余本金

    private Double accrualArrears;  // 拖欠利息

    private Double penalty;  // 罚息

    private Double arrears;  // 欠款合计

    private Date outTime;  // 欠款截止日期

    private Double worth;  // 价值

    private Double advanceCorpus;  // 提前偿还本金

    private String quality;  // 评优

    private String level;  // 评级

    private String memo;  // 备注

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getLenderId() {
        return lenderId;
    }

    public void setLenderId(Integer lenderId) {
        this.lenderId = lenderId;
    }

    public String getPawnids() {
        return pawnids;
    }

    public void setPawnids(String pawnids) {
        this.pawnids = pawnids;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getIouCode() {
        return iouCode;
    }

    public void setIouCode(String iouCode) {
        this.iouCode = iouCode;
    }

    public Date getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(Date loanTime) {
        this.loanTime = loanTime;
    }

    public Date getLoanAttime() {
        return loanAttime;
    }

    public void setLoanAttime(Date loanAttime) {
        this.loanAttime = loanAttime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getPactRate() {
        return pactRate;
    }

    public void setPactRate(Double pactRate) {
        this.pactRate = pactRate;
    }

    public Double getOuttimeMultiple() {
        return outtimeMultiple;
    }

    public void setOuttimeMultiple(Double outtimeMultiple) {
        this.outtimeMultiple = outtimeMultiple;
    }

    public String getAppropriationMultiple() {
        return appropriationMultiple;
    }

    public void setAppropriationMultiple(String appropriationMultiple) {
        this.appropriationMultiple = appropriationMultiple;
    }

    public Double getAccrualRepay() {
        return accrualRepay;
    }

    public void setAccrualRepay(Double accrualRepay) {
        this.accrualRepay = accrualRepay;
    }

    public Double getLoanRepay() {
        return loanRepay;
    }

    public void setLoanRepay(Double loanRepay) {
        this.loanRepay = loanRepay;
    }

    public String getLevelType() {
        return levelType;
    }

    public void setLevelType(String levelType) {
        this.levelType = levelType;
    }

    public Integer getOutDays() {
        return outDays;
    }

    public void setOutDays(Integer outDays) {
        this.outDays = outDays;
    }

    public Double getLessCorpus() {
        return lessCorpus;
    }

    public void setLessCorpus(Double lessCorpus) {
        this.lessCorpus = lessCorpus;
    }

    public Double getAccrualArrears() {
        return accrualArrears;
    }

    public void setAccrualArrears(Double accrualArrears) {
        this.accrualArrears = accrualArrears;
    }

    public Double getPenalty() {
        return penalty;
    }

    public void setPenalty(Double penalty) {
        this.penalty = penalty;
    }

    public Double getArrears() {
        return arrears;
    }

    public void setArrears(Double arrears) {
        this.arrears = arrears;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public Double getWorth() {
        return worth;
    }

    public void setWorth(Double worth) {
        this.worth = worth;
    }

    public Double getAdvanceCorpus() {
        return advanceCorpus;
    }

    public void setAdvanceCorpus(Double advanceCorpus) {
        this.advanceCorpus = advanceCorpus;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
//
//    public Integer getLenderName() {
//        return lenderName;
//    }
//
//    public void setLenderName(Integer lenderName) {
//        this.lenderName = lenderName;
//    }
}