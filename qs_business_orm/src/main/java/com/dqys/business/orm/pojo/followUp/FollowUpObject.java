package com.dqys.business.orm.pojo.followUp;

import java.util.List;

/**
 * Created by mkfeng on 2017/1/17.
 */
public class FollowUpObject {
    private Integer objectType;
    private Integer objectId;
    private Integer secondObjectId;
    private Integer secondObjectType;
    private Integer liquidateStage;
    private Integer secondLiquidateStage;
    private List<FollowUpMessage> followUpMessages;
    private List<FollowUpReadstatus> followUpReadstatuses;

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public List<FollowUpMessage> getFollowUpMessages() {
        return followUpMessages;
    }

    public void setFollowUpMessages(List<FollowUpMessage> followUpMessages) {
        this.followUpMessages = followUpMessages;
    }

    public List<FollowUpReadstatus> getFollowUpReadstatuses() {
        return followUpReadstatuses;
    }

    public void setFollowUpReadstatuses(List<FollowUpReadstatus> followUpReadstatuses) {
        this.followUpReadstatuses = followUpReadstatuses;
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

    public Integer getLiquidateStage() {
        return liquidateStage;
    }

    public void setLiquidateStage(Integer liquidateStage) {
        this.liquidateStage = liquidateStage;
    }

    public Integer getSecondLiquidateStage() {
        return secondLiquidateStage;
    }

    public void setSecondLiquidateStage(Integer secondLiquidateStage) {
        this.secondLiquidateStage = secondLiquidateStage;
    }
}
