package com.dqys.auth.orm.pojo.asset;

import com.dqys.core.base.BaseModel;

import java.io.Serializable;

public class PawnInfo extends BaseModel implements Serializable{

    private Integer lenderId;  // 借款人ID

    private String code;  // 编号

    private Double amount;  // 贷款金额

    private String type;  // 抵押物雷系

    private String quality;  // 评优

    private String level;  // 评级

    private String size;  // 规模大小

    private String province;  //省

    private String city;  // 市

    private String district;  // 区

    private String address;  // 详细地址

    private Double pawnRate;  // 抵押率

    private String disposeStatus;  // 处置状态

    private Double worth;  // 价值

    private String iouIds;  // 借据IDS

    private String memo;  // 备注


    public Integer getLenderId() {
        return lenderId;
    }

    public void setLenderId(Integer lenderId) {
        this.lenderId = lenderId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
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

    public String getIouIds() {
        return iouIds;
    }

    public void setIouIds(String iouIds) {
        this.iouIds = iouIds;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}