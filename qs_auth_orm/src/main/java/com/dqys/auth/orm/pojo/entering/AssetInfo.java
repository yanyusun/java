package com.dqys.auth.orm.pojo.entering;

import com.dqys.core.base.BaseModel;

import java.io.Serializable;
import java.util.Date;

public class AssetInfo extends BaseModel implements Serializable{

    private String code; // 编号

    private Integer type;  // 资产包类型

    private Date entrustStartTime; // 委托开始时间

    private Date entrustEndTime; // 委托结束时间

    private Integer operator; // 操作人ID

    private Double accrual; // 总利息

    private Double loan; // 总贷款

    private Double appraisal; // 总评估

    private String name; // 资源包名称

    private String quality; // 评优

    private String level; // 评级

    private String province;  // 省

    private String city;  // 市

    private String district;  // 区

    private String address;  // 详细地址

    private String loanOrganization;  // 贷款机构

    private String loanOrganizationDistrict;  // 贷款机构行政区域

    private String disposeMode;  // 处置方式

    private String tags;  //  标签

    private Integer isshow;  // 是否展示外网

    private String memo;  // 备注

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getEntrustStartTime() {
        return entrustStartTime;
    }

    public void setEntrustStartTime(Date entrustStartTime) {
        this.entrustStartTime = entrustStartTime;
    }

    public Date getEntrustEndTime() {
        return entrustEndTime;
    }

    public void setEntrustEndTime(Date entrustEndTime) {
        this.entrustEndTime = entrustEndTime;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public Double getAccrual() {
        return accrual;
    }

    public void setAccrual(Double accrual) {
        this.accrual = accrual;
    }

    public Double getLoan() {
        return loan;
    }

    public void setLoan(Double loan) {
        this.loan = loan;
    }

    public Double getAppraisal() {
        return appraisal;
    }

    public void setAppraisal(Double appraisal) {
        this.appraisal = appraisal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLoanOrganization() {
        return loanOrganization;
    }

    public void setLoanOrganization(String loanOrganization) {
        this.loanOrganization = loanOrganization;
    }

    public String getLoanOrganizationDistrict() {
        return loanOrganizationDistrict;
    }

    public void setLoanOrganizationDistrict(String loanOrganizationDistrict) {
        this.loanOrganizationDistrict = loanOrganizationDistrict;
    }

    public String getDisposeMode() {
        return disposeMode;
    }

    public void setDisposeMode(String disposeMode) {
        this.disposeMode = disposeMode;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getIsshow() {
        return isshow;
    }

    public void setIsshow(Integer isshow) {
        this.isshow = isshow;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}