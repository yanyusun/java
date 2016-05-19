package com.dqys.business.orm.dto;

import java.math.BigDecimal;

/**
 * Created by pan on 16-5-18.
 *
 * 方案处置方式
 */
public class PlanDealWay extends AbstractDealWay {

    public PlanDealWay(String dealWayName) {
        super(dealWayName);
    }

    private BigDecimal percent;

    private BigDecimal amount;

    private String planDesc;

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPlanDesc() {
        return planDesc;
    }

    public void setPlanDesc(String planDesc) {
        this.planDesc = planDesc;
    }
}
