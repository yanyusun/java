package com.dqys.business.service.cache.ser;

import java.util.List;

/**
 * Created by yan on 16-8-25.
 */
public abstract class SelectListCache<T> {
    abstract T getCacheBean();
    abstract List<T> getListFromDb();
    List<T> ListCache(){

    };

}
