package com.dqys.business.service.query.asset;

import com.dqys.core.base.BasePagination;

/**
 * Created by Yvan on 16/7/11.
 * @apiDefine LenderListQuery
 * @apiParam {number} id 主键
 * @apiParam {number} entrustId 委托方
 * @apiParam {string} search 搜索(客户编号|姓名|电话|注释?)
 * @apiParam {string} urgeType 催收阶段
 * @apiParam {number} canContact 可联系
 * @apiParam {number} isOwn 自己录入
 * @apiParam {number} isAsset 非资产包
 * @apiParam {number} isWorth 资不抵债
 * @apiParam {number} managerId 所属人
 * @apiParam {number} outDay 超过指定天数
 * @apiParam {number} isOutTime 逾期维护
 * @apiParam {number} isAssigned 已分配
 * @apiParam {number} passOut 转出
 * @apiParam {number} passIn 转入
 * @apiParam {number} passAssist 转协助的
 * @apiParam {number} assist 正在协助
 */
public class LenderListQuery extends BasePagination{

    private Integer id; // 主键
    private Integer entrustId; // 委托方
    private String search; // 客户编号|姓名|电话|注释?
    private String urgeType; // 催收阶段
    private Integer canContact; // 是否可联系
    private Integer isOwn; // 是否自己录入
    private Integer isAsset; // 非资产包借款人
    private Integer isWorth; // 是否资不抵债
    // todo 所属人,协作器
    private Integer managerId; // 所属人
    private Integer outDay; // 跟进天数
    private Integer isOutTime; //是否逾期维护
    private Integer isAssigned; // 是否分配
    private Integer passOut; // 转出的
    private Integer passIn; // 转入的
    private Integer passAssist; // 转协助的
    private Integer assist; // 正在协助

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(Integer entrustId) {
        this.entrustId = entrustId;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getUrgeType() {
        return urgeType;
    }

    public void setUrgeType(String urgeType) {
        this.urgeType = urgeType;
    }

    public Integer getCanContact() {
        return canContact;
    }

    public void setCanContact(Integer canContact) {
        this.canContact = canContact;
    }

    public Integer getIsOwn() {
        return isOwn;
    }

    public void setIsOwn(Integer isOwn) {
        this.isOwn = isOwn;
    }

    public Integer getIsAsset() {
        return isAsset;
    }

    public void setIsAsset(Integer isAsset) {
        this.isAsset = isAsset;
    }

    public Integer getIsWorth() {
        return isWorth;
    }

    public void setIsWorth(Integer isWorth) {
        this.isWorth = isWorth;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getOutDay() {
        return outDay;
    }

    public void setOutDay(Integer outDay) {
        this.outDay = outDay;
    }

    public Integer getIsOutTime() {
        return isOutTime;
    }

    public void setIsOutTime(Integer isOutTime) {
        this.isOutTime = isOutTime;
    }

    public Integer getIsAssigned() {
        return isAssigned;
    }

    public void setIsAssigned(Integer isAssigned) {
        this.isAssigned = isAssigned;
    }

    public Integer getPassOut() {
        return passOut;
    }

    public void setPassOut(Integer passOut) {
        this.passOut = passOut;
    }

    public Integer getPassIn() {
        return passIn;
    }

    public void setPassIn(Integer passIn) {
        this.passIn = passIn;
    }

    public Integer getPassAssist() {
        return passAssist;
    }

    public void setPassAssist(Integer passAssist) {
        this.passAssist = passAssist;
    }

    public Integer getAssist() {
        return assist;
    }

    public void setAssist(Integer assist) {
        this.assist = assist;
    }
}
