package com.dqys.business.service.utils.permission;

import com.dqys.business.orm.constant.business.BusinessStatusEnum;
import com.dqys.business.orm.pojo.business.Business;
import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.core.constant.UserInfoEnum;
import com.dqys.business.service.constant.asset.ObjectTabEnum;
import com.dqys.business.service.service.BusinessService;
import com.dqys.business.service.service.OperTypeService;
import com.dqys.business.service.utils.operType.OperTypeUtile;

import java.util.List;

/**
 * 除了主流程tab以外其他tab需要添加的操作项
 * focus(3, "聚焦"),
 * month(4, "当月"),
 * stock(5, "存量"),
 * joined(10, "已参与"),
 * task(15, "我的任务"),
 * gongingOn(19, "正在进行"),
 * myUrge(20, "我的催收"),
 * Created by yan on 16-11-11.
 */
public class OtherTabAddOperTypeFilter  extends OperTypeFilter{
    private BusinessService businessService;
    private Integer objectId;
    private Integer objectType;
    private int userType;
    private OperTypeService operTypeService;

    public OtherTabAddOperTypeFilter(BusinessService businessService, Integer objectId, Integer objectType, int userType, OperTypeService operTypeService) {
        this.businessService = businessService;
        this.objectId = objectId;
        this.objectType = objectType;
        this.userType = userType;
        this.operTypeService = operTypeService;
    }

    @Override
    public List<OperType> getPermission(List<OperType> list) {
        Business business=businessService.getBusiness(objectType,objectId);
        if(business.getStatus()!=BusinessStatusEnum.platform_refuse.getValue().intValue()
                &&business.getStatus()!=BusinessStatusEnum.init.getValue()){//当不为待审核和已驳回状态时,改为正在处置的操作项
            List<OperType> operTypes1=null;
            if(userType== UserInfoEnum.USER_TYPE_ADMIN.getValue()||userType==UserInfoEnum.USER_TYPE_ENTRUST.getValue()){//是委托或者平台
                setOperTypes(ObjectTabEnum.handling_urge);
            }else{
                setOperTypes(ObjectTabEnum.handling_entrust);
            }
        }else{
            operTypes=list;
        }
        return getNextPermission();
    }

    private void setOperTypes(ObjectTabEnum tabEnum) {
        List<OperType> operTypes1 = operTypeService.getOperType(objectType, objectId);
        List<OperType> operTypes2= OperTypeUtile.getOperType(tabEnum.getValue(),objectType);
        for(OperType operType1:operTypes1){
            for(OperType operType2:operTypes2){
                if(operType2.getOperType().intValue()==operType1.getOperType().intValue()){
                    operTypes.add(operType1);
                }
            }
        }
    }

}
