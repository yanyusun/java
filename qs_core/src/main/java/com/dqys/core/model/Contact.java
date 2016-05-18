package com.dqys.core.model;

import com.dqys.core.base.BaseModel;

/**
 * Created by pan on 16-5-18.
 */
public class Contact extends BaseModel {

    private String way;
    private String value;

    public Contact(String way, String value) {
        this.way = way;
        this.value = value;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
