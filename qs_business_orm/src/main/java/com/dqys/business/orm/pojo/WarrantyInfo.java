package com.dqys.business.orm.pojo;

/**
 * Created by pan on 16-5-18.
 *
 * 委托信息
 */
public class WarrantyInfo {

    private Integer commissionFrom;     //委托来源
    private Integer guaranteeWay;       //担保方式
    private Boolean canCantact;
    private String pecuniaryCondition;      //经济状况

    public Integer getCommissionFrom() {
        return commissionFrom;
    }

    public void setCommissionFrom(Integer commissionFrom) {
        this.commissionFrom = commissionFrom;
    }

    public Integer getGuaranteeWay() {
        return guaranteeWay;
    }

    public void setGuaranteeWay(Integer guaranteeWay) {
        this.guaranteeWay = guaranteeWay;
    }

    public Boolean getCanCantact() {
        return canCantact;
    }

    public void setCanCantact(Boolean canCantact) {
        this.canCantact = canCantact;
    }

    public String getPecuniaryCondition() {
        return pecuniaryCondition;
    }

    public void setPecuniaryCondition(String pecuniaryCondition) {
        this.pecuniaryCondition = pecuniaryCondition;
    }
}
