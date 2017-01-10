package com.dqys.business.service.dto.asset;

/**
 * Created by mkfeng on 2017/1/9.
 */
public class LenderCDTO {
    private Integer lenderId;//借款人id
    private String name;//借款人姓名
    private Integer sex;//借款人性别
    private String avg;//借款人头像
    private String idCard;//借款人身份证
    private String lastTime;//最后跟进时间
    private String address;//地址
    private Integer overdueNum;//逾期天数
    private Double debtMoney;//欠款总金额
    private String organizationConpany;//公司机构
    private String deadline;//截至日期
    private String rate;//比率

    public Integer getLenderId() {
        return lenderId;
    }

    public void setLenderId(Integer lenderId) {
        this.lenderId = lenderId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getOverdueNum() {
        return overdueNum;
    }

    public void setOverdueNum(Integer overdueNum) {
        this.overdueNum = overdueNum;
    }

    public Double getDebtMoney() {
        return debtMoney;
    }

    public void setDebtMoney(Double debtMoney) {
        this.debtMoney = debtMoney;
    }

    public String getOrganizationConpany() {
        return organizationConpany;
    }

    public void setOrganizationConpany(String organizationConpany) {
        this.organizationConpany = organizationConpany;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
