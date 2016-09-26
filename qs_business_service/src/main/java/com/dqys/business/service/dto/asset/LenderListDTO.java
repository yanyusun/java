package com.dqys.business.service.dto.asset;

import com.dqys.business.orm.pojo.coordinator.team.TeamDTO;

import java.util.Date;
import java.util.List;

/**
 * Created by Yvan on 16/7/11.
 */
public class LenderListDTO {
    private String avg; // 头像
    private Integer lenderId; // 借款人ID
    private String name; // 姓名
    private Integer sex; // 性别
    private String code; // 编号
    private Double accrual; // 总利息
    private Double loan; // 总贷款
    private Double appraisal; // 总评估
    private String phone; // 电话
    private String sourceType; // 来源类型
    private String urgeType; // 催收阶段
    private String evaluateExcellent; // 评优
    private String evaluateLevel; // 评级
    private Integer manageTime; // 所属人催收次数
    private Integer lessDay; // 委托剩余天数
    private Date lastFollow; // 最后跟进时间
    private String status; // 化解状态
    private Integer followTime; // 跟进次数
    private Integer belongId; // 所属人ID
    private String belongName; // 所属人姓名
    private Integer message; // 实时动态
    private String memo; // 备注
    private List<TeamDTO> coordinator; // 协作器


    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public Integer getLenderId() {
        return lenderId;
    }

    public void setLenderId(Integer lenderId) {
        this.lenderId = lenderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getUrgeType() {
        return urgeType;
    }

    public void setUrgeType(String urgeType) {
        this.urgeType = urgeType;
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

    public Integer getManageTime() {
        return manageTime;
    }

    public void setManageTime(Integer manageTime) {
        this.manageTime = manageTime;
    }

    public Integer getLessDay() {
        return lessDay;
    }

    public void setLessDay(Integer lessDay) {
        this.lessDay = lessDay;
    }

    public Date getLastFollow() {
        return lastFollow;
    }

    public void setLastFollow(Date lastFollow) {
        this.lastFollow = lastFollow;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Integer followTime) {
        this.followTime = followTime;
    }

    public Integer getBelongId() {
        return belongId;
    }

    public void setBelongId(Integer belongId) {
        this.belongId = belongId;
    }

    public String getBelongName() {
        return belongName;
    }

    public void setBelongName(String belongName) {
        this.belongName = belongName;
    }

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<TeamDTO> getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(List<TeamDTO> coordinator) {
        this.coordinator = coordinator;
    }
}
