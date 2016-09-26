package com.dqys.business.service.dto.asset;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by Yvan on 16/6/16.
 */
public class IouDTO {

    private Integer id;

    private String iouNo;  // 编号
    private String iouName;  // 借据名称
    private String type;  // 借据类型
    private String agency;  // 代理机构
    private String iouCode;  // 原始借据号
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date loanTime;  // 放款时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date loanAtTime;  // 到款时间
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endAt;  // 欠款截止日期
    private Double worth;  // 价值
    private Double advanceCorpus;  // 提前偿还本金
    private String evaluateExcellent;  // 评优
    private String evaluateLevel;  // 评级
    private String memo;  // 备注

    private Integer lenderId;  // 借款基础信息ID
    private ContactDTO lender;  //借款人信息

    private String pawnIds; // 抵押物IDs
    private String pawnNames; // 抵押物名称集合

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPawnIds() {
        return pawnIds;
    }

    public void setPawnIds(String pawnIds) {
        this.pawnIds = pawnIds;
    }

    public Integer getLenderId() {
        return lenderId;
    }

    public void setLenderId(Integer lenderId) {
        this.lenderId = lenderId;
    }

    public String getIouNo() {
        return iouNo;
    }

    public void setIouNo(String iouNo) {
        this.iouNo = iouNo;
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

    public Date getLoanAtTime() {
        return loanAtTime;
    }

    public void setLoanAtTime(Date loanAttime) {
        this.loanAtTime = loanAttime;
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

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
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

    public String getEvaluateExcellent() {
        return evaluateExcellent;
    }

    public void setEvaluateExcellent(String evaluateExcellent) {
        this.evaluateExcellent = evaluateExcellent;
    }

    public String getEvaluateLevel() {
        return evaluateLevel;
    }

    public void setEvaluateLevel(String evaluateLevel) {
        this.evaluateLevel = evaluateLevel;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public ContactDTO getLender() {
        return lender;
    }

    public void setLender(ContactDTO lender) {
        this.lender = lender;
    }

    public String getPawnNames() {
        return pawnNames;
    }

    public void setPawnNames(String pawnNames) {
        this.pawnNames = pawnNames;
    }

    public String getIouName() {
        return iouName;
    }

    public void setIouName(String iouName) {
        this.iouName = iouName;
    }
}
