package com.dqys.business.orm.pojo.sysNotice;

import com.dqys.core.base.BaseModel;

public class SysNotice extends BaseModel {

    private String title;

    private String content;

    private Integer type;

    private String picname;

    private Integer userId;

    private Integer introduce;

    private String userName;

    private Integer isCover;

    private String mark;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPicname() {
        return picname;
    }

    public void setPicname(String picname) {
        this.picname = picname;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIntroduce() {
        return introduce;
    }

    public void setIntroduce(Integer introduce) {
        this.introduce = introduce;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getIsCover() {
        return isCover;
    }

    public void setIsCover(Integer isCover) {
        this.isCover = isCover;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}