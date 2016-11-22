package com.dqys.business.service.service;

import com.dqys.business.orm.pojo.business.Business;
import com.dqys.business.orm.pojo.business.BusinessObjRe;
import com.dqys.business.orm.pojo.business.ObjectUserRelation;

import java.util.List;

/**
 * Created by Yvan on 16/7/15.
 * 企业级应用服务层
 */
public interface BusinessService {

    /**
     * 增加业务对象
     * @param type 对象类型
     * @param id 对象ID
     * @param pType 上级类型
     * @param pid 上级id
     * @return
     */
    Integer addServiceObject(Integer type, Integer id, Integer pType, Integer pid);

    /**
     * 修改业务状态
     * @param business
     * @return
     */
    Integer updateService_tx(Business business);

    /**
     * 修改操作人员与被操作事物的关系
     * @param objectUserRelation
     * @return
     */
    Integer updateObjectUser_tx(ObjectUserRelation objectUserRelation);

    /**
     *  得到业务表信息
     * @param ObjectType 对象类型
     * @param ObjectId 对象信息
     * @return
     */
    Business getBusiness(Integer ObjectType,Integer ObjectId);

    /**
     * 根据对象类型和业务id查询业务关联list
     * @param ObjectType
     * @param BusinessId
     * @return
     */
    List<BusinessObjRe> getlistByObjectTypeAndBusinessId(Integer ObjectType,Integer BusinessId);

    /**
     * 根据对象类型和对象id查询业务关联List
     * @param objectType 对象类型
     * @param objectReType 与上述对象关联在统一个业务中的对象
     * @param objectId 对象id
     * @return
     */
    List<BusinessObjRe>  getListByObjecTypeAndObjectId(Integer objectType,Integer objectReType,Integer objectId);

    /**
     * 根据业务主体(资产包,借款人)跟新业务流转对象(借据,抵押物)
     * @param objectType
     * @param id
     * @param pawnStatus 抵押物状态
     * @param iouStatus 借据状态
     */
    void updateBusinessFlowObjOnType(Integer objectType, Integer id,int pawnStatus,int iouStatus);
}
