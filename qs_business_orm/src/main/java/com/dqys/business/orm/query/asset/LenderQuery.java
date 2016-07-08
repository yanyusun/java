package com.dqys.business.orm.query.asset;

import com.dqys.core.base.BaseQuery;

/**
 * Created by Yvan on 16/6/8.
 */
public class LenderQuery extends BaseQuery {

    private Integer lenderId; // 借款人ID
    private String idCard; // 身份证号码
    private String idCardLike; // 身份证模糊查询

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCardLike() {
        return idCardLike;
    }

    public void setIdCardLike(String idCardLike) {
        this.idCardLike = idCardLike;
    }

    public Integer getLenderId() {
        return lenderId;
    }

    public void setLenderId(Integer lenderId) {
        this.lenderId = lenderId;
    }

}
