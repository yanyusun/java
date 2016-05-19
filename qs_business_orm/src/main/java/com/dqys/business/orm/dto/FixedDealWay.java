package com.dqys.business.orm.dto;

/**
 * Created by pan on 16-5-18.
 *
 * 固定费用处置方式
 */
public class FixedDealWay extends AbstractDealWay {

    public FixedDealWay(String dealWayName) {
        super(dealWayName);
    }

    private Long amount;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
