package com.dqys.business.service.service;

import java.util.List;

import java.util.Map;

/**
 * Created by mkfeng on 2016/7/12.
 */
public interface CoordinatorService {

    /**
     * 创建分配器(邀请人为当前操作人员)
     * @param type 操作对象类型
     * @param id 操作对象id
     * @return
     */
    Integer createDistribution(String type, Integer id);

    /**
     * 邀请公司参与分配器
     * @param companyId 公司id
     * @param distributionId 协作器id
     * @param cooperationType 协作类型
     * @param userId 角色id
     * @return
     */
    Integer inviteDistribution(Integer companyId, Integer distributionId,
                               String cooperationType, Integer userId);


    /**
     * 借款人信息或资产包信息和团队列表
     *
     * @param map
     * @param companyId
     * @param objectId
     * @param objectType
     */
    void readByLenderOrAsset(Map<String, Object> map, Integer companyId, Integer objectId, Integer objectType);

}
