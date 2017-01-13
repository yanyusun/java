package com.dqys.business.orm.query.followUp;

/**
 * Created by yan on 17-1-12.
 */
public class FollowUpSourceQuery {
    private Integer pid;

    private Integer objectType;

    private Integer objectId;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

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
}
