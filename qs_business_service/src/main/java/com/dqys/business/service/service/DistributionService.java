package com.dqys.business.service.service;

import com.dqys.business.service.dto.company.DistributionDTO;
import com.dqys.business.service.exception.bean.BusinessLogException;

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
    DistributionDTO getDistribution_tx(Integer type, Integer id) throws BusinessLogException;

    /**
     * 创建分配器
     * @param type
     * @param id
     * @return
     * @throws BusinessLogException
     */
    Integer addDistribution(Integer type, Integer id) throws BusinessLogException;

    /**
     * 加入分配器
     * @param id 分配器ID
     * @return
     */
    Integer joinDistribution(Integer id) throws BusinessLogException;

    /**
     * 邀请加入分配器
     * @param id 分配器ID
     * @param companyId 被邀请公司ID
     * @return
     * @throws BusinessLogException
     */
    Integer inviteDistribution(Integer id, Integer companyId) throws BusinessLogException;

    /**
     * 修改分配器(同意加入|拒绝加入)
     * @param id
     * @param type
     * @return
     */
    Integer updateDistribution_tx(Integer id, Integer type) throws BusinessLogException;

    /**
     * 退出该分配记录
     * @param id
     * @return
     */
    Integer exitDistribution_tx(Integer id) throws BusinessLogException;



}
