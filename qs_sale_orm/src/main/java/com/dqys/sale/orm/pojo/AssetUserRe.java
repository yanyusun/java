package com.dqys.sale.orm.pojo;

public class AssetUserRe {
    private Integer id;

    private Integer userId;

    private Integer assetId;

    private Integer assetType;

    private Integer isCollection;//是否收藏（0否1是）

    private Integer isDispose;//是否处置（0否1是）

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    public Integer getAssetType() {
        return assetType;
    }

    public void setAssetType(Integer assetType) {
        this.assetType = assetType;
    }

    public Integer getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(Integer isCollection) {
        this.isCollection = isCollection;
    }

    public Integer getIsDispose() {
        return isDispose;
    }

    public void setIsDispose(Integer isDispose) {
        this.isDispose = isDispose;
    }
}