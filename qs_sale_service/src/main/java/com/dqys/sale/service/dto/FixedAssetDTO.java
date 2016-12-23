package com.dqys.sale.service.dto;

import com.dqys.sale.orm.pojo.AssetFile;
import com.dqys.sale.orm.pojo.Dispose;
import com.dqys.sale.orm.pojo.FixedAsset;
import com.dqys.sale.orm.pojo.Label;

import java.util.List;

/**
 * Created by mkfeng on 2016/12/21.
 */
public class FixedAssetDTO {
    private FixedAsset fixedAsset;//资产
    private List<Label> labels;//标签
    private List<Dispose> disposes;//处置方式
    private List<AssetFile> assetFiles;//文件

    public FixedAsset getFixedAsset() {
        return fixedAsset;
    }

    public void setFixedAsset(FixedAsset fixedAsset) {
        this.fixedAsset = fixedAsset;
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
