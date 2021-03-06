package com.dqys.business.service.dto.asset;

import java.util.Date;

/**
 * Created by Yvan on 16/7/8.
 */
public class AssetListDTO {

    private Integer id; // 主键

    private String name; // 资产包名称
    private String code; // 编号
    private String type; // 资产包类型
    private Double accrual; // 总利息
    private Double loan; // 总贷款
    private Double appraisal; // 评估总价
    private String company; // 贷款机构
    private String city; // 行政区划
    private String entrustName; // 委托方名称
    private Integer attribute; // 属性

    private Long lessDay; // 剩余天数
    private String rate; // 完成率
    private Integer operatorId; // 录入人员ID
    private String operator; // 录入人员
    private Date createAt; // 创建时间
    private String remark; // 备注
    private Integer flag; // 状态(有效|无效)

    private Date lastFollowUpTime; // 最后跟进时间
    private String belong; // 所属人
    private Integer followNum; // 实时动态条数
    private String memo; // 备注
    private StatisticsLender statisticsLender;//统计数量

    public StatisticsLender getStatisticsLender() {
        return statisticsLender;
    }

    public void setStatisticsLender(StatisticsLender statisticsLender) {
        this.statisticsLender = statisticsLender;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getFollowNum() {
        return followNum;
    }

    public void setFollowNum(Integer followNum) {
        this.followNum = followNum;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public Date getLastFollowUpTime() {
        return lastFollowUpTime;
    }

    public void setLastFollowUpTime(Date lastFollowUpTime) {
        this.lastFollowUpTime = lastFollowUpTime;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getLessDay() {
        return lessDay;
    }

    public void setLessDay(Long lessDay) {
        this.lessDay = lessDay;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }
}
