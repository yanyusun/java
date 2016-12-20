package com.dqys.business.service.dto.asset;

import java.util.Map;

/**
 * Created by Yvan on 16/6/16.
 */
public class PawnDTO {

    private Integer id;

    private String pawnNo;  // 编号
    private String pawnName;  // 抵押物名称
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


    private Integer lenderId;  // 借款人基础信息ID

    private String iouIds;
    private String iouNames; // 借据的名称集合

    private Integer agent; // 中介
    private Integer urge; // 催收
    private Integer lawyer; // 代理

    private Map coord;//参与人员》协作器

    public Map getCoord() {
        return coord;
    }

    public void setCoord(Map coord) {
        this.coord = coord;
    }

    public Integer getAgent() {
        return agent;
    }

    public void setAgent(Integer agent) {
        this.agent = agent;
    }

    public Integer getUrge() {
        return urge;
    }

    public void setUrge(Integer urge) {
        this.urge = urge;
    }

    public Integer getLawyer() {
        return lawyer;
    }

    public void setLawyer(Integer lawyer) {
        this.lawyer = lawyer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIouIds() {
        return iouIds;
    }

    public void setIouIds(String iouIds) {
        this.iouIds = iouIds;
    }

    public String getPawnNo() {
        return pawnNo;
    }

    public void setPawnNo(String pawnNo) {
        this.pawnNo = pawnNo;
    }

    public Integer getLenderId() {
        return lenderId;
    }

    public void setLenderId(Integer lenderId) {
        this.lenderId = lenderId;
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

    public String getPawnName() {
        return pawnName;
    }

    public void setPawnName(String pawnName) {
        this.pawnName = pawnName;
    }

    public String getIouNames() {
        return iouNames;
    }

    public void setIouNames(String iouNames) {
        this.iouNames = iouNames;
    }
}
