package com.dqys.business.orm.mapper.cases.impl;

import com.dqys.business.orm.mapper.cases.CaseInfoMapper;
import com.dqys.business.orm.pojo.cases.CaseInfo;
import com.dqys.core.base.BaseDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * Created by Yvan on 16/6/14.
 */
@Repository
@Primary
public class CaseInfoMapperImpl extends BaseDao implements CaseInfoMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(CaseInfoMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(CaseInfo caseInfo) {
        return super.getSqlSession().getMapper(CaseInfoMapper.class).insert(caseInfo);
    }

    @Override
    public CaseInfo get(Integer id) {
        return super.getSqlSession().getMapper(CaseInfoMapper.class).get(id);
    }

    @Override
    public Integer update(CaseInfo caseInfo) {
        return super.getSqlSession().getMapper(CaseInfoMapper.class).update(caseInfo);
    }
}
