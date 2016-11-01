package com.dqys.business.service.service;

import java.util.Map;

/**
 * Created by mkfeng on 2016/11/1.
 */
public interface ParticipationService {
    /**
     * 获取参与的公司
     *
     * @return
     */
    Map getList(Integer objectId, Integer objectType);

    /**
     * 详情信息
     *
     * @param objectId
     * @param objectType
     * @param companyId
     * @return
     */
    Map getDetail(Integer objectId, Integer objectType, Integer companyId);
}
