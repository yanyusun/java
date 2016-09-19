package com.dqys.business.service.dto.company;

import java.util.Date;

/**
 * Created by Yvan on 16/7/14.
 * 分配器成员
 * @apiDefine CompanyTeamReDTO
 * @apiSuccessExample {json} CompanyTeamReDTO:
 * {
 *
 * }
 * git 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/company/CompanyTeamReDTO.java
 */
public class CompanyTeamReDTO {

    private Integer id; // 主键
    private String avg; // 头像
    private String type; // 参与方类型
    private String address; // 地址
    private String rate; // 业绩比率
    private Integer task; // 正在进行的任务
    private String contact; // 联系人
    private Integer status; // 接受状态
    private Date time; // 接收时间
    private Integer stateflag; // 数据状态

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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
