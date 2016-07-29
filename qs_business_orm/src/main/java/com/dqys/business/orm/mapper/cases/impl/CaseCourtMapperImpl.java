package com.dqys.business.orm.mapper.cases.impl;

import com.dqys.business.orm.mapper.cases.CaseCourtMapper;
import com.dqys.business.orm.pojo.cases.CaseCourt;
import com.dqys.core.base.BaseDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yvan on 16/7/26.
 */
@Repository
@Primary
public class CaseCourtMapperImpl extends BaseDao implements CaseCourtMapper {

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(CaseCourtMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(CaseCourt record) {
        return super.getSqlSession().getMapper(CaseCourtMapper.class).insert(record);
    }

    @Override
    public CaseCourt get(Integer id) {
        return super.getSqlSession().getMapper(CaseCourtMapper.class).get(id);
    }

    @Override
    public Integer update(CaseCourt record) {
        return super.getSqlSession().getMapper(CaseCourtMapper.class).update(record);
    }

    @Override
    public List<CaseCourt> listByCaseId(Integer id) {
        return super.getSqlSession().getMapper(CaseCourtMapper.class).listByCaseId(id);
    }
}
