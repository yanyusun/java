package com.dqys.auth.orm.query;

import com.dqys.core.base.BaseQuery;

import java.util.List;

/**
 * @author by pan on 16-5-3.
 */
public class CompanyQuery extends BaseQuery {

    private String credential; // 信用码
    private Integer province; // 省份
    private Integer city; // 城市
    private Integer district; // 地区
    private String nameLike; // 名称模糊查询
    private String accountCode;//清搜号
    private List<Integer> companyIds;//公司id集合
    private Integer businessType;//公司帐号类型（委托或是处置机构）

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public List<Integer> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(List<Integer> companyIds) {
        this.companyIds = companyIds;
    }

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
