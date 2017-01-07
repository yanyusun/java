package com.dqys.sale.orm.query;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mkfeng on 2016/12/22.
 */
public class UserBondQuery extends PageEntity implements Serializable {
    private List<Integer> ids;
    private Integer isHomePage;//是否首页
    private Integer bondType;//债权类型
    private Integer checkStatus;//0待审核1审核未通过2审核已通过
    private Integer enables;//'是否无效0不是,1是
    private Integer businessStatus;//业务状态
    private Integer userId;
    private Integer objectType;


    //-----------------------------------------> 新增

    private Integer gradeSort;//评分(1倒叙2正)

    private Integer province;//资产所在省份

    private Integer city;//资产所在城市

    private Integer area;//资产所在区县

    private Double totalInterestMoneyBegin;//'总利息

    private Double totalInterestMoneyEnd;//'总利息

    private Double loanMoneyBegin;//贷款金额

    private Double loanMoneyEnd;//贷款金额

    private Double assessTotalPriceBegin;//评估总价
    private Double assessTotalPriceEnd;//评估总价

    private String loanType;//贷款类型

    private Integer disposes;//处置方式


    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(Integer businessStatus) {
        this.businessStatus = businessStatus;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getEnables() {
        return enables;
    }

    public void setEnables(Integer enables) {
        this.enables = enables;
    }

    public Integer getBondType() {
        return bondType;
    }

    public void setBondType(Integer bondType) {
        this.bondType = bondType;
    }

    public Integer getIsHomePage() {
        return isHomePage;
    }

    public void setIsHomePage(Integer isHomePage) {
        this.isHomePage = isHomePage;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public Integer getGradeSort() {
        return gradeSort;
    }

    public void setGradeSort(Integer gradeSort) {
        this.gradeSort = gradeSort;
    }

    public Integer getDisposes() {
        return disposes;
    }

    public void setDisposes(Integer disposes) {
        this.disposes = disposes;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public Double getAssessTotalPriceBegin() {
        return assessTotalPriceBegin;
    }

    public void setAssessTotalPriceBegin(Double assessTotalPriceBegin) {
        this.assessTotalPriceBegin = assessTotalPriceBegin;
    }

    public Double getAssessTotalPriceEnd() {
        return assessTotalPriceEnd;
    }

    public void setAssessTotalPriceEnd(Double assessTotalPriceEnd) {
        this.assessTotalPriceEnd = assessTotalPriceEnd;
    }

    public Double getTotalInterestMoneyBegin() {
        return totalInterestMoneyBegin;
    }

    public void setTotalInterestMoneyBegin(Double totalInterestMoneyBegin) {
        this.totalInterestMoneyBegin = totalInterestMoneyBegin;
    }

    public Double getLoanMoneyEnd() {
        return loanMoneyEnd;
    }

    public void setLoanMoneyEnd(Double loanMoneyEnd) {
        this.loanMoneyEnd = loanMoneyEnd;
    }

    public Double getLoanMoneyBegin() {
        return loanMoneyBegin;
    }

    public void setLoanMoneyBegin(Double loanMoneyBegin) {
        this.loanMoneyBegin = loanMoneyBegin;
    }

    public Double getTotalInterestMoneyEnd() {
        return totalInterestMoneyEnd;
    }

    public void setTotalInterestMoneyEnd(Double totalInterestMoneyEnd) {
        this.totalInterestMoneyEnd = totalInterestMoneyEnd;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

}
