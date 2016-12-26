package com.dqys.sale.service.dto;

import com.dqys.sale.orm.pojo.AssetFile;
import com.dqys.sale.orm.pojo.Dispose;
import com.dqys.sale.orm.pojo.Label;
import com.dqys.sale.orm.pojo.UserBond;

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
