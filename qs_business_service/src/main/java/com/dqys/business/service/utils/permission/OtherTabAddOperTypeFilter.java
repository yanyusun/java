package com.dqys.business.service.utils.permission;

import com.dqys.business.orm.pojo.business.Business;
import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.service.BusinessService;

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

    public OtherTabAddOperTypeFilter(BusinessService businessService, Integer objectId, Integer objectType) {
        this.businessService = businessService;
        this.objectId = objectId;
        this.objectType = objectType;
    }

    @Override
    public List<OperType> getPermission(List<OperType> list) {
        Business business=businessService.getBusiness(objectType,objectId);
//        if(business.getStatus()){
//
//        }
        return null;
    }
}
