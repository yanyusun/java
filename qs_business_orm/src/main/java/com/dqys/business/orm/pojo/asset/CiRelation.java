package com.dqys.business.orm.pojo.asset;

import com.dqys.core.base.BaseModel;

/**
 * 案件&借据关联表
 */
public class CiRelation extends BaseModel {

    private Integer caseId;  // 案件ID

    private Integer iouId;  // 借据ID


    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public Integer getIouId() {
        return iouId;
    }

    public void setIouId(Integer iouId) {
        this.iouId = iouId;
    }
}