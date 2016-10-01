package com.dqys.business.service.utils.permission;

import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.constant.ObjectEnum.UserInfoEnum;
import com.dqys.core.constant.RoleTypeEnum;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pan on 16-10-1.
 */
@Service
@Primary
public class UserObjOperTypeFilter extends OperTypeFilter {



    private Integer objectType;

    public UserObjOperTypeFilter(Integer objectType) {
        this.objectType = objectType;
    }

    @Override
    public List<OperType> getPermission() {
        Integer roleType = RoleTypeEnum.GENERAL.getValue();
        Integer userType = UserInfoEnum.USER_TYPE_COMMON.getValue();
        List<OperType> operTypes = operTypeService.getOperType(roleType, userType, objectType);
        return  operTypes;
    }

}
