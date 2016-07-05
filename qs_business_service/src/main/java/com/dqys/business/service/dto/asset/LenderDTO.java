package com.dqys.business.service.dto.asset;

import com.dqys.business.service.dto.common.UserDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Yvan on 16/6/16.
 * 借款人基础信息
 */
public class LenderDTO {

    private Integer id; // 主键Id

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startAt;  // 委托开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endAt;  //委托结束时间
    private Double accrual;  // 总利息
    private Double loan;  // 总贷款
    private Double appraisal;  // 总评估
    private String loanType;  // 贷款类型
    private String loanMode;  // 贷款方式
    private String loanName;  // 贷款名称
    private String evaluateExcellent;  // 评优
    private String evaluateLevel;  // 评级
    private String disposeMode;  //  处置方式
    private String tags;  // 标签
    private String urgeType;  // 催收阶段
    private String entrustBornType;  // 委托来源类型
    private String entrustBorn;  // 委托来源
    private String guaranteeType;  // 担保类型(个人|公司)
    private String guaranteeMode;  // 担保方式(抵押|质押)
    private String guaranteeSource;  // 担保源
    private Integer isGuaranteeConnection;  // 担保人是否能联系
    private String guarenteeEconomic;  // 担保人经济状况
    private Integer isLawsuit;  // 诉讼与否
    private Integer isDecision;  // 判决与否
    private Integer realUrgeTime;  // 实地催收次数
    private Integer phoneUrgeTime;  // 电话催收次数
    private Integer entrustUrgeTime;  // 委托催收次数
    private Integer canContact;  // 债务方是否能正常联系
    private Integer canPay;  // 债务方是否能偿还
    private Integer isWorth;  // 抵押物是否能覆盖债务
    private String memo;  // 备注

    private Integer lenderId;  // 借款人基础信息ID
    private ContactDTO lender;  // 借款人信息
    private Integer operatorId;  // 操作人Id
    private UserDTO operator;  // 操作人信息

    public Integer getLenderId() {
        return lenderId;
    }

    public void setLenderId(Integer lenderId) {
        this.lenderId = lenderId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Double getAccrual() {
        return accrual;
    }

    public void setAccrual(Double accrual) {
        this.accrual = accrual;
    }

    public Double getLoan() {
        return loan;
    }

    public void setLoan(Double loan) {
        this.loan = loan;
    }

    public Double getAppraisal() {
        return appraisal;
    }

    public void setAppraisal(Double appraisal) {
        this.appraisal = appraisal;
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

    public String getDisposeMode() {
        return disposeMode;
    }

    public void setDisposeMode(String disposeMode) {
        this.disposeMode = disposeMode;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getUrgeType() {
        return urgeType;
    }

    public void setUrgeType(String urgeType) {
        this.urgeType = urgeType;
    }

    public String getEntrustBornType() {
        return entrustBornType;
    }

    public void setEntrustBornType(String entrustBornType) {
        this.entrustBornType = entrustBornType;
    }

    public String getEntrustBorn() {
        return entrustBorn;
    }

    public void setEntrustBorn(String entrustBorn) {
        this.entrustBorn = entrustBorn;
    }

    public String getGuaranteeType() {
        return guaranteeType;
    }

    public void setGuaranteeType(String guaranteeType) {
        this.guaranteeType = guaranteeType;
    }

    public String getGuaranteeMode() {
        return guaranteeMode;
    }

    public void setGuaranteeMode(String guaranteeMode) {
        this.guaranteeMode = guaranteeMode;
    }

    public String getGuaranteeSource() {
        return guaranteeSource;
    }

    public void setGuaranteeSource(String guaranteeSource) {
        this.guaranteeSource = guaranteeSource;
    }

    public Integer getIsGuaranteeConnection() {
        return isGuaranteeConnection;
    }

    public void setIsGuaranteeConnection(Integer isGuaranteeConnection) {
        this.isGuaranteeConnection = isGuaranteeConnection;
    }

    public String getGuarenteeEconomic() {
        return guarenteeEconomic;
    }

    public void setGuarenteeEconomic(String guarenteeEconomic) {
        this.guarenteeEconomic = guarenteeEconomic;
    }

    public Integer getIsLawsuit() {
        return isLawsuit;
    }

    public void setIsLawsuit(Integer isLawsuit) {
        this.isLawsuit = isLawsuit;
    }

    public Integer getIsDecision() {
        return isDecision;
    }

    public void setIsDecision(Integer isDecision) {
        this.isDecision = isDecision;
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

    public Integer getCanContact() {
        return canContact;
    }

    public void setCanContact(Integer canContact) {
        this.canContact = canContact;
    }

    public Integer getCanPay() {
        return canPay;
    }

    public void setCanPay(Integer canPay) {
        this.canPay = canPay;
    }

    public Integer getIsWorth() {
        return isWorth;
    }

    public void setIsWorth(Integer isWorth) {
        this.isWorth = isWorth;
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

    public UserDTO getOperator() {
        return operator;
    }

    public void setOperator(UserDTO operator) {
        this.operator = operator;
    }
}