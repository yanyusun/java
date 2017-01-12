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
    private List<Integer> assetIds; // 资产包ID
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

    private Boolean outTime = false; // 委托时间是否逾期o
    private boolean over; // 委托时间是否逾期
    private boolean asset; // 资产包
    private boolean notAsset; // 非资产包
    private boolean collection; // 催收
    private boolean agent; // 中介
    private boolean lawyer; // 律所
    private boolean takePart; // 是否其他方参与(处置方任何一方都算)
    private boolean stop = false; // 是否暂停
    private boolean noTakePart; // 暂未其他处置方

    private Integer province;//区县省份
    private Integer city;//市
    private Integer area;//区县
    private Double totalMoneyStart;//欠款总额最小值
    private Double totalMoneyEnd;//欠款总额最大值
    private Integer totalPriceSort;//欠款总额（0倒序1正序）
    private Integer overdueSort;//逾期天数大小（0倒序1正序）
    private Integer latelyPulishSort;//最近发布时间（0倒序1正序）

    public Boolean getOutTime() {
        return outTime;
    }

    public void setOutTime(Boolean outTime) {
        this.outTime = outTime;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Double getTotalMoneyStart() {
        return totalMoneyStart;
    }

    public void setTotalMoneyStart(Double totalMoneyStart) {
        this.totalMoneyStart = totalMoneyStart;
    }

    public Double getTotalMoneyEnd() {
        return totalMoneyEnd;
    }

    public void setTotalMoneyEnd(Double totalMoneyEnd) {
        this.totalMoneyEnd = totalMoneyEnd;
    }

    public Integer getTotalPriceSort() {
        return totalPriceSort;
    }

    public void setTotalPriceSort(Integer totalPriceSort) {
        this.totalPriceSort = totalPriceSort;
    }


    public Integer getOverdueSort() {
        return overdueSort;
    }

    public void setOverdueSort(Integer overdueSort) {
        this.overdueSort = overdueSort;
    }

    public Integer getLatelyPulishSort() {
        return latelyPulishSort;
    }

    public void setLatelyPulishSort(Integer latelyPulishSort) {
        this.latelyPulishSort = latelyPulishSort;
    }

    public List<Integer> getAssetIds() {
        return assetIds;
    }

    public void setAssetIds(List<Integer> assetIds) {
        this.assetIds = assetIds;
    }

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
