package com.dqys.business.service.utils.permission;

import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.constant.OperTypeExtendEnum;
import com.dqys.business.service.utils.common.buttonUtil.ListButtonShowerBean;
import com.dqys.business.service.utils.common.buttonUtil.ListButtonShowerUtil;

import java.util.List;

/**
 * Created by yan on 16-11-17.
 */
public class AddApplyCompanyTeamOperTypeFilter extends OperTypeFilter{
    private Integer navId;
    private Integer objectType;
    private String userType;
    private String roleType;

    public AddApplyCompanyTeamOperTypeFilter(Integer navId, Integer objectType, String userType, String roleType) {
        this.navId = navId;
        this.objectType = objectType;
        this.userType = userType;
        this.roleType = roleType;
    }

    @Override
    public List<OperType> getPermission(List<OperType> list)  {
        try{
            ListButtonShowerBean listButtonShowerBean=ListButtonShowerUtil.getListButtonShowerBean(navId,objectType,userType,roleType);
            if(listButtonShowerBean.isHasCompanyTeamButtonApply()){
                PermissionUtil.addEditOperType(list,OperTypeExtendEnum.APPLY_COMPANYTEAM.getValue(),OperTypeExtendEnum.APPLY_COMPANYTEAM.getName());
            }
        }catch (Exception e){
            //// TODO: 16-11-17  增加日志
            e.printStackTrace();
        }
        operTypes=list;
        return getNextPermission();
    }
}
