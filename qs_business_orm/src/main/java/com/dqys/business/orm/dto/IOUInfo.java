package com.dqys.business.orm.dto;

import com.dqys.core.base.BaseDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by pan on 16-5-18.
 *
 * 借据
 */
public class IOUInfo extends BaseDTO {

    private String iouNo;       //借据编号
    private String variety;     //品种
    private String handler;     //经办机构
    private Date loanDate;       //放款日
    private Date expiryDate;     //到期日
    private BigDecimal loanAmount;      //放款金额
    private BigDecimal insterestRate;       //合同利率
    private BigDecimal overInsterestRateMultiple;        //逾期利率倍数
    private BigDecimal embezzleInsterestRateMultiple;        //逾期利率倍数
    private BigDecimal repayedInsterestAmount;      //已还利息金额
    private BigDecimal repayedLoanAmount;      //已还贷款金额
    private String level;       //五级分类
    private Integer overDays;   //逾期天数
    private BigDecimal loanRemain;  //剩余本金
    private BigDecimal defaultInterest;       //拖欠利息
    private List<InterestFavorable> interestFavorables;     //利息优惠列表
    private BigDecimal pubnishInterest;        //罚息
    private BigDecimal debtAmount;      //欠款合计
    private Date debtEndDate;       //欠款合计截止日期
    private List<AssetEvaluation> assetEvaluations;     //抵押物估值
    private BigDecimal loanPreRepayAmount;      //提前偿还本金
    private Integer inputUid;       //录入人UID
    private Integer evaluateExcellent;       //评优
    private Integer evaluateLevel;      //评级

    public String getIouNo() {
        return iouNo;
    }

    public void setIouNo(String iouNo) {
        this.iouNo = iouNo;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public BigDecimal getInsterestRate() {
        return insterestRate;
    }

    public void setInsterestRate(BigDecimal insterestRate) {
        this.insterestRate = insterestRate;
    }

    public BigDecimal getOverInsterestRateMultiple() {
        return overInsterestRateMultiple;
    }

    public void setOverInsterestRateMultiple(BigDecimal overInsterestRateMultiple) {
        this.overInsterestRateMultiple = overInsterestRateMultiple;
    }

    public BigDecimal getEmbezzleInsterestRateMultiple() {
        return embezzleInsterestRateMultiple;
    }

    public void setEmbezzleInsterestRateMultiple(BigDecimal embezzleInsterestRateMultiple) {
        this.embezzleInsterestRateMultiple = embezzleInsterestRateMultiple;
    }

    public BigDecimal getRepayedInsterestAmount() {
        return repayedInsterestAmount;
    }

    public void setRepayedInsterestAmount(BigDecimal repayedInsterestAmount) {
        this.repayedInsterestAmount = repayedInsterestAmount;
    }

    public BigDecimal getRepayedLoanAmount() {
        return repayedLoanAmount;
    }

    public void setRepayedLoanAmount(BigDecimal repayedLoanAmount) {
        this.repayedLoanAmount = repayedLoanAmount;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getOverDays() {
        return overDays;
    }

    public void setOverDays(Integer overDays) {
        this.overDays = overDays;
    }

    public BigDecimal getLoanRemain() {
        return loanRemain;
    }

    public void setLoanRemain(BigDecimal loanRemain) {
        this.loanRemain = loanRemain;
    }

    public BigDecimal getDefaultInterest() {
        return defaultInterest;
    }

    public void setDefaultInterest(BigDecimal defaultInterest) {
        this.defaultInterest = defaultInterest;
    }

    public List<InterestFavorable> getInterestFavorables() {
        return interestFavorables;
    }

    public void setInterestFavorables(List<InterestFavorable> interestFavorables) {
        this.interestFavorables = interestFavorables;
    }

    public BigDecimal getPubnishInterest() {
        return pubnishInterest;
    }

    public void setPubnishInterest(BigDecimal pubnishInterest) {
        this.pubnishInterest = pubnishInterest;
    }

    public BigDecimal getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(BigDecimal debtAmount) {
        this.debtAmount = debtAmount;
    }

    public Date getDebtEndDate() {
        return debtEndDate;
    }

    public void setDebtEndDate(Date debtEndDate) {
        this.debtEndDate = debtEndDate;
    }

    public List<AssetEvaluation> getAssetEvaluations() {
        return assetEvaluations;
    }

    public void setAssetEvaluations(List<AssetEvaluation> assetEvaluations) {
        this.assetEvaluations = assetEvaluations;
    }

    public BigDecimal getLoanPreRepayAmount() {
        return loanPreRepayAmount;
    }

    public void setLoanPreRepayAmount(BigDecimal loanPreRepayAmount) {
        this.loanPreRepayAmount = loanPreRepayAmount;
    }

    public Integer getInputUid() {
        return inputUid;
    }

    public void setInputUid(Integer inputUid) {
        this.inputUid = inputUid;
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
}
