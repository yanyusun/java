package com.dqys.business.service.dto.asset;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by mfkeng on 2016/9/9.
 */
public class LenderSynDTO {

    private String avg;  // 头像地址
    private String name;// 姓名
    private String gender;//性别
    private Integer type;  // 借款类型
    private String address;  // 详细地址
    private String lenderNo;//编号
    private String tags;  // 标签
    private String accrual;  // 总利息
    private String loan;  // 总贷款
    private String appraisal;  // 总评估
    private String out_days;//逾期天数（计算？）
    private String loanType;  // 贷款类型
    private String loanMode;  // 贷款方式
    private String loanName;  // 贷款名称
    private Integer realUrgeTime;  // 实地催收次数
    private Integer phoneUrgeTime;  // 电话催收次数
    private Integer entrustUrgeTime;  // 委托催收次数
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date followUpDate; // 最后跟进时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date enteringDate; // 录入时间
    private Integer belongId; // 所属人ID
    private String belongName; // 所属人姓名
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startAt;  // 委托开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endAt;  //委托结束时间
    private String surplusDay;//剩余委托催收天数
    private String source;//来源
    private String assetNo;//资产包编号

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLenderNo() {
        return lenderNo;
    }

    public void setLenderNo(String lenderNo) {
        this.lenderNo = lenderNo;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccrual() {
        return accrual;
    }

    public void setAccrual(String accrual) {
        this.accrual = accrual;
    }

    public String getLoan() {
        return loan;
    }

    public void setLoan(String loan) {
        this.loan = loan;
    }

    public String getAppraisal() {
        return appraisal;
    }

    public void setAppraisal(String appraisal) {
        this.appraisal = appraisal;
    }

    public String getOut_days() {
        return out_days;
    }

    public void setOut_days(String out_days) {
        this.out_days = out_days;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getLoanMode() {
        return loanMode;
    }

    public void setLoanMode(String loanMode) {
        this.loanMode = loanMode;
    }

    public String getLoanName() {
        return loanName;
    }

    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }

    public Integer getRealUrgeTime() {
        return realUrgeTime;
    }

    public void setRealUrgeTime(Integer realUrgeTime) {
        this.realUrgeTime = realUrgeTime;
    }

    public Integer getPhoneUrgeTime() {
        return phoneUrgeTime;
    }

    public void setPhoneUrgeTime(Integer phoneUrgeTime) {
        this.phoneUrgeTime = phoneUrgeTime;
    }

    public Integer getEntrustUrgeTime() {
        return entrustUrgeTime;
    }

    public void setEntrustUrgeTime(Integer entrustUrgeTime) {
        this.entrustUrgeTime = entrustUrgeTime;
    }

    public Date getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(Date followUpDate) {
        this.followUpDate = followUpDate;
    }

    public Date getEnteringDate() {
        return enteringDate;
    }

    public void setEnteringDate(Date enteringDate) {
        this.enteringDate = enteringDate;
    }

    public Integer getBelongId() {
        return belongId;
    }

    public void setBelongId(Integer belongId) {
        this.belongId = belongId;
    }

    public String getBelongName() {
        return belongName;
    }

    public void setBelongName(String belongName) {
        this.belongName = belongName;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public String getSurplusDay() {
        return surplusDay;
    }

    public void setSurplusDay(String surplusDay) {
        this.surplusDay = surplusDay;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAssetNo() {
        return assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
    }
}
