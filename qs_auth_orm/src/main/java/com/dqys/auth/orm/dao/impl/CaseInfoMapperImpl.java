package com.dqys.auth.orm.dao.impl;

import com.dqys.auth.orm.dao.facade.CaseInfoMapper;
import com.dqys.auth.orm.pojo.CaseInfo;
import com.dqys.core.base.BaseDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Created by Yvan on 16/6/12.
 */
@Service
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
