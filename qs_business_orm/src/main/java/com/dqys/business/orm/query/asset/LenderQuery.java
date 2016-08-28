package com.dqys.business.orm.query.asset;

import com.dqys.core.base.BaseQuery;

import java.util.Date;
import java.util.List;

/**
 * Created by Yvan on 16/6/8.
 */
public class LenderQuery extends BaseQuery {

    private Integer lenderId; // 借款人ID
    private Integer assetId; // 资产包ID
    private Integer stateflag; // 数据状态

    private Integer canContact; // 可联系
    private Integer operator; // 操作人
    private Integer isWorth; // 资不抵债
    private Integer entrustId; // 委托来源
    private Integer repayStatus; // 还款状态

    private List<Integer> ids; // id集合
    private List<Integer> exceptIds; // id集合之外的id都符合条件

    private Date startAt; // 开始时间
    private Date endAt; // 结束时间
    private Date belongFollowDate; // 所属人跟进时间

    private String listSearch; // 列表时搜索条件(模糊查询备注|借款人编号)

    private boolean isOutTime; // 委托时间是否逾期
    private boolean isOver; // 委托时间是否逾期
    private boolean isAsset; // 资产包
    private boolean isNotAsset; // 非资产包
    private boolean isCollection; // 催收
    private boolean isAgent; // 中介
    private boolean isLawyer; // 律所
    private boolean isTakePart; // 是否其他方参与(处置方任何一方都算)


    public Integer getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(Integer entrustId) {
        this.entrustId = entrustId;
    }

    public Integer getIsWorth() {
        return isWorth;
    }

    public void setIsWorth(Integer isWorth) {
        this.isWorth = isWorth;
    }

    public boolean isAsset() {
        return isAsset;
    }

    public void setIsAsset(boolean isAsset) {
        this.isAsset = isAsset;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public Integer getLenderId() {
        return lenderId;
    }

    public void setLenderId(Integer lenderId) {
        this.lenderId = lenderId;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
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

    public boolean isOutTime() {
        return isOutTime;
    }

    public void setIsOutTime(boolean isOutTime) {
        this.isOutTime = isOutTime;
    }

    public Integer getStateflag() {
        return stateflag;
    }

    public void setStateflag(Integer stateflag) {
        this.stateflag = stateflag;
    }

    public boolean isOver() {
        return isOver;
    }

    public void setIsOver(boolean isOver) {
        this.isOver = isOver;
    }

    public List<Integer> getExceptIds() {
        return exceptIds;
    }

    public void setExceptIds(List<Integer> exceptIds) {
        this.exceptIds = exceptIds;
    }

    public Integer getCanContact() {
        return canContact;
    }

    public void setCanContact(Integer canContact) {
        this.canContact = canContact;
    }

    public boolean isNotAsset() {
        return isNotAsset;
    }

    public void setIsNotAsset(boolean isNotAsset) {
        this.isNotAsset = isNotAsset;
    }

    public boolean isTakePart() {
        return isTakePart;
    }

    public void setIsTakePart(boolean isTakePart) {
        this.isTakePart = isTakePart;
    }

    public boolean isCollection() {
        return isCollection;
    }

    public void setIsCollection(boolean isCollection) {
        this.isCollection = isCollection;
    }

    public boolean isAgent() {
        return isAgent;
    }

    public void setIsAgent(boolean isAgent) {
        this.isAgent = isAgent;
    }

    public boolean isLawyer() {
        return isLawyer;
    }

    public void setIsLawyer(boolean isLawyer) {
        this.isLawyer = isLawyer;
    }

    public String getListSearch() {
        return listSearch;
    }

    public void setListSearch(String listSearch) {
        this.listSearch = listSearch;
    }

    public Date getBelongFollowDate() {
        return belongFollowDate;
    }

    public void setBelongFollowDate(Date belongFollowDate) {
        this.belongFollowDate = belongFollowDate;
    }

    public Integer getRepayStatus() {
        return repayStatus;
    }

    public void setRepayStatus(Integer repayStatus) {
        this.repayStatus = repayStatus;
    }
}
