package com.dqys.business.service.dto.company;

/**
 * Created by Yvan on 16/6/30.
 * 公司信息
 */
public class CompanyDTO {

    private Integer id; // 主键

    private String name; // 公司名称
    private String province; // 省份
    private String city; // 城市
    private String district; // 区域
    private Integer companyType;//公司类型（1平台2委托31催收32律所33中介）

    public Integer getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
