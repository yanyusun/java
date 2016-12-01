package com.dqys.business.service.dto.asset;

/**
 * 资产包、借款人、 借据、抵押物、案件的统计
 * Created by mkfeng on 2016/11/29.
 */
public class StatisticsLender {
    private Integer assetCount = 0;//资产包数量
    private Integer lenderCount = 0;//借款人数量
    private Integer pawnCount = 0;//抵押物数量
    private Integer iouCount = 0;//借据数量
    private Integer caseCount = 0;//案件数量

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
