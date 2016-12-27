package com.dqys.sale.orm.pojo;

public class UserBusTotal {
    private Integer hasPublish;//已发布数量

    private Integer onCollection;//已收藏数量

    private Integer onBusiness;//正在处置数量

    private Integer id;

    private Integer userId;

    public Integer getHasPublish() {
        return hasPublish;
    }

    public void setHasPublish(Integer hasPublish) {
        this.hasPublish = hasPublish;
    }

    public Integer getOnCollection() {
        return onCollection;
    }

    public void setOnCollection(Integer onCollection) {
        this.onCollection = onCollection;
    }

    public Integer getOnBusiness() {
        return onBusiness;
    }

    public void setOnBusiness(Integer onBusiness) {
        this.onBusiness = onBusiness;
    }

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
}