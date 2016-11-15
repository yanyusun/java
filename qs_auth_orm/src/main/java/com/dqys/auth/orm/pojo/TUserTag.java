package com.dqys.auth.orm.pojo;

import com.dqys.core.base.BaseModel;

import java.io.Serializable;

public class TUserTag extends BaseModel implements Serializable {

    private int userId;

    private Byte userType;

    private Byte roleId;

    private Boolean isCertified;

    private String occupation; // 职位
    private String occupationTel; // 座机
    private Integer apartmentId; // 部门ID
    private String duty; // 职责名称
    private String dutyMark; // 职责简介
    private Integer dutyArea; // 职责区域
    private Integer teamId; // 团队ID
    //后续补充
    private String entryTime;//入职时间
    private Integer yearsLimit;//从业年限
    private Integer workStatus;//工作状态（0默认在职，1离职，2请假）

    public Integer getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public Integer getYearsLimit() {
        return yearsLimit;
    }

    public void setYearsLimit(Integer yearsLimit) {
        this.yearsLimit = yearsLimit;
    }

    private static final long serialVersionUID = 1L;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public Byte getRoleId() {
        return roleId;
    }

    public void setRoleId(Byte roleId) {
        this.roleId = roleId;
    }

    public Boolean getIsCertified() {
        return isCertified;
    }

    public void setIsCertified(Boolean isCertified) {
        this.isCertified = isCertified;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getOccupationTel() {
        return occupationTel;
    }

    public void setOccupationTel(String occupationTel) {
        this.occupationTel = occupationTel;
    }

    public Integer getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Integer apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getDutyMark() {
        return dutyMark;
    }

    public void setDutyMark(String dutyMark) {
        this.dutyMark = dutyMark;
    }

    public Integer getDutyArea() {
        return dutyArea;
    }

    public void setDutyArea(Integer dutyArea) {
        this.dutyArea = dutyArea;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}