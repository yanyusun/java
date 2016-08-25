package com.dqys.business.service.cache.ser.base;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

/**
 * Created by yan on 16-8-25.
 */
public interface SelectListCache<CacheBean> {
    /**
     * 得到用于缓存的对象
     * @return
     */
    CacheBean getCacheBean();

    /**
     * 从数据库中查询list
     * @return
     */
     List<> getListFromDb();

     int getListCount();

    /**
     * 有缓存处理的list
     * @return
     */
    List<B> ListCache();

    Map<String,Object>
}
