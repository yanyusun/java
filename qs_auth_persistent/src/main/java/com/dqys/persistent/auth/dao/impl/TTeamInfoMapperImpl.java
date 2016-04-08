package com.dqys.persistent.auth.dao.impl;

import com.dqys.core.base.BaseDao;
import com.dqys.persistent.auth.dao.TTeamInfoMapper;
import com.dqys.persistent.auth.pojo.TTeamInfo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @author by pan on 16-4-6.
 */
@Repository
@Primary
public class TTeamInfoMapperImpl extends BaseDao implements TTeamInfoMapper {
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(TTeamInfoMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(TTeamInfo record) {
        return super.getSqlSession().getMapper(TTeamInfoMapper.class).insertSelective(record);
    }

    @Override
    public TTeamInfo selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(TTeamInfoMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TTeamInfo record) {
        return super.getSqlSession().getMapper(TTeamInfoMapper.class).updateByPrimaryKeySelective(record);
    }
}
