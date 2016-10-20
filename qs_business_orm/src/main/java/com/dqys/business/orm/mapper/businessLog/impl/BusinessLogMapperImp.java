package com.dqys.business.orm.mapper.businessLog.impl;

import com.dqys.business.orm.mapper.businessLog.BusinessLogMapper;
import com.dqys.business.orm.pojo.businessLog.BusinessLog;
import com.dqys.business.orm.query.businessLog.BusinessLogQuery;
import com.dqys.core.base.BaseDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yan on 16-7-13.
 */
@Repository
@Primary
public class BusinessLogMapperImp extends BaseDao implements BusinessLogMapper {
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(BusinessLog record) {
        return super.getSqlSession().getMapper(BusinessLogMapper.class).insert(record);
    }

    @Override
    public int insertSelective(BusinessLog record) {
        return 0;
    }

    @Override
    public BusinessLog selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(BusinessLogMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(BusinessLog record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(BusinessLog record) {
        return 0;
    }

    @Override
    public List<BusinessLog> list(BusinessLogQuery query) {
        return super.getSqlSession().getMapper(BusinessLogMapper.class).list(query);
    }

    @Override
    public int queryCount(BusinessLogQuery businessLogQuery) {
        return super.getSqlSession().getMapper(BusinessLogMapper.class).queryCount(businessLogQuery);
    }

    @Override
    public List<BusinessLog> listAll(BusinessLogQuery query) {
        return super.getSqlSession().getMapper(BusinessLogMapper.class).listAll(query);
    }

    @Override
    public List<BusinessLog> listAllByObjectType(BusinessLogQuery query) {
        return super.getSqlSession().getMapper(BusinessLogMapper.class).listAllByObjectType(query);
    }

    @Override
    public List<BusinessLog> allQueryCount(BusinessLogQuery query) {
        return super.getSqlSession().getMapper(BusinessLogMapper.class).allQueryCount(query);
    }

    @Override
    public List<BusinessLog> allByObjectQueryCount(BusinessLogQuery query) {
        return super.getSqlSession().getMapper(BusinessLogMapper.class).allByObjectQueryCount(query);
    }
}
