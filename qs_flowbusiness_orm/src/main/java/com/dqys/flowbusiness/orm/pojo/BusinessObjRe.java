package com.dqys.flowbusiness.orm.pojo;

import java.util.Date;

// TODO: 16-9-29 要继承basemode Integer 改为 Long
public class BusinessObjRe {
    private Integer id; // 主键

    private Integer objectId; // 对象ID

    private Integer objectType; // 对象类型

    private Integer businessId; // 业务ID

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

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
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