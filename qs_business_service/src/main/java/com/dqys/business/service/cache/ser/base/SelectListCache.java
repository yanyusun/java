package com.dqys.business.service.cache.ser.base;

import com.dqys.business.service.cache.bean.CacheBean;
import com.dqys.core.base.BaseModel;
import com.dqys.core.base.BaseQuery;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

/**
 * 查询列表缓存
 * Created by yan on 16-8-25.
 */
public interface SelectListCache {
    /**
     * 得到用于缓存的对象
     * @return
     */
    CacheBean getCacheBean();

    /**
     * 从数据库中查询list
     * @return
     */
     List<BaseModel> getListFromDb(BaseQuery query);

    /**
     * 查询分页数量
     * @param query
     * @return
     */
     int getListCount(BaseQuery query);

    /**
     * 有缓存处理的list
     * @return
     */
    List<BaseModel> ListCache(BaseQuery query);

    /**
     * 有缓存处理的分页系统
     * @param query
     * @return
     */
    Map<String,Object> pageMapCache(BaseQuery query);
}
