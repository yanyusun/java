package com.dqys.business.orm.pojo.coupleBack;

import com.dqys.core.base.BaseDTO;

/**
 * Created by mkfeng on| 2016/11/16.
 *
 * @apiDefine CoupleBackMessage
 * @apiParam {int} tcbId 反馈信息id
 * @apiParam {string} contentMessage 内容
 * @apiParam {int} leaveUserId 被回复用户id
 * @apiParam {string} remark 备注
 */
public class CoupleBackMessage extends BaseDTO {

    private Integer userId;//添加消息的当前用户
    private Integer tcbId;//t_couple_back 表id
    private String contentMessage;//内容
    private Integer leaveUserId;//回复留言者用户'
    private Integer messType;//信息类型（0主动留言1回复留言）

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
