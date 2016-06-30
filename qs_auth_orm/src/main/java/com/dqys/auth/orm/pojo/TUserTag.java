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