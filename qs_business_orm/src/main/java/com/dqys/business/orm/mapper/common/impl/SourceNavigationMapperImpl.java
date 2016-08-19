package com.dqys.business.orm.mapper.common.impl;

import com.dqys.business.orm.mapper.common.SourceNavigationMapper;
import com.dqys.business.orm.pojo.common.SourceNavigation;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yvan on 16/7/29.
 */
@Repository
@Primary
public class SourceNavigationMapperImpl extends BaseDao implements SourceNavigationMapper {

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(SourceNavigationMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(SourceNavigation record) {
        return super.getSqlSession().getMapper(SourceNavigationMapper.class).insert(record);
    }

    @Override
    public SourceNavigation get(Integer id) {
        return super.getSqlSession().getMapper(SourceNavigationMapper.class).get(id);
    }

    @Override
    public Integer update(SourceNavigation record) {
        return super.getSqlSession().getMapper(SourceNavigationMapper.class).update(record);
    }

    @Override
    public List<SourceNavigation> listByTypeAndLenderId(@Param("lenderId") Integer lenderId, @Param("type") Integer type) {
        return super.getSqlSession().getMapper(SourceNavigationMapper.class).listByTypeAndLenderId(lenderId, type);
    }
}
