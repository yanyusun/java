package com.dqys.sale.service.dto;

import com.dqys.sale.orm.pojo.*;

import java.util.List;

/**
 * 资产包
 * Created by mkfeng on 2016/12/22.
 */
public class AssetPackageDTO {
    private AssetPackage assetPackage;
    private APDto apDto;
    private List<Label> labels;//标签
    private List<Dispose> disposes;//处置方式
    private List<AssetFile> assetFiles;//文件
    private BusinessORelation oRelation;//业务与对象关系表

    public BusinessORelation getoRelation() {
        return oRelation;
    }

    public void setoRelation(BusinessORelation oRelation) {
        this.oRelation = oRelation;
    }

    public APDto getApDto() {
        return apDto;
    }

    public void setApDto(APDto apDto) {
        this.apDto = apDto;
    }

    public AssetPackage getAssetPackage() {
        return assetPackage;
    }

    public void setAssetPackage(AssetPackage assetPackage) {
        this.assetPackage = assetPackage;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public List<Dispose> getDisposes() {
        return disposes;
    }

    public void setDisposes(List<Dispose> disposes) {
        this.disposes = disposes;
    }

    public List<AssetFile> getAssetFiles() {
        return assetFiles;
    }

    public void setAssetFiles(List<AssetFile> assetFiles) {
        this.assetFiles = assetFiles;
    }
}
