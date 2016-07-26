package com.dqys.business.orm.query.company;

public class NavigationQuery {

    private Integer id; // 主键
    private Integer pid; // 父级ID
    private Boolean manager; // 管理员
    private Boolean governor; // 管理者
    private Boolean employee; // 普通用户
    private Boolean platform; // 平台
    private Boolean personal; // 个人
    private Boolean entrust; // 委托方
    private Boolean collection; // 催收
    private Boolean agent; // 中介
    private Boolean law; // 律所

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

    public Boolean getPlatform() {
        return platform;
    }

    public void setPlatform(Boolean platform) {
        this.platform = platform;
    }

    public Boolean getPersonal() {
        return personal;
    }

    public void setPersonal(Boolean personal) {
        this.personal = personal;
    }

    public Boolean getEntrust() {
        return entrust;
    }

    public void setEntrust(Boolean entrust) {
        this.entrust = entrust;
    }

    public Boolean getCollection() {
        return collection;
    }

    public void setCollection(Boolean collection) {
        this.collection = collection;
    }

    public Boolean getAgent() {
        return agent;
    }

    public void setAgent(Boolean agent) {
        this.agent = agent;
    }

    public Boolean getLaw() {
        return law;
    }

    public void setLaw(Boolean law) {
        this.law = law;
    }
}