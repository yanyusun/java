package com.dqys.core.model;


import com.dqys.core.base.BaseModel;

/**
 * @author by pan on 16-3-15.
 */
public class TSysProperty extends BaseModel {

    private Integer type;

    private String propertyName;

    private String propertyValue;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }
}
