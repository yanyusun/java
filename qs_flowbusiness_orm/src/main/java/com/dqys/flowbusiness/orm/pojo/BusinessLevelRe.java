package com.dqys.flowbusiness.orm.pojo;

public class BusinessLevelRe {
    private Integer id;

    private Integer status;

    private Integer atBusinessType;

    private Integer goBusinessType;

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

    public Integer getAtBusinessType() {
        return atBusinessType;
    }

    public void setAtBusinessType(Integer atBusinessType) {
        this.atBusinessType = atBusinessType;
    }

    public Integer getGoBusinessType() {
        return goBusinessType;
    }

    public void setGoBusinessType(Integer goBusinessType) {
        this.goBusinessType = goBusinessType;
    }

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }
}