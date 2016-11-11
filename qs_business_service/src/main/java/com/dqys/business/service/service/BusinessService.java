package com.dqys.business.service.service;

import com.dqys.business.orm.pojo.business.Business;
import com.dqys.business.orm.pojo.business.ObjectUserRelation;

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

}
