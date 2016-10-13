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
    private String entrustName; // 委托方名称
    private Integer repayStatus; // 还款状态
    private Integer stopStatus; // 暂停状态1，无效状态2

    private List<Integer> ids; // id集合
    private List<Integer> exceptIds; // id集合之外的id都符合条件

    private Date startAt; // 开始时间
    private Date endAt; // 结束时间
    private Date followDate; // 所属人跟进时间

    private String listSearch; // 列表时搜索条件(模糊查询备注|借款人编号)

    private boolean outTime; // 委托时间是否逾期o
    private boolean over; // 委托时间是否逾期
    private boolean asset; // 资产包
    private boolean notAsset; // 非资产包
    private boolean collection; // 催收
    private boolean agent; // 中介
    private boolean lawyer; // 律所
    private boolean takePart; // 是否其他方参与(处置方任何一方都算)
    private boolean stop = false; // 是否暂停
    private boolean noTakePart; // 暂未其他处置方


    public Integer getStopStatus() {
        return stopStatus;
    }

    public void setStopStatus(Integer stopStatus) {
        this.stopStatus = stopStatus;
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

    public Integer getStateflag() {
        return stateflag;
    }

    public void setStateflag(Integer stateflag) {
        this.stateflag = stateflag;
    }

    public Integer getCanContact() {
        return canContact;
    }

    public void setCanContact(Integer canContact) {
        this.canContact = canContact;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public Integer getIsWorth() {
        return isWorth;
    }

    public void setIsWorth(Integer isWorth) {
        this.isWorth = isWorth;
    }

    public String getEntrustName() {
        return entrustName;
    }

    public void setEntrustName(String entrustName) {
        this.entrustName = entrustName;
    }

    public Integer getRepayStatus() {
        return repayStatus;
    }

    public void setRepayStatus(Integer repayStatus) {
        this.repayStatus = repayStatus;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public List<Integer> getExceptIds() {
        return exceptIds;
    }

    public void setExceptIds(List<Integer> exceptIds) {
        this.exceptIds = exceptIds;
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

    public Date getFollowDate() {
        return followDate;
    }

    public void setFollowDate(Date followDate) {
        this.followDate = followDate;
    }

    public String getListSearch() {
        return listSearch;
    }

    public void setListSearch(String listSearch) {
        this.listSearch = listSearch;
    }

    public boolean isOutTime() {
        return outTime;
    }

    public void setOutTime(boolean outTime) {
        this.outTime = outTime;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public boolean isAsset() {
        return asset;
    }

    public void setAsset(boolean asset) {
        this.asset = asset;
    }

    public boolean isNotAsset() {
        return notAsset;
    }

    public void setNotAsset(boolean notAsset) {
        this.notAsset = notAsset;
    }

    public boolean isCollection() {
        return collection;
    }

    public void setCollection(boolean collection) {
        this.collection = collection;
    }

    public boolean isAgent() {
        return agent;
    }

    public void setAgent(boolean agent) {
        this.agent = agent;
    }

    public boolean isLawyer() {
        return lawyer;
    }

    public void setLawyer(boolean lawyer) {
        this.lawyer = lawyer;
    }

    public boolean isTakePart() {
        return takePart;
    }

    public void setTakePart(boolean takePart) {
        this.takePart = takePart;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public boolean isNoTakePart() {
        return noTakePart;
    }

    public void setNoTakePart(boolean noTakePart) {
        this.noTakePart = noTakePart;
    }
}
