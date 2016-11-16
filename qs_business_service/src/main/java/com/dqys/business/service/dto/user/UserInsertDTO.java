package com.dqys.business.service.dto.user;
/**
 * @apiDefine UserInsertDTO
 * @apiSuccessExample {json} UserInsert:
 * {
 * "id": 30,
 * "avg": null,
 * "userName": "name1",
 * "realName": "real11111",
 * "sex": 0,
 * "roleId": 13,
 * "account": "asd",
 * "mobile": "13302021212",
 * "wechat": "wechar",
 * "email": "email",
 * "qq": null,
 * "occupation": "职位",
 * "occupationTel": null,
 * "apartmentId": 1,
 * "duty": "经理助理",
 * "dutyMark": null,
 * "areaId": 1128,
 * "teamId": null,
 * "remark": null,
 * "userType": 1,
 * "companyId": 120
 * }
 * git 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/user/UserInsertDTO.java
 */

import java.util.List;

/**
 * @apiDefine UserInsert
 * @apiParam {number} [id] 主键
 * @apiParam {string} [avg] 头像地址
 * @apiParam {string} userName 昵称
 * @apiParam {string} realName 真实姓名
 * @apiParam {number} sex 性别
 * @apiParam {number} roleId 角色ID
 * @apiParam {String} account 账号
 * @apiParam {String} mobile 手机号
 * @apiParam {String} wechat 微信号
 * @apiParam {String} email 邮箱
 * @apiParam {String} [qq] QQ账号
 * @apiParam {String} occupation 职位
 * @apiParam {String} [occupationTel] 座机
 * @apiParam {String} duty 职责
 * @apiParam {String} dutyMark 职责介绍
 * @apiParam {number} apartmentId 部门ID
 * @apiParam {number} areaId 职责区域
 * @apiParam {number} [teamId] 团队
 * @apiParam {string} [remark] 备注
 * @apiParam {number} userType 用户类型
 * @apiParam {number} [companyId] 公司Id
 * @apiParam {string} entryTime 入职时间(yyyy-MM-dd)
 * @apiParam {number} yearsLimit 从业年限
 * @apiParam {number} workStatus 工作状态（0默认在职，1离职，2请假）
 * <p/>
 * Created by Yvan on 16/6/23.
 */
public class UserInsertDTO {
    private Integer id;
    private String avg; //头像地址
    private String userName; //用户昵称(必填)
    private String realName; //真实姓名(必填)
    private Integer sex; //性别<男1|女0>(必填)
    private Integer roleId; //角色ID(必填)
    private String account; //账号(必填)
    private String mobile; //手机号(必填)
    private String wechat; //微信(必填)
    private String email; //邮箱(必填)
    private String qq; //QQ
    private String occupation; //职位(必填)
    private String occupationTel; //座机
    private Integer apartmentId; //部门ID(必填)
    private String duty; //职责名称(必填)
    private String dutyMark; //职责介绍
    private Integer areaId; //职责区域(必填)
    private Integer teamId; //团队
    private String remark; // 备注
    private Integer userType; //用户类型(必填)

    private Integer useStatus; //帐号使用状态
    private Integer companyId; // 公司Id

    private List<Integer> userIds;//多个用户存储

    private String companyName;//公司名称
    private String apartmentName;//部门名称
    private String teamName;//团队名称
    private String address;//地址
    private String entryTime;//入职时间
    private Integer yearsLimit;//从业年限
    private Integer workStatus;//工作状态（0默认在职，1离职，2请假）
    private String leaveWordTime;//最后留言时间
    private String accountCreateTime;//帐号创建时间
    private String resultsContrast;//业绩比例
    private String ongoing;//当前进行的任务数

    public String getResultsContrast() {
        return resultsContrast;
    }

    public void setResultsContrast(String resultsContrast) {
        this.resultsContrast = resultsContrast;
    }

    public String getOngoing() {
        return ongoing;
    }

    public void setOngoing(String ongoing) {
        this.ongoing = ongoing;
    }

    public String getAccountCreateTime() {
        return accountCreateTime;
    }

    public void setAccountCreateTime(String accountCreateTime) {
        this.accountCreateTime = accountCreateTime;
    }

    public String getLeaveWordTime() {
        return leaveWordTime;
    }

    public void setLeaveWordTime(String leaveWordTime) {
        this.leaveWordTime = leaveWordTime;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public Integer getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

}
