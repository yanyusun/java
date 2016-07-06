package com.dqys.business.orm.pojo.operLog;

import com.dqys.core.base.BasePagination;

import java.io.Serializable;

/**
 * Created by mkfeng on 2016/6/28.
 */
public class OperLog extends BasePagination implements Serializable {
    private Integer id;
    private String realName;//真实姓名
    private String time;//操作时间
    private String remark;//备注
    private String text;//操作内容
    private Integer objectType;//被操作对象类型
    private String objectName;//被操作对象名称
    private Integer objectId;//被操作对象id
    private Integer operType;//操作类型
    private String operName;//操作类型名称
    private Integer userId;//操作人员id
    private String startTime;//开始时间
    private String endTime;//结束时间

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
