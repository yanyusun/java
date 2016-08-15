package com.dqys.business.orm.query.followUp;

import com.dqys.core.base.BaseQuery;

/**
 * Created by yan on 16-8-15.
 *
 * @apiDefine FollowUpMessageQuery
 * @apiParam {number} [id] id
 * @apiParam {number} [objectId] 对象id
 * @apiParam {number} [objectType] 对象类型
 * @apiParam {number} [teamId] 团队id
 * @apiParam {string} [content] 内容
 * @apiParam {number} [secondObjectId] 二级对象id
 * @apiParam {number} [secondObjectType] 二级对象类型
 * @apiParam {number} [liquidateStage] 请搜阶段
 * @apiParam {number} [secondLiquidateStage] 二级清收阶段
 * @apiParam {number} [sendStatus] 发送状态
 * @apiParam {boolean} [isPaging] 是否分页0分页，1不分页
 * @apiParam {number} [startPageNum] 当前分页
 * @apiParam {number} [pageSize] 分页大小
 * @apiParam {date} [createAt] 创建时间
 */

public class FollowUpMessageQuery extends BaseQuery {

    private Integer id;

    private Integer objectId;

    private Integer objectType;

    private Integer teamId;

    private String content;

    private Integer secondObjectId;

    private Integer secondObjectType;

    private Integer liquidateStage;

    private Integer secondLiquidateStage;

    private Integer sendStatus;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
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

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSecondObjectId() {
        return secondObjectId;
    }

    public void setSecondObjectId(Integer secondObjectId) {
        this.secondObjectId = secondObjectId;
    }

    public Integer getSecondObjectType() {
        return secondObjectType;
    }

    public void setSecondObjectType(Integer secondObjectType) {
        this.secondObjectType = secondObjectType;
    }

    public Integer getLiquidateStage() {
        return liquidateStage;
    }

    public void setLiquidateStage(Integer liquidateStage) {
        this.liquidateStage = liquidateStage;
    }

    public Integer getSecondLiquidateStage() {
        return secondLiquidateStage;
    }

    public void setSecondLiquidateStage(Integer secondLiquidateStage) {
        this.secondLiquidateStage = secondLiquidateStage;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }
}
