package com.dqys.business.orm.pojo.coordinator;

/**
 * Created by mkfeng on 2016/12/7.
 */
public class CompanyDTO {
    private Integer id;//公司id
    private String company_name;//公司名称
    private Integer type;//公司类型
    private Integer userType;//用户类型（参考UserInfoEnum）
    private Integer organizationStatus;//机构状态（0所属机构，1参与方：在协作器中是所属机构还是参与方）
    private String alias;//别名-》组装展示名称

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getOrganizationStatus() {
        return organizationStatus;
    }

    public void setOrganizationStatus(Integer organizationStatus) {
        this.organizationStatus = organizationStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
