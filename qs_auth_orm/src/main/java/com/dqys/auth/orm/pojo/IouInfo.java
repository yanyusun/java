package com.dqys.auth.orm.pojo;

import java.util.Date;

public class IouInfo {
    private Integer id;

    private Integer version;

    private Date createAt;

    private Date updateAt;

    private Byte stateflag;

    private String remark;

    private String code;

    private Integer lenderId;

    private Integer type;

    private String agency;

    private Date loanTime;

    private Date loanAttime;

    private Double amount;

    private String pactRate;

    private String outtimeMultiple;

    private String appropriationMultiple;

    private Double accrualRepay;

    private Double loanRepay;

    private String level;

    private Integer outDays;

    private Double lessCorpus;

    private Double accrualArrears;

    private Double penalty;

    private Double arrears;

    private Date outTime;

    private Double worth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Byte getStateflag() {
        return stateflag;
    }

    public void setStateflag(Byte stateflag) {
        this.stateflag = stateflag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
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

    public String getPactRate() {
        return pactRate;
    }

    public void setPactRate(String pactRate) {
        this.pactRate = pactRate;
    }

    public String getOuttimeMultiple() {
        return outtimeMultiple;
    }

    public void setOuttimeMultiple(String outtimeMultiple) {
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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
}