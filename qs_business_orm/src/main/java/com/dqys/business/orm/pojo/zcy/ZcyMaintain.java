package com.dqys.business.orm.pojo.zcy;

import java.io.Serializable;
import java.util.Date;

/**
 * 维护信息表（资产源）
 *
 * @apiDefine ZcyMaintain
 * @apiParam {number} id
 * @apiParam {number} estatesId 资产信息id
 * @apiParam {double} originalPrice 原购房价格（单位：万）
 * @apiParam {double} marketPrice 市场评估价（单位：万）
 * @apiParam {double} agencyPrice 代理价格（单位：万）
 * @apiParam {double} profitPrice 建议设置利润价（单位：万）
 * @apiParam {double} remodelingsPrice 装修费用
 * @apiParam {double} loanAmount 贷款金额
 * @apiParam {string} advantage 优劣势
 * @apiParam {string} adaptability 配合度
 * @apiParam {string} proposition 销售建议
 * @apiParam {string} bonusPacks 附加赠送
 * @apiParam {string} ownerClaim 业主要求
 * @apiParam {string} warn 特别提醒
 * @apiParam {string} replenish 补充
 * @apiParam {string} fulls 满几年
 * @apiParam {string} sole 唯一
 * @apiParam {string} account 户口状况
 * @apiParam {string} owned 是否共有
 * @apiParam {string} houseCase 房屋状况
 * @apiParam {string} decorateCase 装修状况
 * @apiParam {string} decorateType 装修类型
 * @apiParam {string} decorateTime 装修时间(格式：yyyy-MM-dd)
 * @apiParam {string} schoolHouseNumber 学区房名额
 * @apiParam {string} keyes 是否能留钥匙
 * @apiParam {string} lookHouseTime 看房时间
 * @apiParam {string} certificateComplete 两证是否齐全
 * @apiParam {string} equityNum 产权共有人数
 * @apiParam {string} sign 签约当日能否到场
 * @apiParam {string} loan 是否有贷款
 * @apiParam {string} loanPrice 贷款金额
 * @apiParam {string} inHouse 户口是否在本房屋内
 * @apiParam {string} give 家具家电是否赠送
 * @apiParam {string} carport 是否有车位
 * @apiParam {string} houseOccupy 学区房是否被占用
 * @apiParam {string} occupyTime 已占用多久
 * @apiParam {string} expectTime 预期售房时间
 * @apiParam {string} fullFive 是否满五唯一
 * @apiParam {string} payWay 付款方式有何要求
 */
public class ZcyMaintain implements Serializable {
    private Integer id;

    private Integer estatesId;//资产信息id

    private Double originalPrice;//原购房价格（单位：万）

    private Double marketPrice;//市场评估价（单位：万）

    private Double agencyPrice;//代理价格（单位：万）

    private Double profitPrice;// 建议设置利润价（单位：万）

    private Double remodelingsPrice;//装修费用

    private Double loanAmount;// 贷款金额

    private String advantage;//优劣势

    private String adaptability;//配合度

    private String proposition;// 销售建议

    private String bonusPacks;//附加赠送

    private String ownerClaim;// 业主要求

    private String warn;//特别提醒

    private String replenish;// 补充

    private String fulls;// 满几年

    private String sole;// 唯一

    private String account;// 户口状况

    private String owned;// 是否共有

    private String houseCase;// 房屋状况

    private String decorateCase;// 装修状况

    private String decorateType;// 装修类型

    private String decorateTime;// 装修时间

    private String schoolHouseNumber;//学区房名额

    private String keyes;//是否能留钥匙

    private String lookHouseTime;// 看房时间

    private String certificateComplete;//两证是否齐全

    private String equityNum;//产权共有人数

    private String sign;//签约当日能否到场

    private String loan;//是否有贷款

    private String loanPrice;// 贷款金额

    private String inHouse;//户口是否在本房屋内

    private String give;// 家具家电是否赠送

    private String carport;//是否有车位

    private String houseOccupy;//学区房是否被占用

    private String occupyTime;//已占用多久

    private String expectTime;//预期售房时间

    private String fullFive;//是否满五唯一

    private String payWay;//付款方式有何要求

    private Integer version;

    private Date createAt;

    private Date updateAt;

    private Long stateflag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstatesId() {
        return estatesId;
    }

    public void setEstatesId(Integer estatesId) {
        this.estatesId = estatesId;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Double getAgencyPrice() {
        return agencyPrice;
    }

    public void setAgencyPrice(Double agencyPrice) {
        this.agencyPrice = agencyPrice;
    }

    public Double getProfitPrice() {
        return profitPrice;
    }

    public void setProfitPrice(Double profitPrice) {
        this.profitPrice = profitPrice;
    }

    public Double getRemodelingsPrice() {
        return remodelingsPrice;
    }

    public void setRemodelingsPrice(Double remodelingsPrice) {
        this.remodelingsPrice = remodelingsPrice;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getAdvantage() {
        return advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }

    public String getAdaptability() {
        return adaptability;
    }

    public void setAdaptability(String adaptability) {
        this.adaptability = adaptability;
    }

    public String getProposition() {
        return proposition;
    }

    public void setProposition(String proposition) {
        this.proposition = proposition;
    }

    public String getBonusPacks() {
        return bonusPacks;
    }

    public void setBonusPacks(String bonusPacks) {
        this.bonusPacks = bonusPacks;
    }

    public String getOwnerClaim() {
        return ownerClaim;
    }

    public void setOwnerClaim(String ownerClaim) {
        this.ownerClaim = ownerClaim;
    }

    public String getWarn() {
        return warn;
    }

    public void setWarn(String warn) {
        this.warn = warn;
    }

    public String getReplenish() {
        return replenish;
    }

    public void setReplenish(String replenish) {
        this.replenish = replenish;
    }

    public String getFulls() {
        return fulls;
    }

    public void setFulls(String fulls) {
        this.fulls = fulls;
    }

    public String getSole() {
        return sole;
    }

    public void setSole(String sole) {
        this.sole = sole;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getOwned() {
        return owned;
    }

    public void setOwned(String owned) {
        this.owned = owned;
    }

    public String getHouseCase() {
        return houseCase;
    }

    public void setHouseCase(String houseCase) {
        this.houseCase = houseCase;
    }

    public String getDecorateCase() {
        return decorateCase;
    }

    public void setDecorateCase(String decorateCase) {
        this.decorateCase = decorateCase;
    }

    public String getDecorateType() {
        return decorateType;
    }

    public void setDecorateType(String decorateType) {
        this.decorateType = decorateType;
    }

    public String getDecorateTime() {
        return decorateTime;
    }

    public void setDecorateTime(String decorateTime) {
        this.decorateTime = decorateTime;
    }

    public String getSchoolHouseNumber() {
        return schoolHouseNumber;
    }

    public void setSchoolHouseNumber(String schoolHouseNumber) {
        this.schoolHouseNumber = schoolHouseNumber;
    }

    public String getKeyes() {
        return keyes;
    }

    public void setKeyes(String keyes) {
        this.keyes = keyes;
    }

    public String getLookHouseTime() {
        return lookHouseTime;
    }

    public void setLookHouseTime(String lookHouseTime) {
        this.lookHouseTime = lookHouseTime;
    }

    public String getCertificateComplete() {
        return certificateComplete;
    }

    public void setCertificateComplete(String certificateComplete) {
        this.certificateComplete = certificateComplete;
    }

    public String getEquityNum() {
        return equityNum;
    }

    public void setEquityNum(String equityNum) {
        this.equityNum = equityNum;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getLoan() {
        return loan;
    }

    public void setLoan(String loan) {
        this.loan = loan;
    }

    public String getLoanPrice() {
        return loanPrice;
    }

    public void setLoanPrice(String loanPrice) {
        this.loanPrice = loanPrice;
    }

    public String getInHouse() {
        return inHouse;
    }

    public void setInHouse(String inHouse) {
        this.inHouse = inHouse;
    }

    public String getGive() {
        return give;
    }

    public void setGive(String give) {
        this.give = give;
    }

    public String getCarport() {
        return carport;
    }

    public void setCarport(String carport) {
        this.carport = carport;
    }

    public String getHouseOccupy() {
        return houseOccupy;
    }

    public void setHouseOccupy(String houseOccupy) {
        this.houseOccupy = houseOccupy;
    }

    public String getOccupyTime() {
        return occupyTime;
    }

    public void setOccupyTime(String occupyTime) {
        this.occupyTime = occupyTime;
    }

    public String getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(String expectTime) {
        this.expectTime = expectTime;
    }

    public String getFullFive() {
        return fullFive;
    }

    public void setFullFive(String fullFive) {
        this.fullFive = fullFive;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Long getStateflag() {
        return stateflag;
    }

    public void setStateflag(Long stateflag) {
        this.stateflag = stateflag;
    }
}