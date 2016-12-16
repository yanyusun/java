package com.dqys.business.orm.pojo.partner;

import com.dqys.auth.orm.query.CompanyQuery;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/16.
 */
public class ModulPartner implements Serializable {
    private CompanyQuery query;
    private String account;
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public CompanyQuery getQuery() {
        return query;
    }

    public void setQuery(CompanyQuery query) {
        this.query = query;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
