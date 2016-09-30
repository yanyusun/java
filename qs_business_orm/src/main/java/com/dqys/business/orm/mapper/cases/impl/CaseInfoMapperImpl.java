package com.dqys.business.orm.mapper.cases.impl;

import com.dqys.business.orm.mapper.cases.CaseInfoMapper;
import com.dqys.business.orm.pojo.cases.CaseInfo;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public Integer countByLender(Integer id) {
        return super.getSqlSession().getMapper(CaseInfoMapper.class).countByLender(id);
    }

    @Override
    public List<CaseInfo> listByLender(@Param("id")Integer id, @Param("index")Integer index) {
        return super.getSqlSession().getMapper(CaseInfoMapper.class).listByLender(id, index);
    }

    @Override
    public Integer countByCase(Integer id) {
        return super.getSqlSession().getMapper(CaseInfoMapper.class).countByCase(id);
    }

    @Override
    public List<CaseInfo> listByCase(@Param("id") Integer id, @Param("index") Integer index) {
        return super.getSqlSession().getMapper(CaseInfoMapper.class).listByCase(id, index);
    }
}
