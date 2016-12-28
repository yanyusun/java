package com.dqys.flowbusiness.orm.pojo;

public class BusinessLevelRe {
    private Integer id;

    private Integer status;

    private Integer atBusinessLevel;

    private Integer goBusinessLevel;

    private Integer operType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAtBusinessLevel() {
        return atBusinessLevel;
    }

    public void setAtBusinessLevel(Integer atBusinessLevel) {
        this.atBusinessLevel = atBusinessLevel;
    }

    public Integer getGoBusinessLevel() {
        return goBusinessLevel;
    }

    public void setGoBusinessLevel(Integer goBusinessLevel) {
        this.goBusinessLevel = goBusinessLevel;
    }

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }
}