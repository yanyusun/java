package com.dqys.business.orm.pojo.asset;

import com.dqys.core.base.BaseModel;

import java.io.Serializable;

public class PawnInfo extends BaseModel  {

    private Integer lenderId;  // 借款人ID
    private String pawnNo;  // 编号
    private String name;  // 借据名称
    private Double amount;  // 贷款金额
    private String type;  // 抵押物类型
    private String evaluateExcellent;  // 评优
    private String evaluateLevel;  // 评级
    private String size;  // 规模大小
    private Integer province;  //省
    private Integer city;  // 市
    private Integer district;  // 区
    private String address;  // 详细地址
    private Double pawnRate;  // 抵押率
    private String disposeStatus;  // 处置状态
    private Double worth;  // 价值
    private String memo;  // 备注

    private Integer onCollection;//是否可以催收:0可以1不能',
    private Integer onLawyer;//是否可以司法处置:0可以1不能',
    private Integer onAgent;// private Integer

    public Integer getOnCollection() {
        return onCollection;
    }

    public void setOnCollection(Integer onCollection) {
        this.onCollection = onCollection;
    }

    public Integer getOnLawyer() {
        return onLawyer;
    }

    public void setOnLawyer(Integer onLawyer) {
        this.onLawyer = onLawyer;
    }

    public Integer getOnAgent() {
        return onAgent;
    }

    public void setOnAgent(Integer onAgent) {
        this.onAgent = onAgent;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLenderId() {
        return lenderId;
    }

    public void setLenderId(Integer lenderId) {
        this.lenderId = lenderId;
    }

    public String getPawnNo() {
        return pawnNo;
    }

    public void setPawnNo(String pawnNo) {
        this.pawnNo = pawnNo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEvaluateExcellent() {
        return evaluateExcellent;
    }

    public void setEvaluateExcellent(String evaluateExcellent) {
        this.evaluateExcellent = evaluateExcellent;
    }

    public String getEvaluateLevel() {
        return evaluateLevel;
    }

    public void setEvaluateLevel(String evaluateLevel) {
        this.evaluateLevel = evaluateLevel;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getPawnRate() {
        return pawnRate;
    }

    public void setPawnRate(Double pawnRate) {
        this.pawnRate = pawnRate;
    }

    public String getDisposeStatus() {
        return disposeStatus;
    }

    public void setDisposeStatus(String disposeStatus) {
        this.disposeStatus = disposeStatus;
    }

    public Double getWorth() {
        return worth;
    }

    public void setWorth(Double worth) {
        this.worth = worth;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}