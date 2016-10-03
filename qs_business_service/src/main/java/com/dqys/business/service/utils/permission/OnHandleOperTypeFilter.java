package com.dqys.business.service.utils.permission;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.asset.IOUInfoMapper;
import com.dqys.business.orm.mapper.asset.PawnInfoMapper;
import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.orm.pojo.asset.PawnInfo;
import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.constant.ObjectEnum.LenderEnum;
import com.dqys.business.service.constant.ObjectEnum.UserInfoEnum;
import com.dqys.business.service.service.permission.Permission;
import com.dqys.core.base.BusinessFlowModel;
import com.dqys.core.model.UserSession;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yan on 16-10-4.
 */
public class OnHandleOperTypeFilter extends OperTypeFilter {

    private Integer objectType;

    private Integer objectId;

    private PawnInfoMapper pawnInfoMapper;

    private IOUInfoMapper iouInfoMapper;

    public OnHandleOperTypeFilter(Integer objectType, Integer objectId, PawnInfoMapper pawnInfoMapper, IOUInfoMapper iouInfoMapper) {
        this.objectType = objectType;
        this.objectId = objectId;
        this.pawnInfoMapper = pawnInfoMapper;
        this.iouInfoMapper = iouInfoMapper;
    }

    @Override
    public List<OperType> getPermission(List<OperType> list) {
        boolean isOnHandle=true;
        UserSession userSession = UserSession.getCurrent();
        String userType=userSession.getUserType();
        UserInfoEnum userInfoEnum=UserInfoEnum.getUserInfoEnum(Integer.valueOf(userType));
        BusinessFlowModel businessFlowModel=null;
        if(ObjectTypeEnum.IOU.getValue()==objectType){
            businessFlowModel=iouInfoMapper.get(objectId);
        }else if(ObjectTypeEnum.PAWN.getValue()==objectType){
            businessFlowModel=pawnInfoMapper.get(objectId);
        }
        switch (userInfoEnum){
            case USER_TYPE_COLLECTION:
                if(businessFlowModel.getOnCollection()==1){
                    isOnHandle=false;
                }
                break;
            case USER_TYPE_JUDICIARY:
                if(businessFlowModel.getOnLawyer()==1){
                    isOnHandle=false;
                }
                break;
            case USER_TYPE_INTERMEDIARY:
                if(businessFlowModel.getOnAgent()==1){
                    isOnHandle=false;
                }
        }
        if(isOnHandle){
            operTypes=list;
        }else{
            operTypes= PermissionUtil.getDefaultOperTypeList(list);
        }
        return getNextPermission();
    }
}
