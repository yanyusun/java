package com.dqys.business.orm.query.business;

import com.dqys.core.base.BaseQuery;

import java.util.Date;
import java.util.List;

/**
 * Created by yan on 16-7-16.
 *
 * @apiDefine ObjectUserRelationQuery
 * @apiParam {number} [id] id
 * @apiParam {number} [objectType] 对象类型
 * @apiParam {number} [operType] 操作类型
 * @apiParam {number} [userId] 操作人员id
 * @apiParam {number} [employerId] 协作团队
 * @apiParam {number} [type] 协作类型
 * @apiParam {number} [businessId] 业务号
 * @apiParam {boolean} [isPaging] 是否分页0分页，1不分页
 * @apiParam {number} [startPageNum] 当前分页
 * @apiParam {number} [pageSize] 分页大小
 * @apiParam {date} [createAt] 创建时间
 */
public class ObjectUserRelationQuery extends BaseQuery {
    private Integer id; // 主键ID

    private Integer objectType; // 对象类型

    private Integer objectId; // 对象ID

    private Integer userId; // 操作人Id

    private Integer status; // 状态(1待接收；2带审核;3已跟进)

    private Integer type; // 关联类型:0自己分配;1团队分配;2公司分配

    private Integer employerId; //关联对象id:t_inside_team的id或者t_outside_team的id

    private Integer businessId; // 业务ID

    private Date startAt; // 开始时间
    private Date endAt; // 结束时间

    private List<Integer> userIds; // 操作人集合

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
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

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }
}
