package com.dqys.business.orm.mapper.asset.impl;

import com.dqys.business.orm.mapper.asset.LenderInfoMapper;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.core.base.BaseDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yvan on 16/6/8.
 */
@Repository
@Primary
public class LenderInfoMapperImpl extends BaseDao implements LenderInfoMapper {

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(LenderInfoMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer update(LenderInfo lenderInfo) {
        return super.getSqlSession().getMapper(LenderInfoMapper.class).update(lenderInfo);
    }

    @Override
    public Integer insert(LenderInfo record) {
        return super.getSqlSession().getMapper(LenderInfoMapper.class).insert(record);
    }

    @Override
    public LenderInfo get(Integer id) {
        return super.getSqlSession().getMapper(LenderInfoMapper.class).get(id);
    }

}