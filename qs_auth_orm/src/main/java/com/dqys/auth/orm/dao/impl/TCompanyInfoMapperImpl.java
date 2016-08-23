package com.dqys.auth.orm.dao.impl;

import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.auth.orm.pojo.CompanyDetailInfo;
import com.dqys.auth.orm.query.CompanyQuery;
import com.dqys.core.base.BaseDao;
import com.dqys.auth.orm.pojo.TCompanyInfo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author by pan on 16-4-6.
 */
@Repository
@Primary
public class TCompanyInfoMapperImpl extends BaseDao implements TCompanyInfoMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(TCompanyInfoMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insertSelective(TCompanyInfo record) {
        return super.getSqlSession().getMapper(TCompanyInfoMapper.class).insertSelective(record);
    }

    @Override
    public TCompanyInfo selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(TCompanyInfoMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(TCompanyInfo record) {
        return super.getSqlSession().getMapper(TCompanyInfoMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer countByQuery(CompanyQuery query) {
        return super.getSqlSession().getMapper(TCompanyInfoMapper.class).countByQuery(query);
    }

    @Override
    public List<TCompanyInfo> queryList(CompanyQuery query) {
        return super.getSqlSession().getMapper(TCompanyInfoMapper.class).queryList(query);
    }

    @Override
    public CompanyDetailInfo get(Integer id) {
        return super.getSqlSession().getMapper(TCompanyInfoMapper.class).get(id);
    }

    @Override
    public List<TCompanyInfo> listByType(Integer type) {
        return super.getSqlSession().getMapper(TCompanyInfoMapper.class).listByType(type);
    }
}
