package com.dqys.business.service.dto.asset;

import com.dqys.business.service.dto.common.UserDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Yvan on 16/6/16.
 */
public class AssetDTO {

    private Integer id; // 主键ID

    private Integer type;  // 资产包类型
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startAt; // 委托开始时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endAt; // 委托结束时间
    private String assetNo;  // 资产包编号
    private Double accrual; // 总利息
    private Double loan; // 总贷款
    private Double appraisal; // 总评估
    private String name; // 资源包名称
    private String evaluateExcellent; // 评优
    private String evaluateLevel; // 评级
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

    private Integer operatorId; // 操作人ID
    private UserDTO operator; // 操作人


    public String getAssetNo() {
        return assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public UserDTO getOperator() {
        return operator;
    }

    public void setOperator(UserDTO operator) {
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

    public String getEvaluateExcellent() {
        return evaluateExcellent;
    }

    public void setEvaluateExcellent(String evaluateExcellent) {
        this.evaluateExcellent = evaluateExcellent;
    }

    public String getEvaluateLevel() {
        return evaluateLevel;
    }

    public void setEvaluateLevel(String evaluateLevel) {
        this.evaluateLevel = evaluateLevel;
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
