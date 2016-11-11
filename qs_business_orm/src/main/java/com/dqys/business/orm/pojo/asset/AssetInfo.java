package com.dqys.business.orm.pojo.asset;

import com.dqys.core.base.BaseModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 资产包基础信息
 */
public class AssetInfo extends BaseModel {

    private String assetNo; // 编号
    private Integer type;  // 资产包类型
    private Date startAt; // 委托开始时间
    private Date endAt; // 委托结束时间
    private Integer operator; // 操作人ID
    private Double accrual; // 总利息
    private Double loan; // 总贷款
    private Double appraisal; // 总评估
    private String name; // 资源包名称
    private String evaluateExcellent; // 评优
    private String evaluateLevel; // 评级
    private Integer province;  // 省
    private Integer city;  // 市
    private Integer district;  // 区
    private String address;  // 详细地址
    private String loanOrganization;  // 贷款机构
    private String loanOrganizationDistrict;  // 贷款机构行政区域
    private String disposeMode;  // 处置方式
    private String tags;  //  标签
    private Integer isshow;  // 是否展示外网
    private String entrustName; // 委托方名称
    private Integer attribute; // 属性（0私有，1公有）
    private String memo; // 备注
    private Integer followUpTimes;
    private Date followUpTime;
    private Integer belongUpTimes;
    private Date belongUpTime;
    private Integer isCollection;
    private Integer isLawyer;
    private Integer isAgent;
    private Integer isStop;

    private Integer repayStatus; // 还款(1已还款)

    @Override
    public String toString() {
        String string = "Asset:[";
        string += "assetNo:" + assetNo + ",type:" + type + ",startAt:" + startAt
                + ",endAt:" + endAt + ",operator:" + operator + ",accrual:" + accrual
                + ",loan:" + loan + ",appraisal:" + appraisal + ",name:" + name
                + ",evaluateExcellent:" + evaluateExcellent + ",evaluateLevel:" + evaluateLevel + ",province:" + province
                + ",city:" + city + ",district:" + district + ",address:" + address
                + ",loanOrganization:" + loanOrganization + ",loanOrganizationDistrict:" + loanOrganizationDistrict + ",disposeMode:" + disposeMode
                + ",tags:" + tags + ",isshow:" + isshow;
        string += "]";
        return string;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getEntrustName() {
        return entrustName;
    }

    public void setEntrustName(String entrustName) {
        this.entrustName = entrustName;
    }

    public Integer getAttribute() {
        return attribute;
    }

    public void setAttribute(Integer attribute) {
        this.attribute = attribute;
    }

    public Integer getRepayStatus() {
        return repayStatus;
    }

    public void setRepayStatus(Integer repayStatus) {
        this.repayStatus = repayStatus;
    }

    public String getAssetNo() {
        return assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
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

    public Integer getFollowUpTimes() {
        return followUpTimes;
    }

    public void setFollowUpTimes(Integer followUpTimes) {
        this.followUpTimes = followUpTimes;
    }

    public Date getFollowUpTime() {
        return followUpTime;
    }

    public void setFollowUpTime(Date followUpTime) {
        this.followUpTime = followUpTime;
    }

    public Integer getBelongUpTimes() {
        return belongUpTimes;
    }

    public void setBelongUpTimes(Integer belongUpTimes) {
        this.belongUpTimes = belongUpTimes;
    }

    public Date getBelongUpTime() {
        return belongUpTime;
    }

    public void setBelongUpTime(Date belongUpTime) {
        this.belongUpTime = belongUpTime;
    }

    public Integer getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(Integer isCollection) {
        this.isCollection = isCollection;
    }

    public Integer getIsLawyer() {
        return isLawyer;
    }

    public void setIsLawyer(Integer isLawyer) {
        this.isLawyer = isLawyer;
    }

    public Integer getIsAgent() {
        return isAgent;
    }

    public void setIsAgent(Integer isAgent) {
        this.isAgent = isAgent;
    }

    public Integer getIsStop() {
        return isStop;
    }

    public void setIsStop(Integer isStop) {
        this.isStop = isStop;
    }
}