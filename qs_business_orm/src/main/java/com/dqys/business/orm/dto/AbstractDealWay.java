package com.dqys.business.orm.dto;

/**
 * Created by pan on 16-5-18.
 */
public abstract class AbstractDealWay {

    private String dealWayName;

    public AbstractDealWay(String dealWayName) {
        this.dealWayName = dealWayName;
    }

    public String getDealWayName() {
        return dealWayName;
    }

    public void setDealWayName(String dealWayName) {
        this.dealWayName = dealWayName;
    }
}
