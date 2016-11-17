package com.dqys.business.orm.pojo.coupleBack.dto;

import com.dqys.core.base.BaseDTO;

/**
 * Created by mkfeng on 2016/11/16.
 */
public class CoupleBackMessageDTO {

    private Integer userId;
    private Integer tcbId;
    private String contentMessage;//内容
    private Integer leaveUserId;
    private Integer messType;
    private String createTime;
    private String leaveUserName;//被回复的用户名
    private String userName;//回复者用户名
    private String leaveRealName;//被回复的真实姓名
    private String realName;//回复者真实姓名

    public String getLeaveRealName() {
        return leaveRealName;
    }

    public void setLeaveRealName(String leaveRealName) {
        this.leaveRealName = leaveRealName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLeaveUserName() {
        return leaveUserName;
    }

    public void setLeaveUserName(String leaveUserName) {
        this.leaveUserName = leaveUserName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getMessType() {
        return messType;
    }

    public void setMessType(Integer messType) {
        this.messType = messType;
    }

    public Integer getLeaveUserId() {
        return leaveUserId;
    }

    public void setLeaveUserId(Integer leaveUserId) {
        this.leaveUserId = leaveUserId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTcbId() {
        return tcbId;
    }

    public void setTcbId(Integer tcbId) {
        this.tcbId = tcbId;
    }

    public String getContentMessage() {
        return contentMessage;
    }

    public void setContentMessage(String contentMessage) {
        this.contentMessage = contentMessage;
    }
}
