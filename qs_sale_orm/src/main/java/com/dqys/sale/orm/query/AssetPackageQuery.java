package com.dqys.sale.orm.query;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mkfeng on 2016/12/23.
 */
public class AssetPackageQuery extends PageEntity implements Serializable {
    private List<Integer> ids;
    private Integer isHomePage;//是否首页
    private Integer checkStatus;//0待审核1审核未通过2审核已通过
    private Integer enable;//'是否无效0不是,1是
    private Integer businessStatus;//业务状态
    private Integer userId;
    private Integer objectType;

    //-----------------------------------------> 新增
    private Integer assetType;//资产类型（0房产包1小额信贷包2信用卡包3混合包4其它包）

    private Double totalMoneyBegin;//资产总额开始

    private Double totalMoneyEnd;//资产总额结束

    private Integer province;//省',

    private Integer city;//市',

    private Integer area;//区县',


    private Integer entrustType;//委托类型（0企业1个人）

    private Integer isBank;//是否银行类金融机构(0银行类1非银行类）

    private Integer collectionNumSort;//收藏数量 排序 1倒叙  2正叙
    private Integer disposeNumSort;// 申请处置数量 排序 1倒叙  2正叙
    private Integer totalMoneySort;//资产总额 排序 1倒叙  2正叙
    private Integer disposeStatus; //处置状态
    private Integer disposes;//处置方式

    public Integer getTotalMoneySort() {
        return totalMoneySort;
    }

    public void setTotalMoneySort(Integer totalMoneySort) {
        this.totalMoneySort = totalMoneySort;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(Integer businessStatus) {
        this.businessStatus = businessStatus;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getIsHomePage() {
        return isHomePage;
    }

    public void setIsHomePage(Integer isHomePage) {
        this.isHomePage = isHomePage;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public Integer getAssetType() {
        return assetType;
    }

    public void setAssetType(Integer assetType) {
        this.assetType = assetType;
    }

    public Integer getDisposes() {
        return disposes;
    }

    public void setDisposes(Integer disposes) {
        this.disposes = disposes;
    }

    public Integer getDisposeNumSort() {
        return disposeNumSort;
    }

    public void setDisposeNumSort(Integer disposeNumSort) {
        this.disposeNumSort = disposeNumSort;
    }

    public Integer getDisposeStatus() {
        return disposeStatus;
    }

    public void setDisposeStatus(Integer disposeStatus) {
        this.disposeStatus = disposeStatus;
    }

    public Integer getCollectionNumSort() {
        return collectionNumSort;
    }

    public void setCollectionNumSort(Integer collectionNumSort) {
        this.collectionNumSort = collectionNumSort;
    }

    public Integer getIsBank() {
        return isBank;
    }

    public void setIsBank(Integer isBank) {
        this.isBank = isBank;
    }

    public Integer getEntrustType() {
        return entrustType;
    }

    public void setEntrustType(Integer entrustType) {
        this.entrustType = entrustType;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Double getTotalMoneyBegin() {
        return totalMoneyBegin;
    }

    public void setTotalMoneyBegin(Double totalMoneyBegin) {
        this.totalMoneyBegin = totalMoneyBegin;
    }

    public Double getTotalMoneyEnd() {
        return totalMoneyEnd;
    }

    public void setTotalMoneyEnd(Double totalMoneyEnd) {
        this.totalMoneyEnd = totalMoneyEnd;
    }
}
