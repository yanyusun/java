package com.dqys.business.orm.pojo.followUp;

import java.util.List;

/**
 * Created by mkfeng on 2017/1/17.
 */
public class FollowUpObject {
    private Integer objectType;
    private Integer objectId;
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
}
