package com.dqys.auth.orm.query;

import com.dqys.core.base.BaseQuery;

/**
 * @author by pan on 16-5-3.
 */
public class CompanyQuery extends BaseQuery {

    private String credential;

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }
}
