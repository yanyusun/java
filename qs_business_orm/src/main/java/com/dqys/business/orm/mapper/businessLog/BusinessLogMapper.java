package com.dqys.business.orm.mapper.businessLog;


import com.dqys.business.orm.pojo.businessLog.BusinessLog;
import com.dqys.business.orm.query.businessLog.BusinessLogQuery;

import java.util.List;

public interface BusinessLogMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(BusinessLog record);

    int insertSelective(BusinessLog record);

    BusinessLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessLog record);

    int updateByPrimaryKey(BusinessLog record);

    List<BusinessLog> list(BusinessLogQuery query);

    int queryCount(BusinessLogQuery businessLogQuery);

    /**
     * 查询所有前段需要展示的对象
     * @param query
     * @return
     */
    List<BusinessLog> listAll(BusinessLogQuery query);

    /**
     * 查询前段需要展示的特定对象
     * @param query
     * @return
     */
    List<BusinessLog> listAllByObjectType(BusinessLogQuery query);

    /**
     * 查询所有前段需要展示的对象的数量
     * @param query
     * @return
     */
    int allQueryCount(BusinessLogQuery query);

    /**
     * 查询前段需要展示的特定对象的数量
     * @param query
     * @return
     */
    int allByObjectQueryCount(BusinessLogQuery query);

}