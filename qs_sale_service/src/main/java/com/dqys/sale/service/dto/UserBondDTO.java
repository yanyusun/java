package com.dqys.sale.service.dto;

import com.dqys.sale.orm.pojo.*;

import java.util.List;

/**
 * 债权
 * Created by mkfeng on 2016/12/22.
 */
public class UserBondDTO {
    private UserBond userBond;//债权
    private UBDto ubDto;
    private List<Label> labels;//标签
    private List<Dispose> disposes;//处置方式
    private List<AssetFile> assetFiles;//文件
    private List<Business> business;//业务表
    private AssetUserRe assetUserRe;//收藏处置对象

    public AssetUserRe getAssetUserRe() {
        return assetUserRe;
    }

    public void setAssetUserRe(AssetUserRe assetUserRe) {
        this.assetUserRe = assetUserRe;
    }

    public List<Business> getBusiness() {
        return business;
    }

    public void setBusiness(List<Business> business) {
        this.business = business;
    }

    public UBDto getUbDto() {
        return ubDto;
    }

    public void setUbDto(UBDto ubDto) {
        this.ubDto = ubDto;
    }

    public UserBond getUserBond() {
        return userBond;
    }

    public void setUserBond(UserBond userBond) {
        this.userBond = userBond;
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
