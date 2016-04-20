package com.dqys.core.mapper.impl;


import com.dqys.core.base.BaseDao;
import com.dqys.core.mapper.facade.TSysPropertyMapper;
import com.dqys.core.model.TSysProperty;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author by pan on 16-3-15.
 */
@Repository
public class TSysPropertyMapperImpl extends BaseDao implements TSysPropertyMapper {

    @Override
    public List<TSysProperty> selectAll() throws Exception {
        return super.getSqlSession().getMapper(TSysPropertyMapper.class).selectAll();
    }

    @Override
    public List<TSysProperty> selectByType(Integer type) {
        return super.getSqlSession().getMapper(TSysPropertyMapper.class).selectByType(type);
    }

    @Override
    public int insertSelective(TSysProperty tSysProperty) {
        return super.getSqlSession().getMapper(TSysPropertyMapper.class).insertSelective(tSysProperty);
    }

    @Override
    public int updateByPrimaryKeySelective(TSysProperty tSysProperty) {
        return super.getSqlSession().getMapper(TSysPropertyMapper.class).updateByPrimaryKeySelective(tSysProperty);
    }

    @Override
    public int delete(Integer id) {
        return super.getSqlSession().getMapper(TSysPropertyMapper.class).delete(id);
    }

    @Override
    public TSysProperty selectByPromaryKey(Integer id) {
        return super.getSqlSession().getMapper(TSysPropertyMapper.class).selectByPromaryKey(id);
    }
}
