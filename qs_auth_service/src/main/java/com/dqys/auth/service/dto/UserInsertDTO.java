package com.dqys.auth.service.dto;

/**
 * Created by Yvan on 16/6/23.
 */
public class UserInsertDTO {

    private String avg; //头像地址
    private String userName; //用户昵称
    private String realName; //真实姓名
    private Integer sex; //性别
    private Integer roleId; //角色ID
    private String account; //账号
    private String mobile; //手机号
    private String wechat; //微信
    private String email; //邮箱
    private String qq; //QQ
    private Integer occupationId; //职位ID
    private String occupationTel; //座机
    private Integer apartmentId; //部门ID
    private String duty; //职责名称
    private String dutyMark; //职责介绍
    private Integer areaId; //区域
    private Integer teamId; //团队
    private Integer type; //账号类型<平台|机构委托号|个人委托号|催收处置方|个人处置方|律所|中介|c用户|个体户>

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Integer getOccupationId() {
        return occupationId;
    }

    public void setOccupationId(Integer occupationId) {
        this.occupationId = occupationId;
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

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
