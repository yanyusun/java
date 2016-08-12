package com.dqys.core.model;

import java.io.Serializable;

public class TArea implements Serializable {
    private Integer value; // 由原来的id转变

    private Integer upper;

    private String label; // 由原来的name转变

    private Integer level;

    private Boolean isLeaf;

    private static final long serialVersionUID = 1L;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUpper() {
        return upper;
    }

    public void setUpper(Integer upper) {
        this.upper = upper;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }
}