package com.dqys.business.orm.dto;

import com.dqys.core.base.BaseDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by pan on 16-5-18.
 *
 * 资产包信息
 */
public class AssetInfo extends BaseDTO {

    private String assertNo;
    private Integer type;       //资产类型
    private Date start;
    private Date end;
    private Integer inputUid;
    private BigDecimal interest;
    private BigDecimal loanTotal;
    private List<AssetEvaluation> assetEvaluations;
    private Integer evaluateExcellent;       //评优
    private Integer evaluateLevel;      //评级

    private LoanInfo loanInfo;      //贷款信息

    private List<AbstractDealWay> dealWays;     //处置方式
    private List<String> tags;          //标签
    private List<String> steps;         //阶段

    private WarrantyInfo warrantyInfo;
    private Boolean hasAppealed;        //是否诉讼
    private Boolean hasAdjudged;        //是否判决
    private Integer fieldCount;
    private Integer callCount;
    private Integer deputeCount;

    private Boolean canContact;     //是否能联系
    private Boolean hasAbility;     //是否有能力
    private Boolean canRepay;       //抵押物是否能够偿还


    public String getAssertNo() {
        return assertNo;
    }

    public void setAssertNo(String assertNo) {
        this.assertNo = assertNo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Integer getInputUid() {
        return inputUid;
    }

    public void setInputUid(Integer inputUid) {
        this.inputUid = inputUid;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getLoanTotal() {
        return loanTotal;
    }

    public void setLoanTotal(BigDecimal loanTotal) {
        this.loanTotal = loanTotal;
    }

    public List<AssetEvaluation> getAssetEvaluations() {
        return assetEvaluations;
    }

    public void setAssetEvaluations(List<AssetEvaluation> assetEvaluations) {
        this.assetEvaluations = assetEvaluations;
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

    public LoanInfo getLoanInfo() {
        return loanInfo;
    }

    public void setLoanInfo(LoanInfo loanInfo) {
        this.loanInfo = loanInfo;
    }

    public List<AbstractDealWay> getDealWays() {
        return dealWays;
    }

    public void setDealWays(List<AbstractDealWay> dealWays) {
        this.dealWays = dealWays;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public WarrantyInfo getWarrantyInfo() {
        return warrantyInfo;
    }

    public void setWarrantyInfo(WarrantyInfo warrantyInfo) {
        this.warrantyInfo = warrantyInfo;
    }

    public Boolean getHasAppealed() {
        return hasAppealed;
    }

    public void setHasAppealed(Boolean hasAppealed) {
        this.hasAppealed = hasAppealed;
    }

    public Boolean getHasAdjudged() {
        return hasAdjudged;
    }

    public void setHasAdjudged(Boolean hasAdjudged) {
        this.hasAdjudged = hasAdjudged;
    }

    public Integer getFieldCount() {
        return fieldCount;
    }

    public void setFieldCount(Integer fieldCount) {
        this.fieldCount = fieldCount;
    }

    public Integer getCallCount() {
        return callCount;
    }

    public void setCallCount(Integer callCount) {
        this.callCount = callCount;
    }

    public Integer getDeputeCount() {
        return deputeCount;
    }

    public void setDeputeCount(Integer deputeCount) {
        this.deputeCount = deputeCount;
    }

    public Boolean getCanContact() {
        return canContact;
    }

    public void setCanContact(Boolean canContact) {
        this.canContact = canContact;
    }

    public Boolean getHasAbility() {
        return hasAbility;
    }

    public void setHasAbility(Boolean hasAbility) {
        this.hasAbility = hasAbility;
    }

    public Boolean getCanRepay() {
        return canRepay;
    }

    public void setCanRepay(Boolean canRepay) {
        this.canRepay = canRepay;
    }
}
