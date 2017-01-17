package com.dqys.business.orm.pojo.followUp;

import com.dqys.core.base.BaseModel;

import java.util.Date;

public class FollowUpReadstatus extends BaseModel  {
    private Integer id;

    private Integer userId;

    private Integer objectId;

    private Integer objectType;

    private Integer moment;

    private Integer secondObjectId;

    private Integer secondObjectType;

    private Integer secondLiquidateStage;

    private Integer version;

    private Date createAt;

    private Date updateAt;

    private Long stateflag;

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

    public Integer getMoment() {
        return moment;
    }

    public void setMoment(Integer moment) {
        this.moment = moment;
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

    public Long getStateflag() {
        return stateflag;
    }

    public void setStateflag(Long stateflag) {
        this.stateflag = stateflag;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSecondObjectId() {
        return secondObjectId;
    }

    public void setSecondObjectId(Integer secondObjectId) {
        this.secondObjectId = secondObjectId;
    }

    public Integer getSecondObjectType() {
        return secondObjectType;
    }

    public void setSecondObjectType(Integer secondObjectType) {
        this.secondObjectType = secondObjectType;
    }

    public Integer getSecondLiquidateStage() {
        return secondLiquidateStage;
    }

    public void setSecondLiquidateStage(Integer secondLiquidateStage) {
        this.secondLiquidateStage = secondLiquidateStage;
    }
}