package com.dqys.business.service.service.index;

import java.util.Map;

/**
 * Created by mkfeng on 2016/8/1.
 */
public interface IndexService {
    /**
     * 获取统计数据
     *
     * @param userId
     */
    void getStatistic(Map map, Integer userId);

    /**
     * 获取用户个人信息
     *
     * @param map
     * @param userId
     */
    void getUserDetail(Map map, Integer userId);
}
