package com.dqys.business.orm.pojo.followUp;

import com.dqys.core.base.BaseModel;

public class FollowUpSource extends BaseModel{
    private Integer id;

    private String pathFilename;

    private String showFilename;

    private Integer followUpMessageId;

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

}