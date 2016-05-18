package com.dqys.business.orm.pojo;

import java.math.BigDecimal;

/**
 * Created by pan on 16-5-18.
 *
 * 比例处理方式
 */
public class PercentDealWay extends AbstractDealWay {

    public PercentDealWay(String dealWayName) {
        super(dealWayName);
    }

    private BigDecimal percent;

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }
}
