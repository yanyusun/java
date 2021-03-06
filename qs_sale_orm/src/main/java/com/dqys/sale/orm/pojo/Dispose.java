package com.dqys.sale.orm.pojo;

public class Dispose {
    private Integer id;

    private Integer disposeType;//处置方式类型

    private Integer assetId;

    private Integer assetType;

    private Integer alg;//算法（比如1为%）

    private String value;//数值

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDisposeType() {
        return disposeType;
    }

    public void setDisposeType(Integer disposeType) {
        this.disposeType = disposeType;
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

    public Integer getAlg() {
        return alg;
    }

    public void setAlg(Integer alg) {
        this.alg = alg;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}