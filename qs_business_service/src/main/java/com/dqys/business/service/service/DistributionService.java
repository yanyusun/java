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
     * 获取分配器列表(没有分配器则创建分配器)
     * @param type
     * @param id
     * @return
     */
    DistributionDTO getDistribution_tx(Integer type, Integer id) throws BusinessLogException;

    /**
     * 加入分配器
     * @param id 分配器ID
     * @param type 加入类型(邀请|被邀请)
     * @param text 邀请内容
     * @return
     */
    Integer joinDistribution_tx(Integer id, Integer type, String text) throws BusinessLogException;

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
