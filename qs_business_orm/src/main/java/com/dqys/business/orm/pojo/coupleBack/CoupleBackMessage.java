package com.dqys.business.orm.pojo.coupleBack;

import com.dqys.core.base.BaseDTO;

/**
 * Created by mkfeng on 2016/11/16.
 */
public class CoupleBackMessage extends BaseDTO {

    private Integer userId;
    private Integer tcbId;
    private String contentMessage;
    private Integer leaveUserId;
    private Integer messType;

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
