package com.dqys.business.service.query.asset;

import com.dqys.core.base.BasePagination;
import sun.text.normalizer.UBiDiProps;

/**
 * Created by Yvan on 16/7/11.
 */
public class LenderListQuery extends BasePagination {

    private Integer id; // 主键

    private String entrustName; // 委托方
    private String search; // 借款人编号|姓名|电话|注释?
    private String belong; // 所属人
    private Integer urgeType; // 催收阶段
    private Integer outDays; // N天以上未催收借款人
    private String assetNo; // 资产包编号

    private boolean outTime; //逾期维护
    private boolean canContact; // 可联系
    private boolean assigned; // 已分配
    private boolean own; // 我录入
    private boolean asset; // 非资产包借款人
    private boolean passOut; // 转出的
    private boolean passIn; // 转入的
    private boolean worth; // 资不抵债
    private boolean waitAssist; // 待协助的
    private boolean assist; // 正在协助

    private Integer province;//区县省份
    private Integer city;//市
    private Integer area;//区县
    private Double totalMoneyStart;//欠款总额最小值
    private Double totalMoneyEnd;//欠款总额最大值
    private Integer totalPriceSort;//欠款总额（0倒序1正序）
    private Integer overdueSort;//逾期天数大小（0倒序1正序）
    private Integer latelyPulishSort;//最近发布时间（0倒序1正序）

    public boolean getOutTime() {
        return outTime;
    }

    public void setOutTime(boolean outTime) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEntrustName() {
        return entrustName;
    }

    public void setEntrustName(String entrustName) {
        this.entrustName = entrustName;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
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

    public boolean isCanContact() {
        return canContact;
    }

    public void setCanContact(boolean canContact) {
        this.canContact = canContact;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public boolean isOwn() {
        return own;
    }

    public void setOwn(boolean own) {
        this.own = own;
    }

    public boolean isAsset() {
        return asset;
    }

    public void setAsset(boolean asset) {
        this.asset = asset;
    }

    public boolean isPassOut() {
        return passOut;
    }

    public void setPassOut(boolean passOut) {
        this.passOut = passOut;
    }

    public boolean isPassIn() {
        return passIn;
    }

    public void setPassIn(boolean passIn) {
        this.passIn = passIn;
    }

    public boolean isWorth() {
        return worth;
    }

    public void setWorth(boolean worth) {
        this.worth = worth;
    }

    public boolean isWaitAssist() {
        return waitAssist;
    }

    public void setWaitAssist(boolean waitAssist) {
        this.waitAssist = waitAssist;
    }

    public boolean isAssist() {
        return assist;
    }

    public void setAssist(boolean assist) {
        this.assist = assist;
    }
}
