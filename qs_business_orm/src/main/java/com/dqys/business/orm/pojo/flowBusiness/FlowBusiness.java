package com.dqys.business.orm.pojo.flowBusiness;

import com.dqys.core.base.BaseModel;

import java.io.Serializable;

/**
 * 业务流转业务状态
 * Created by mkfeng on 2016/12/2.
 */
public class FlowBusiness extends BaseModel implements Serializable {
    private Integer objectId;//int(11) NOT NULL COMMENT '对象id',
    private Integer objectType;//int(10) NOT NULL COMMENT '对象类型:抵押物，借据',
    private Integer status;//int(11) NOT NULL DEFAULT '0' COMMENT '状态：0平台未同意1平台已同意100完成',
    private Integer createUserId;//int(11) NOT NULL COMMENT '创建者id',
    private Integer type;//int(11) NOT NULL COMMENT '业务类型:即业务流转的所有类型，值相同',
    private Integer collection;//int(11) DEFAULT '0' COMMENT '催收状态:0待添加,1待同意,2已同意,3已拒绝,-1已有该机构',
    private Integer lawyer;//int(11) DEFAULT '0' COMMENT '司法状态:0待添加,1待同意,2已同意,3已拒绝,-1已有该机构',
    private Integer agent;//int(11) DEFAULT '0' COMMENT '中介状态:0待添加,1待同意,2已同意,3已拒绝,-1已有该机构',


    public FlowBusiness(Integer objectId, Integer objectType, Integer status, Integer createUserId, Integer type, Integer collection, Integer lawyer, Integer agent) {
        this.objectId = objectId;
        this.objectType = objectType;
        this.status = status;
        this.createUserId = createUserId;
        this.type = type;
        this.collection = collection;
        this.lawyer = lawyer;
        this.agent = agent;
    }

    public FlowBusiness() {
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCollection() {
        return collection;
    }

    public void setCollection(Integer collection) {
        this.collection = collection;
    }

    public Integer getLawyer() {
        return lawyer;
    }

    public void setLawyer(Integer lawyer) {
        this.lawyer = lawyer;
    }

    public Integer getAgent() {
        return agent;
    }

    public void setAgent(Integer agent) {
        this.agent = agent;
    }
}
