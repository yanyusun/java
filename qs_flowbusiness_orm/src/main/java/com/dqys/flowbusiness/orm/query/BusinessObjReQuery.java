package com.dqys.flowbusiness.orm.query;

import com.dqys.core.base.BaseQuery;

/**
 * Created by yan on 16-11-22.
 */
public class BusinessObjReQuery extends BaseQuery {
    private Integer id; // 主键

    private Integer objectId; // 对象ID

    private Integer objectType; // 对象类型

    private Integer businessId; // 业务ID

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }
}
