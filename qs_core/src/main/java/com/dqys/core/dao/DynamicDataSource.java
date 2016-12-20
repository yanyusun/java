package com.dqys.core.dao;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by yan on 16-12-19.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        //从自定义的位置获取数据源标识
       return DynamicDataSourceHolder.getDataSource();
    }

}
