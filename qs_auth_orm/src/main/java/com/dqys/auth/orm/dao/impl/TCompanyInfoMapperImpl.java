package com.dqys.auth.orm.dao.impl;

import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.core.base.BaseDao;
import com.dqys.auth.orm.pojo.TCompanyInfo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @author by pan on 16-4-6.
 */
@Repository
@Primary
public class TCompanyInfoMapperImpl extends BaseDao implements TCompanyInfoMapper {
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(TCompanyInfoMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(TCompanyInfo record) {
        return super.getSqlSession().getMapper(TCompanyInfoMapper.class).insertSelective(record);
    }

    @Override
    public TCompanyInfo selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(TCompanyInfoMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TCompanyInfo record) {
        return super.getSqlSession().getMapper(TCompanyInfoMapper.class).updateByPrimaryKeySelective(record);
    }
}