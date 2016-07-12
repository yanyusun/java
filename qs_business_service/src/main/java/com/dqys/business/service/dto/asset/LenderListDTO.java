package com.dqys.business.service.dto.asset;

import java.util.Date;

/**
 * Created by Yvan on 16/7/11.
 * @apiDefine LenderListDTO
 * @apiSuccessExample {json} lenderListDTO:
 * {
 *     id:1,
 * }
 */
public class LenderListDTO {

    private Integer id; // 主键
    private String avg; // 头像
    private String name; // 姓名
    private String code; // 编号
    private Double accrual; // 总利息
    private Double loan; // 总贷款
    private Double appraisal; // 总评估
    private String urgeType; // 催收阶段
    private String evaluateExcellent; // 评优
    private String evaluateLevel; // 评级
    private Integer manageTime; // 所属人催收次数
    private Integer lessDay; // 委托剩余天数
    private Date lastFollow; // 最后跟进时间
    private Integer FollowTime; // 跟进次数

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getFollowTime() {
        return FollowTime;
    }

    public void setFollowTime(Integer followTime) {
        FollowTime = followTime;
    }
}
