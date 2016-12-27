package com.dqys.sale.service.facade;

import java.util.Map;

/**
 * Created by mkfeng on 2016/12/26.
 */
public interface BusinessService {
    //加入和取消收藏操作
    Map collect(Integer status, Integer objectId, Integer objectType);

    /**
     * 修改已发布、已收藏、正在处置的数量
     *
     * @param id
     * @param userId
     * @param hasPublish
     * @param onCollection
     * @param onBusiness
     * @return
     */
    Integer setUserBus(Integer id, Integer userId, Integer hasPublish, Integer onCollection, Integer onBusiness);

    /**
     * 处置申请
     *
     * @param status
     * @param objectId
     * @param objectType
     * @return
     */
    Map applyDispose(Integer status, Integer objectId, Integer objectType);

    /**
     * 发布>>平台发布能显示在前台，用户发布则需要平台审核
     * @return
     */
    Map release(Integer businessId, Integer businessType, Integer businessLevel, Integer operType);
}
