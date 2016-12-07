package com.dqys.business.service.utils.permission;

import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.constant.ObjectEnum.ObjectEnumBase;

import java.util.List;

/**
 * 用于添加业务流转的操作
 * Created by yan on 16-12-7.
 */
public class AddBusinessOperTypeFilter extends OperTypeFilter{
    private ObjectEnumBase objectEnumBase;
    @Override
    public List<OperType> getPermission(List<OperType> list) {

        return null;
    }
}
