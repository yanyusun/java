package com.dqys.business.orm.query.businessLog;

import com.dqys.core.base.BaseQuery;

/**
 * Created by yan on 16-7-13.
 *
 * @apiDefine BusinessLogListQuery
 * @apiParam {number} [id] id
 * @apiParam {number} [objectType] 对象类型
 * @apiParam {number} [operType] 操作类型
 * @apiParam {number} [userId] 操作人员id
 * @apiParam {number} [teamId] 协作团队
 * @apiParam {number} [businessId] 业务号
 * @apiParam {boolean} [isPaging] 是否分页0分页，1不分页
 * @apiParam {number} [startPageNum] 当前分页
 * @apiParam {number} [pageSize] 分页大小
 * @apiParam {date} [createAt] 创建时间
 */

public class BusinessLogQuery extends BaseQuery {
    private Integer id;

    private Integer objectType;

    private Integer objectId;

    private Integer operType;

    private Integer userId;

    private Integer teamId;

    private Integer businessId;

    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
