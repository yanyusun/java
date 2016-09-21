package com.dqys.business.orm.pojo.business;

import java.util.Date;

public class ObjectUserRelation {
    private Integer id; // 主键ID

    private Integer objectType; // 对象类型

    private Integer objectId; // 对象ID

    private Integer userId; // 操作人Id

    private Integer status; // 状态(1待接收；2已拒绝;3已跟进；0已接受)

    private Integer type; // 关联类型:0自己分配;1团队分配;2公司分配

    private Integer employerId; //关联对象id:t_inside_team的id或者t_outside_team的id

    private Integer businessId; // 业务ID

    private Long stateflag; // 数据状态

    private Date createAt; // 创建时间

    private Date updateAt; // 修改时间

    private Integer visibleType;//可见类型:0该对象可见该对象关联的对象也可见1该对象可见

    public Integer getVisibleType() {
        return visibleType;
    }

    public void setVisibleType(Integer visibleType) {
        this.visibleType = visibleType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Long getStateflag() {
        return stateflag;
    }

    public void setStateflag(Long stateflag) {
        this.stateflag = stateflag;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}