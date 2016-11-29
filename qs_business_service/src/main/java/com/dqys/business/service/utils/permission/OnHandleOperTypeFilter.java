package com.dqys.business.service.utils.permission;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.asset.IOUInfoMapper;
import com.dqys.business.orm.mapper.asset.PawnInfoMapper;
import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.constant.ObjectEnum.IouEnum;
import com.dqys.business.service.constant.ObjectEnum.PawnEnum;
import com.dqys.core.constant.UserInfoEnum;
import com.dqys.business.service.utils.user.UserServiceUtils;
import com.dqys.core.base.BusinessFlowModel;
import com.dqys.core.model.UserSession;

import java.util.LinkedList;
import java.util.List;

/**
 * 是否在该机构的处置流程中
 * Created by yan on 16-10-4.
 */
public class OnHandleOperTypeFilter extends OperTypeFilter {
    private static List<OperType> iouNotOnHandeList;

    private static List<OperType> pawnNotOnHandeList;

    //// TODO: 16-10-8 changto DB
    static{
        Integer[] pawn = {121, 122, 123, 124, 125,126,1213};
        pawnNotOnHandeList = new LinkedList<>();
            for (PawnEnum e : PawnEnum.values()) {
                OperType operType = new OperType();
                for (Integer n : pawn) {
                    //遍历是否拥有权限，有权限的就加入
                    if (e.getValue().equals(n)) {
                        operType.setOperType(e.getValue());
                        operType.setOperName(e.getName());
                        pawnNotOnHandeList.add(operType);
                        break;
                    }
                }
            }
        Integer[] iou = {130, 131, 132, 133, 134,135,1310};
        iouNotOnHandeList = new LinkedList<>();
            for (IouEnum e : IouEnum.values()) {
                OperType operType = new OperType();
                for (Integer n : iou) {
                    //遍历是否拥有权限，有权限的就加入
                    if (e.getValue().equals(n)) {
                        operType.setOperType(e.getValue());
                        operType.setOperName(e.getName());
                        iouNotOnHandeList.add(operType);
                        break;
                    }
                }
            }
    }

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
        Integer userType= UserServiceUtils.headerStringToInt(userSession.getUserType());
        UserInfoEnum userInfoEnum=UserInfoEnum.getUserInfoEnum(userType);
        BusinessFlowModel businessFlowModel=null;
        if(ObjectTypeEnum.IOU.getValue()==objectType.intValue()){
            businessFlowModel=iouInfoMapper.get(objectId);
        }else if(ObjectTypeEnum.PAWN.getValue()==objectType.intValue()){
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
            if(ObjectTypeEnum.IOU.getValue()==objectType.intValue()){
                PermissionUtil.intersection(list,iouNotOnHandeList,operTypes);
            }else{
                PermissionUtil.intersection(list,pawnNotOnHandeList,operTypes);
            }
        }
        return getNextPermission();
    }


}
