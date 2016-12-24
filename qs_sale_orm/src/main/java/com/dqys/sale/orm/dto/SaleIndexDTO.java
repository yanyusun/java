package com.dqys.sale.orm.dto;

/**
 * Created by mkfeng on 2016/12/24.
 */
public class SaleIndexDTO {
    private Integer accMess = 0;//帐号消息数量
    private Integer sysMess = 0;//系统通知数量
    private Integer issueMess = 0;//发布消息数量

    public Integer getAccMess() {
        return accMess;
    }

    public void setAccMess(Integer accMess) {
        this.accMess = accMess;
    }

    public Integer getSysMess() {
        return sysMess;
    }

    public void setSysMess(Integer sysMess) {
        this.sysMess = sysMess;
    }

    public Integer getIssueMess() {
        return issueMess;
    }

    public void setIssueMess(Integer issueMess) {
        this.issueMess = issueMess;
    }
}
