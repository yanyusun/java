package com.dqys.auth.orm.query;

import com.dqys.core.base.BaseQuery;

/**
 * @author by pan on 16-5-3.
 */
public class CompanyQuery extends BaseQuery {

    private String credential; // 信用码
    private Integer province; // 省份
    private Integer city; // 城市
    private Integer district; // 地区
    private String nameLike; // 名称模糊查询

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public String getNameLike() {
        return nameLike;
    }

    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
    }
}
