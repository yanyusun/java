package com.dqys.business.orm.pojo.asset;

import com.dqys.core.base.BaseModel;

/**
 * 抵押物&借据关系表
 */
public class PiRelation extends BaseModel{

    private Integer pawnId;  // 抵押物ID

    private Integer iouId;  // 借据ID


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