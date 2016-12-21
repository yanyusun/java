package com.dqys.flowbusiness.orm.pojo;

import java.util.Date;

// TODO: 16-9-29 要继承basemode Integer 改为 Long
public class Business {
    private Integer id; // 主键

    private Integer status; // 状态

    private Integer type; // 类型

    private Integer createId; // 创建者iD

    private Integer version; // 版本

    private Date createAt; // 创建时间

    private Date updateAt; // 修改时间

    private Integer stateflag; // 数据状态

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Integer getStateflag() {
        return stateflag;
    }

    public void setStateflag(Integer stateflag) {
        this.stateflag = stateflag;
    }
}