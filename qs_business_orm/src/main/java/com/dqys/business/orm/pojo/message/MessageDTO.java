package com.dqys.business.orm.pojo.message;

/**
 * Created by mkfeng on 2016/7/11.
 */
public class MessageDTO {
    private Integer id;
    private String title;
    private String contant;
    private String sendTime;
    private String typeName;
    private Integer status;
    private String label;
    private Integer businessType;//int(2) NOT NULL COMMENT '业务类型：具体就看MessageBTEnum枚举'',
    private String operUrl;//int(2) NOT NULL COMMENT '操作地址'',;
    private Integer operStatus;//int(2) NOT NULL COMMENT '操作状态(0未操作1已同意2未同意)',
    private String operUserName;//操作人姓名
    private Integer messageNo;//操作人编号

    public String getOperUserName() {
        return operUserName;
    }

    public void setOperUserName(String operUserName) {
        this.operUserName = operUserName;
    }

    public Integer getMessageNo() {
        return messageNo;
    }

    public void setMessageNo(Integer messageNo) {
        this.messageNo = messageNo;
    }

    public Integer getOperStatus() {
        return operStatus;
    }

    public void setOperStatus(Integer operStatus) {
        this.operStatus = operStatus;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getOperUrl() {
        return operUrl;
    }

    public void setOperUrl(String operUrl) {
        this.operUrl = operUrl;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getContant() {
        return contant;
    }

    public void setContant(String contant) {
        this.contant = contant;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
