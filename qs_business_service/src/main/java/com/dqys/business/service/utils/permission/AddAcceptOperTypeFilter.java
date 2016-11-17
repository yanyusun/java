package com.dqys.business.service.utils.permission;

import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.constant.OperTypeExtendEnum;

import java.util.List;

/**
 * Created by pan on 16-11-17.
 */
public class AddAcceptOperTypeFilter extends OperTypeFilter{
    @Override
    public List<OperType> getPermission(List<OperType> list) {
        PermissionUtil.addEditOperType(list, OperTypeExtendEnum.ACCEPT_AGREE.getValue(),OperTypeExtendEnum.ACCEPT_AGREE.getName());
        PermissionUtil.addEditOperType(list, OperTypeExtendEnum.ACCEPT_REJECT.getValue(),OperTypeExtendEnum.ACCEPT_REJECT.getName());
        operTypes=list;
        return getNextPermission();
    }
}
