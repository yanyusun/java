package com.dqys.business.orm.pojo.company;

import com.dqys.core.base.BaseModel;

/**
 * 导航栏对象
 */
public class Navigation extends BaseModel {

    private Integer id; // 主键
    private String value; // 值
    private String name; // 名称
    private Integer sort; // 排序
    private String icon; // 小图标
    private Integer pid; // 父级ID

    private Boolean child; // 子导航

    private Boolean manager; // 管理员
    private Boolean governor; // 管理者
    private Boolean employee; // 普通用户

    private Boolean platform; // 平台
    private Boolean personal; // 个人
    private Boolean entrust; // 委托方
    private Boolean collection; // 催收
    private Boolean agent; // 中介
    private Boolean law; // 律所


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean getChild() {
        return child;
    }

    public void setChild(Boolean child) {
        this.child = child;
    }
}