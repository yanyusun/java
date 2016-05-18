package com.dqys.business.orm.pojo;

import java.math.BigDecimal;

/**
 * Created by pan on 16-5-18.
 *
 * 抵押物评估
 */
public class AssetEvaluation {

    private String evaluationName;

    private BigDecimal amount;

    public String getEvaluationName() {
        return evaluationName;
    }

    public void setEvaluationName(String evaluationName) {
        this.evaluationName = evaluationName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
