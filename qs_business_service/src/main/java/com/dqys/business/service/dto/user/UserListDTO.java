package com.dqys.business.service.dto.user;

/**
 * Created by Yvan on 16/6/22.
 * 用户模块用户列表数据传输对象
 *
 * @apiDefine UserList
 * @apiSuccessExample {json} UserList:
 * {
 * id:1,
 * status:1,
 * avg:"http://114.215.239.181:9988/html/jiagoutu.png",
 * sex:1,
 * userName:"userName",
 * realName:"realName",
 * account:"",
 * mobile:"",
 * email:"",
 * area:"",
 * company:"",
 * taskNum:0
 * }
 * git 地址 : http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/user/UserListDTO.java
 */
public class UserListDTO {

    private Integer id; //主键

    private Integer status; // 状态<正常1|禁用2|未激活0|停用3>
    private String avg; // 头像
    private Integer sex; // 性别
    private String userName; // 昵称
    private String realName; // 姓名
    private String account; // 账号
    private String mobile; // 手机号
    private String email; // 邮箱
    private String area; // 行政区域
    private String company; // 公司信息
    private Integer taskNum; // 当前任务数量

    private String apartment; // 部门
    private String occupation; // 职位名称
    private String roleName; // 角色名称
    private String duty; // 职责名称
    private Integer work; // 在职状态
    private Integer onGoingNum; // 正在进行任务数
    private Integer useStatus; // 帐号使用状态（1帐号停用，2帐号禁止登入）

    // 待定二维码<暂未提供>


    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public Integer getWork() {
        return work;
    }

    public void setWork(Integer work) {
        this.work = work;
    }

    public Integer getOnGoingNum() {
        return onGoingNum;
    }

    public void setOnGoingNum(Integer onGoingNum) {
        this.onGoingNum = onGoingNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(Integer taskNum) {
        this.taskNum = taskNum;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
