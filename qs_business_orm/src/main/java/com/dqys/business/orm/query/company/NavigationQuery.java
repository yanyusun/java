package com.dqys.business.orm.query.company;

public class NavigationQuery {

    private Integer id; // 主键
    private Integer pid; // 父级ID
    private Boolean manager; // 管理员
    private Boolean governor; // 管理者
    private Boolean employee; // 普通用户
//    private Boolean platform; // 平台
//    private Boolean personal; // 个人
//    private Boolean entrust; // 委托方
//    private Boolean collection; // 催收
//    private Boolean agent; // 中介
//    private Boolean law; // 律所
    private String type; // 用户类型(委托，平台，催收，律所，中介)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Boolean getManager() {
        return manager;
    }

    public void setManager(Boolean manager) {
        this.manager = manager;
    }

    public Boolean getGovernor() {
        return governor;
    }

    public void setGovernor(Boolean governor) {
        this.governor = governor;
    }

    public Boolean getEmployee() {
        return employee;
    }

    public void setEmployee(Boolean employee) {
        this.employee = employee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}