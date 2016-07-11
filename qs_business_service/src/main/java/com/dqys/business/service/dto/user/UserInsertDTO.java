package com.dqys.business.service.dto.user;
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
 *
 * Created by Yvan on 16/6/23.
 */
public class UserInsertDTO {
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
     */

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

    private Integer companyId; // 公司Id

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
