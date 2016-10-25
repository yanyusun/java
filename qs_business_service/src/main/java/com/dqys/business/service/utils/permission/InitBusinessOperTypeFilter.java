package com.dqys.business.service.utils.permission;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.constant.ObjectEnum.IouEnum;
import com.dqys.business.service.constant.ObjectEnum.PawnEnum;
import com.dqys.business.service.service.OperTypeService;

import java.util.LinkedList;
import java.util.List;

/**
 * 是否具有该项业务权限
 * Created by yan on 16-10-24.
 */
public class InitBusinessOperTypeFilter extends OperTypeFilter {
    private OperTypeService operTypeService;
    private Integer objectType;
    private Integer objectId;
    private Integer flowType;

    public InitBusinessOperTypeFilter(OperTypeService operTypeService, Integer objectType, Integer objectId, Integer flowType) {
        this.operTypeService = operTypeService;
        this.objectType = objectType;
        this.objectId = objectId;
        this.flowType = flowType;
    }

    @Override
    public List<OperType> getPermission(List<OperType> list) {
        List<OperType> newList = new LinkedList<>();
        List<OperType>  buisnesOperTypeList=operTypeService.getInitBuisnesOperTypeList(objectType,objectId,flowType);
        //需要检测的业务流转行为
        for(OperType operType:list){
            if(needcheck(operType,flowType)){
                boolean notNeedAdd=true;//不需要添加
                for(OperType bOperType:buisnesOperTypeList){//在业务类型中只保留录入时允许的业务流转方式
                    if(bOperType.getOperType().intValue()==operType.getOperType()){
                        notNeedAdd=false;
                    }
                }
                if(!notNeedAdd){
                    newList.add(operType);
                }
            }else{
                newList.add(operType);
            }
        }
        operTypes=newList;
        return getNextPermission();
    }

    /**
     * 需要检测的业务流转行为
     * @param operType
     * @return ture需要，false不需要
     */
    private boolean needcheck(OperType operType,Integer flowType){
        LinkedList<Integer> businessTypeList = new LinkedList<>();
        ObjectTypeEnum objectTypeEnum = ObjectTypeEnum.getObjectTypeEnum(flowType);
        switch (objectTypeEnum){
            case PAWN:
                businessTypeList.add(PawnEnum.MAINTAIN_REGULAR.getValue());
                businessTypeList.add(PawnEnum.MARKET_DISPOSITION.getValue());
                businessTypeList.add(PawnEnum.CM_SIMULTANEOUS.getValue());
                businessTypeList.add(PawnEnum.EXECUTE_JUSTICE_RESOLVE.getValue());
                businessTypeList.add(PawnEnum.CJ_SIMULTANEOUS.getValue());
                businessTypeList.add(PawnEnum.CMJ_SIMULTANEOUS.getValue());
                break;
            case IOU:
                businessTypeList.add(IouEnum.MAINTAIN_REGULAR.getValue());
                businessTypeList.add(IouEnum.MARKET_DISPOSITION.getValue());
                businessTypeList.add(IouEnum.CM_SIMULTANEOUS.getValue());
                businessTypeList.add(IouEnum.EXECUTE_JUSTICE_RESOLVE.getValue());
                businessTypeList.add(IouEnum.CJ_SIMULTANEOUS.getValue());
                businessTypeList.add(IouEnum.CMJ_SIMULTANEOUS.getValue());
                break;
        }
        for(Integer businessType:businessTypeList){
            if(businessType.intValue()==operType.getOperType()){
                return true;
            }
        }
        return false;
    }
}
