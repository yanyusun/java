package com.dqys.business.service.service;

import com.dqys.business.service.dto.company.DistributionDTO;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.core.model.JsonResponse;

/**
 * Created by Yvan on 16/7/21.
 *
 * 分配器管理
 */
public interface DistributionService {

    /**
     * 获取分配器
     * @param type
     * @param id
     * @return
     */
    DistributionDTO listDistribution_tx(Integer type, Integer id) throws BusinessLogException;

    /**
     * 创建分配器
     * @param type
     * @param id
     * @return
     * @throws BusinessLogException
     */
    JsonResponse addDistribution(Integer type, Integer id) throws BusinessLogException;

    /**
     * 加入分配器
     * @param id 分配器ID
     * @return
     */
    JsonResponse joinDistribution(Integer id) throws BusinessLogException;

    /**
     * 邀请加入分配器
     * @param id 分配器ID
     * @param companyId 被邀请公司ID
     * @return
     * @throws BusinessLogException
     */
    JsonResponse inviteDistribution(Integer id, Integer companyId) throws BusinessLogException;

    /**
     * 修改分配器(同意加入|拒绝加入)
     * @param id
     * @param type
     * @return
     */
    JsonResponse updateDistribution_tx(Integer id, Integer type) throws BusinessLogException;

    /**
     * 退出该分配记录
     * @param id
     * @return
     */
    JsonResponse exitDistribution_tx(Integer id) throws BusinessLogException;

    /**
     * 平台为申请业务流转的公司添加业务流转伙伴
     * @param type 对象类型
     * @param id 对象ID
     * @param distributionId 分配器ID
     * @param businessType 业务类型
     * @param companyId 被邀请公司ID
     */
    JsonResponse addBusinessService(Integer type, Integer id, Integer distributionId,
                               Integer businessType, Integer companyId,Integer businessRequestId) throws BusinessLogException;

    /**
     * 被添加公司接受或者拒绝业务流转邀请
     * @param type
     * @param id
     * @param distributionId
     * @param status
     * @return
     */
    JsonResponse updateBusinessService(Integer type, Integer id, Integer distributionId,
                                  Integer businessType, Integer status) throws BusinessLogException;

    /**
     * 删除业务流转的被添加公司
     * @param id
     * @param targetType
     * @param targetId
     * @return
     * @throws BusinessLogException
     */
    JsonResponse exitBusinessService(Integer id, Integer targetType, Integer targetId) throws BusinessLogException;

    /**
     *  所属机构同意平台将其加入分配器
     * @param id
     * @param status
     * @return
     * @throws BusinessLogException
     */
    JsonResponse designDistribution(Integer id, Integer status) throws BusinessLogException;



}
