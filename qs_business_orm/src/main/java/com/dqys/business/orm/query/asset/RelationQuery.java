package com.dqys.business.orm.query.asset;

/**
 * Created by Yvan on 16/6/16.
 */
public class RelationQuery {

    private Integer caseId;
    private Integer pawnId;
    private Integer iouId;

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public Integer getPawnId() {
        return pawnId;
    }

    public void setPawnId(Integer pawnId) {
        this.pawnId = pawnId;
    }

    public Integer getIouId() {
        return iouId;
    }

    public void setIouId(Integer iouId) {
        this.iouId = iouId;
    }
}
