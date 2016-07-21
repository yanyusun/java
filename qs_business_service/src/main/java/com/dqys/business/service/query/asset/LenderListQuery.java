package com.dqys.business.service.query.asset;

import com.dqys.core.base.BasePagination;

/**
 * Created by Yvan on 16/7/11.
 * @apiDefine LenderListQuery
 * @apiParam {number} id 主键
 * @apiParam {number} entrustId 委托方
 * @apiParam {string} search 搜索(客户编号|姓名|电话|注释?)
 * @apiParam {string} urgeType 催收阶段
 * @apiParam {string} assetNo 资产包编号
 * @apiParam {number} belong 所属人
 * @apiParam {number} outDays N天以上未催收
 * @apiParam {number} isOutTime 逾期维护
 * @apiParam {number} canContact 可联系
 * @apiParam {number} isAssigned 已分配
 * @apiParam {number} isOwn 自己录入
 * @apiParam {number} isAsset 非资产包
 * @apiParam {number} passOut 转出
 * @apiParam {number} passIn 转入
 * @apiParam {number} isWorth 资不抵债
 * @apiParam {number} waitAssist 转协助的
 * @apiParam {number} assist 正在协助
 */
public class LenderListQuery extends BasePagination{

    private Integer id; // 主键

    private Integer entrustId; // 委托方
    private String search; // 客户编号|姓名|电话|注释?
    private Integer belong; // 所属人
    private Integer urgeType; // 催收阶段
    private Integer outDays; // N天以上未催收借款人
    private String assetNo; // 资产包编号

    private Integer isOutTime; //逾期维护
    private Integer canContact; // 可联系
    private Integer isAssigned; // 已分配
    private Integer isOwn; // 我录入
    private Integer isAsset; // 非资产包借款人
    private Integer passOut; // 转出的
    private Integer passIn; // 转入的
    private Integer isWorth; // 资不抵债
    private Integer waitAssist; // 待协助的
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

    public Integer getBelong() {
        return belong;
    }

    public void setBelong(Integer belong) {
        this.belong = belong;
    }

    public Integer getUrgeType() {
        return urgeType;
    }

    public void setUrgeType(Integer urgeType) {
        this.urgeType = urgeType;
    }

    public Integer getOutDays() {
        return outDays;
    }

    public void setOutDays(Integer outDays) {
        this.outDays = outDays;
    }

    public String getAssetNo() {
        return assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
    }

    public Integer getIsOutTime() {
        return isOutTime;
    }

    public void setIsOutTime(Integer isOutTime) {
        this.isOutTime = isOutTime;
    }

    public Integer getCanContact() {
        return canContact;
    }

    public void setCanContact(Integer canContact) {
        this.canContact = canContact;
    }

    public Integer getIsAssigned() {
        return isAssigned;
    }

    public void setIsAssigned(Integer isAssigned) {
        this.isAssigned = isAssigned;
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

    public Integer getIsWorth() {
        return isWorth;
    }

    public void setIsWorth(Integer isWorth) {
        this.isWorth = isWorth;
    }

    public Integer getWaitAssist() {
        return waitAssist;
    }

    public void setWaitAssist(Integer waitAssist) {
        this.waitAssist = waitAssist;
    }

    public Integer getAssist() {
        return assist;
    }

    public void setAssist(Integer assist) {
        this.assist = assist;
    }
}
