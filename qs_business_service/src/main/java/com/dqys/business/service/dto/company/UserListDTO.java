package com.dqys.business.service.dto.company;

/**
 * Created by Yvan on 16/6/22.
 * 用户模块用户列表数据传输对象
 */
public class UserListDTO {

    private Integer id; //主键
    private String userName; // 昵称
    private String realName; // 姓名
    private Integer sex; // 性别
    private String mobile; // 手机号
    private String email; // 性别
    private String identity; // 身份证
    private Integer status; // 状态<启用1|禁用-1|未激活0>
    private Integer userStatus; // 人员状态(0就职状态中,1已离职)
    private String area; // 行政区域
    private String company; // 公司信息
    private Integer taskNum; // 当前任务数量

    // 待定二维码<暂未提供>


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

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
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
}
