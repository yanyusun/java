package com.dqys.business.service.utils.permission;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.pojo.operType.OperType;

import java.util.List;

/**
 * 用于添加业务流转的操作
 * Created by yan on 16-12-7.
 */
public class AddBusinessOperTypeFilter extends OperTypeFilter {
    private Integer objectType;

    public AddBusinessOperTypeFilter(Integer objectType) {
        this.objectType = objectType;
    }

    @Override
    public List<OperType> getPermission(List<OperType> list) {
        if (objectType == ObjectTypeEnum.PAWN.getValue().intValue()) {
            PermissionUtil.addPawnBusinessOpertype(list);
        } else if (objectType == ObjectTypeEnum.IOU.getValue().intValue()) {
            PermissionUtil.addIouBusinessOpertype(list);
        }
        operTypes=list;
        return getNextPermission();
    }

}
