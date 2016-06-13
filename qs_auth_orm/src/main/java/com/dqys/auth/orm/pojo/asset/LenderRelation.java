package com.dqys.auth.orm.pojo.asset;

import com.dqys.core.base.BaseModel;

import java.io.Serializable;
import java.util.Date;

public class LenderRelation extends BaseModel implements Serializable{

    private Integer lenderId;  // 借款人ID

    private String slenderIds;  // 共同借款人IDS

    private String guaranteeIds;  // 担保人IDS

    private String bankManagerIds;  // 银行客户经理IDS

    private String otherIds;  // 其他IDS

    private Date entrustStartTime;  // 委托开始时间

    private Date entrustEndTime;  //委托结束时间

    private Integer operator;  // 操作人ID

    private Double accrual;  // 总利息

    private Double loan;  // 总贷款

    private Double appraisal;  // 总评估

    private String loanType;  // 贷款类型

    private String loanMode;  // 贷款方式

    private String loanName;  // 贷款名称

    private String quality;  // 评优

    private String level;  // 评级

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

    private Integer isConnection;  // 债务方是否能正常联系

    private Integer canPay;  // 债务方是否能偿还

    private Integer isWorth;  // 抵押物是否能覆盖债务

    private String memo;  // 备注

    @Override
    public String toString(){
        String string = "LenderRelation:[";
        string += "lenderId:" + lenderId + ",slenderIds:" + slenderIds + ",guaranteeIds:" + guaranteeIds
                + ",bankManagerIds:" + bankManagerIds + ",otherIds:" + otherIds + ",entrustStartTime:" + entrustStartTime
                + ",entrustEndTime:" + entrustEndTime + ",operator:" + operator + ",accrual:" + accrual
                + ",loan:" + loan + ",appraisal:" + appraisal + ",loanType:" + loanType
                + ",loanMode:" + loanMode + ",loanName:" + loanName + ",quality:" + quality
                + ",level:" + level + ",disposeMode:" + disposeMode + ",tags:" + tags
                + ",urgeType:" + urgeType + ",entrustBornType:" + entrustBornType + ",entrustBorn:" + entrustBorn
                + ",guaranteeType:" + guaranteeType + ",guaranteeMode:" + guaranteeMode + ",guaranteeSource:" + guaranteeSource
                + ",isGuaranteeConnection:" + isGuaranteeConnection + ",guarenteeEconomic:" + guarenteeEconomic + ",isLawsuit:" + isLawsuit
                + ",isDecision:" + isDecision + ",realUrgeTime:" + realUrgeTime + ",phoneUrgeTime:" + phoneUrgeTime
                + ",entrustUrgeTime:" + entrustUrgeTime + ",isConnection:" + isConnection + ",canPay:" + canPay
                + ",isWorth:" + isWorth + ",memo:" + memo;
        string += "]";
        return string;
    }

    public Integer getLenderId() {
        return lenderId;
    }

    public void setLenderId(Integer lenderId) {
        this.lenderId = lenderId;
    }

    public String getSlenderIds() {
        return slenderIds;
    }

    public void setSlenderIds(String slenderIds) {
        this.slenderIds = slenderIds;
    }

    public String getGuaranteeIds() {
        return guaranteeIds;
    }

    public void setGuaranteeIds(String guaranteeIds) {
        this.guaranteeIds = guaranteeIds;
    }

    public String getBankManagerIds() {
        return bankManagerIds;
    }

    public void setBankManagerIds(String bankManagerIds) {
        this.bankManagerIds = bankManagerIds;
    }

    public String getOtherIds() {
        return otherIds;
    }

    public void setOtherIds(String otherIds) {
        this.otherIds = otherIds;
    }

    public Date getEntrustStartTime() {
        return entrustStartTime;
    }

    public void setEntrustStartTime(Date entrustStartTime) {
        this.entrustStartTime = entrustStartTime;
    }

    public Date getEntrustEndTime() {
        return entrustEndTime;
    }

    public void setEntrustEndTime(Date entrustEndTime) {
        this.entrustEndTime = entrustEndTime;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
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

    public Integer getIsConnection() {
        return isConnection;
    }

    public void setIsConnection(Integer isConnection) {
        this.isConnection = isConnection;
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
}