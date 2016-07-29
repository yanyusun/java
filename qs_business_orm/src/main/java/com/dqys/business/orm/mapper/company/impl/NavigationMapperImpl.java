package com.dqys.business.orm.mapper.company.impl;

import com.dqys.business.orm.mapper.company.NavigationMapper;
import com.dqys.business.orm.pojo.company.Navigation;
import com.dqys.business.orm.query.company.NavigationQuery;
import com.dqys.core.base.BaseDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yvan on 16/7/25.
 */
@Repository
@Primary
public class NavigationMapperImpl extends BaseDao implements NavigationMapper {

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(NavigationMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(Navigation record) {
        return super.getSqlSession().getMapper(NavigationMapper.class).insert(record);
    }

    @Override
    public Navigation get(Integer id) {
        return super.getSqlSession().getMapper(NavigationMapper.class).get(id);
    }

    @Override
    public Integer update(Navigation record) {
        return super.getSqlSession().getMapper(NavigationMapper.class).update(record);
    }

    @Override
    public List<Navigation> queryList(NavigationQuery query) {
        return super.getSqlSession().getMapper(NavigationMapper.class).queryList(query);
    }

    @Override
    public Navigation getParent(Integer id) {
        return super.getSqlSession().getMapper(NavigationMapper.class).getParent(id);
    }
}
