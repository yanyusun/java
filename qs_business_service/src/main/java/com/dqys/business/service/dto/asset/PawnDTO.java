package com.dqys.business.service.dto.asset;

import java.util.List;

/**
 * Created by Yvan on 16/6/16.
 */
public class PawnDTO {

    private Integer id;

    private String pawnNo;  // 编号
    private Double amount;  // 贷款金额
    private String type;  // 抵押物雷系
    private String evaluateExcellent;  // 评优
    private String evaluateLevel;  // 评级
    private String size;  // 规模大小
    private String province;  //省
    private String city;  // 市
    private String district;  // 区
    private String address;  // 详细地址
    private Double pawnRate;  // 抵押率
    private String disposeStatus;  // 处置状态
    private Double worth;  // 价值
    private String memo;  // 备注

    private Integer lenderId;  // 借款人基础信息ID
    private ContactDTO lender;  // 借款人信息


    private List<Integer> iouIds;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getIouIds() {
        return iouIds;
    }

    public void setIouIds(List<Integer> iouIds) {
        this.iouIds = iouIds;
    }

    public String getPawnNo() {
        return pawnNo;
    }

    public Integer getLenderId() {
        return lenderId;
    }

    public void setLenderId(Integer lenderId) {
        this.lenderId = lenderId;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public ContactDTO getLender() {
        return lender;
    }

    public void setLender(ContactDTO lender) {
        this.lender = lender;
    }
}
