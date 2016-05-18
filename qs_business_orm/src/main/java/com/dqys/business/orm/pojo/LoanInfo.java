package com.dqys.business.orm.pojo;

/**
 * Created by pan on 16-5-18.
 *
 * 贷款信息
 */
public class LoanInfo {

    private Integer loanType;
    private Integer loanWay;
    private String loanName;

    public Integer getLoanType() {
        return loanType;
    }

    public void setLoanType(Integer loanType) {
        this.loanType = loanType;
    }

    public Integer getLoanWay() {
        return loanWay;
    }

    public void setLoanWay(Integer loanWay) {
        this.loanWay = loanWay;
    }

    public String getLoanName() {
        return loanName;
    }

    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }
}
