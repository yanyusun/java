package com.dqys.business.orm.dto;

import java.math.BigDecimal;

/**
 * Created by pan on 16-5-18.
 *
 * 利息优惠
 */
public class InterestFavorable {

    private String name;
    private BigDecimal value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
