package com.dqys.business.service.dto.asset;

/**
 * Created by mkfeng on 2016/11/29.
 */
public class StatisticsLender {
    private Integer assetCount;
    private Integer lenderCount;
    private Integer pawnCount;
    private Integer iouCount;
    private Integer caseCount;

    public Integer getAssetCount() {
        return assetCount;
    }

    public void setAssetCount(Integer assetCount) {
        this.assetCount = assetCount;
    }

    public Integer getLenderCount() {
        return lenderCount;
    }

    public void setLenderCount(Integer lenderCount) {
        this.lenderCount = lenderCount;
    }

    public Integer getPawnCount() {
        return pawnCount;
    }

    public void setPawnCount(Integer pawnCount) {
        this.pawnCount = pawnCount;
    }

    public Integer getIouCount() {
        return iouCount;
    }

    public void setIouCount(Integer iouCount) {
        this.iouCount = iouCount;
    }

    public Integer getCaseCount() {
        return caseCount;
    }

    public void setCaseCount(Integer caseCount) {
        this.caseCount = caseCount;
    }
}
