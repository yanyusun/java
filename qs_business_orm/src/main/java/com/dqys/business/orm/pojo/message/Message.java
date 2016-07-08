package com.dqys.business.orm.pojo.message;

import com.dqys.core.base.BasePagination;

import java.io.Serializable;

/**
 * 消息
 * Created by mkfeng on 2016/7/8.
 */
public class Message extends BasePagination implements Serializable {
    private Integer id;//int(11) NOT NULL AUTO_INCREMENT,
    private String title;//varchar(255) DEFAULT NULL COMMENT '标题',
    private String content;//varchar(1024) NOT NULL COMMENT '内容',
    private Integer senderId;//int(11) NOT NULL COMMENT '发送者id',
    private Integer receiveId;//int(11) NOT NULL COMMENT '接受者id\n',
    private String label;//varchar(256) DEFAULT NULL COMMENT '标签',
    private String sendIime;//datetime NOT NULL COMMENT '发送时间',
    private Integer type;//int(2) NOT NULL COMMENT '消息类型(0任务1产品2安全3服务)',
    private Integer status;//int(2) NOT NULL COMMENT '消息状态(0未读1已读2删除)',

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

    public String getSendIime() {
        return sendIime;
    }

    public void setSendIime(String sendIime) {
        this.sendIime = sendIime;
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
