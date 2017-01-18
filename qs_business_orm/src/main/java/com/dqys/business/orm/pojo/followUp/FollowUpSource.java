package com.dqys.business.orm.pojo.followUp;

import com.dqys.core.base.BaseModel;

public class FollowUpSource extends BaseModel{
    private Integer id;

    private String pathFilename;

    private String showFilename;

    private Integer followUpMessageId;

    private Integer type;

    private Integer objectType;

    private Integer objectId;

    private Integer pid;

    private Integer userId;

    private String userName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPathFilename() {
        return pathFilename;
    }

    public void setPathFilename(String pathFilename) {
        this.pathFilename = pathFilename;
    }

    public String getShowFilename() {
        return showFilename;
    }

    public void setShowFilename(String showFilename) {
        this.showFilename = showFilename;
    }

    public Integer getFollowUpMessageId() {
        return followUpMessageId;
    }

    public void setFollowUpMessageId(Integer followUpMessageId) {
        this.followUpMessageId = followUpMessageId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}