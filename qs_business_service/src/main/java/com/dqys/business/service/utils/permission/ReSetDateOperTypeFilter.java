package com.dqys.business.service.utils.permission;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.pojo.operType.OperType;

import java.util.List;

/**
 * Created by yan on 16-10-1.
 */
public class ReSetDateOperTypeFilter extends OperTypeFilter{

    private Integer objectId;

    private Integer ObjectType;

    private Integer navId;

    public ReSetDateOperTypeFilter(Integer objectId, Integer objectType, Integer navId) {
        this.objectId = objectId;
        ObjectType = objectType;
        this.navId = navId;
    }

    @Override
    public List<OperType> getPermission(List<OperType> list) {
        ObjectTypeEnum objectTypeEnum=ObjectTypeEnum.getObjectTypeEnum(ObjectType);
        switch (objectTypeEnum){
            case ASSETPACKAGE:

                break;

        }
        return null;
    }
}
