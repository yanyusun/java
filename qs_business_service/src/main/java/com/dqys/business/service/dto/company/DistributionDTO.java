package com.dqys.business.service.dto.company;

import java.util.List;

/**
 * Created by Yvan on 16/7/14.
 * 分配器
 */
public class DistributionDTO {
    private Integer id; // 分配器ID

    private Integer platformNum = 0; // 平台数量
    private Integer mechanismNum = 0; // 机构数量
    private Integer disposeNum = 0; // 处置方数量
    private double accrual = 0; // 总利息'
    private double loan = 0; // 总贷款
    private double appraisal = 0; // 总评估
    private String numberNo; //编号
    private String name; // 名称
    private String sex; // 性别
    private String avg; // 头像

    private List<CompanyTeamReDTO> companyTeamReDTOList; // 分配器成员
    private List<BusinessServiceDTO> businessServiceDTOList; // 业务流成员

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public double getAccrual() {
        return accrual;
    }

    public void setAccrual(double accrual) {
        this.accrual = accrual;
    }

    public double getLoan() {
        return loan;
    }

    public void setLoan(double loan) {
        this.loan = loan;
    }

    public double getAppraisal() {
        return appraisal;
    }

    public void setAppraisal(double appraisal) {
        this.appraisal = appraisal;
    }

    public String getNumberNo() {
        return numberNo;
    }

    public void setNumberNo(String numberNo) {
        this.numberNo = numberNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlatformNum() {
        return platformNum;
    }

    public void setPlatformNum(Integer platformNum) {
        this.platformNum = platformNum;
    }

    public Integer getMechanismNum() {
        return mechanismNum;
    }

    public void setMechanismNum(Integer mechanismNum) {
        this.mechanismNum = mechanismNum;
    }

    public Integer getDisposeNum() {
        return disposeNum;
    }

    public void setDisposeNum(Integer disposeNum) {
        this.disposeNum = disposeNum;
    }

    public List<CompanyTeamReDTO> getCompanyTeamReDTOList() {
        return companyTeamReDTOList;
    }

    public void setCompanyTeamReDTOList(List<CompanyTeamReDTO> companyTeamReDTOList) {
        this.companyTeamReDTOList = companyTeamReDTOList;
    }

    public List<BusinessServiceDTO> getBusinessServiceDTOList() {
        return businessServiceDTOList;
    }

    public void setBusinessServiceDTOList(List<BusinessServiceDTO> businessServiceDTOList) {
        this.businessServiceDTOList = businessServiceDTOList;
    }
}
