package com.dqys.business.orm.query.asset;

import com.dqys.core.base.BasePagination;

/**
 * Created by Yvan on 16/6/8.
 */
public class LenderQuery extends BasePagination {

    private Integer id; // 主键ID
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
