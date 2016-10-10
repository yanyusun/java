package com.dqys.business.orm.pojo.message;

import com.dqys.core.base.BaseQuery;

import java.io.Serializable;

/**
 * 消息
 * Created by mkfeng on 2016/7/8.
 */
public class Message extends BaseQuery implements Serializable {
    private Integer id;//int(11) NOT NULL AUTO_INCREMENT,
    private String title;//varchar(255) DEFAULT NULL COMMENT '标题',
    private String content;//varchar(1024) NOT NULL COMMENT '内容',
    private Integer senderId;//int(11) NOT NULL COMMENT '发送者id',
    private Integer receiveId;//int(11) NOT NULL COMMENT '接受者id\n',
    private String label;//varchar(256) DEFAULT NULL COMMENT '标签',
    private String sendTime;//datetime NOT NULL COMMENT '发送时间',
    private Integer type;//int(2) NOT NULL COMMENT '消息类型(0任务1产品2安全3服务)',
    private Integer status;//int(2) NOT NULL COMMENT '消息状态(0未读1已读2删除)',
    private Integer businessType;//int(2) NOT NULL COMMENT '业务类型：具体就看MessageBTEnum枚举'',
    private String operUrl;//int(2) NOT NULL COMMENT '操作地址'',
    private Integer operStatus;//int(2) NOT NULL COMMENT '操作状态(0未操作1已同意2未同意)',

    public Integer getOperStatus() {
        return operStatus;
    }

    public void setOperStatus(Integer operStatus) {
        this.operStatus = operStatus;
    }

    public String getOperUrl() {
        return operUrl;
    }

    public void setOperUrl(String operUrl) {
        this.operUrl = operUrl;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(Integer receiveId) {
        this.receiveId = receiveId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
