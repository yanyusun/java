package com.dqys.business.service.dto.company;

import java.util.Date;

/**
 * Created by Yvan on 16/9/11.
 * 分配器的业务流转对象
 */
public class BusinessServiceDTO {

    private Integer id; // 主键
    private String avg; // 头像
    private String type; // 参与方类型
    private String name; // 参与方名称
    private String address; // 地址
    private String rate; // 业绩比率
    private Integer task; // 正在进行的任务
    private String target; // 针对对象
    private Date time; // 接收时间
    private Integer stateflag; // 数据状态

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Integer getTask() {
        return task;
    }

    public void setTask(Integer task) {
        this.task = task;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getStateflag() {
        return stateflag;
    }

    public void setStateflag(Integer stateflag) {
        this.stateflag = stateflag;
    }
}
