package com.dqys.sale.service.dto;

import com.dqys.sale.orm.pojo.AssetFile;
import com.dqys.sale.orm.pojo.Dispose;
import com.dqys.sale.orm.pojo.Label;

import java.util.Date;
import java.util.List;

/**
 * 债权
 * Created by mkfeng on 2016/12/26.
 */
public class UBDto {
    private Integer id;
    private String bondNo;//编码
    private String title;//标题',
    private Integer grade;//评分
    private Integer isSpecial;//是否专项
    private Date startTime;//委托开始时间
    private Date endTime;//委托结束时间
    private Double totalMoney;//债权总额
    private String address;//具体地址
    private Double totalInterestMoney;//'总利息
    private Double loanMoney;//贷款金额
    private Double assessTotalPrice;//评估总价
    private Integer collectionNum;//收藏数量
    private Integer disposeNum;//申请处置数量
    private Integer disposeStatus;// 处置状态（0待处置1处置中2已处置）

    public Integer getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(Integer isSpecial) {
        this.isSpecial = isSpecial;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBondNo() {
        return bondNo;
    }

    public void setBondNo(String bondNo) {
        this.bondNo = bondNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getTotalInterestMoney() {
        return totalInterestMoney;
    }

    public void setTotalInterestMoney(Double totalInterestMoney) {
        this.totalInterestMoney = totalInterestMoney;
    }

    public Double getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(Double loanMoney) {
        this.loanMoney = loanMoney;
    }

    public Double getAssessTotalPrice() {
        return assessTotalPrice;
    }

    public void setAssessTotalPrice(Double assessTotalPrice) {
        this.assessTotalPrice = assessTotalPrice;
    }

    public Integer getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(Integer collectionNum) {
        this.collectionNum = collectionNum;
    }

    public Integer getDisposeNum() {
        return disposeNum;
    }

    public void setDisposeNum(Integer disposeNum) {
        this.disposeNum = disposeNum;
    }

    public Integer getDisposeStatus() {
        return disposeStatus;
    }

    public void setDisposeStatus(Integer disposeStatus) {
        this.disposeStatus = disposeStatus;
    }
}
