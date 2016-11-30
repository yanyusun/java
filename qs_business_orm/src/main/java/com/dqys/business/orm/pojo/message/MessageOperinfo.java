package com.dqys.business.orm.pojo.message;

/**
 * Created by mkfeng on 2016/11/30.
 */
public class MessageOperinfo {
    private Integer userId;//int(11) DEFAULT NULL COMMENT '当前操作者id',
    private Integer operStatus;//int(11) DEFAULT NULL COMMENT '用来描述当很多人同时操作时，短信的所属操作状态：0未操作1已同意2未同意',
    private Integer messageMo;//int(20) NOT NULL COMMENT '统一业务短信编号',

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOperStatus() {
        return operStatus;
    }

    public void setOperStatus(Integer operStatus) {
        this.operStatus = operStatus;
    }

    public Integer getMessageMo() {
        return messageMo;
    }

    public void setMessageMo(Integer messageMo) {
        this.messageMo = messageMo;
    }
}
