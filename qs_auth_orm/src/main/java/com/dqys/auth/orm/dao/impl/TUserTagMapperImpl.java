package com.dqys.auth.orm.dao.impl;

import com.dqys.auth.orm.dao.facade.TUserTagMapper;
import com.dqys.auth.orm.query.UserQuery;
import com.dqys.core.base.BaseDao;
import com.dqys.auth.orm.pojo.TUserTag;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author by pan on 16-4-6.
 */
@Repository
@Primary
public class TUserTagMapperImpl extends BaseDao implements TUserTagMapper {
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(TUserTagMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(TUserTag record) {
        return super.getSqlSession().getMapper(TUserTagMapper.class).insertSelective(record);
    }

    @Override
    public TUserTag selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(TUserTagMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TUserTag record) {
        return super.getSqlSession().getMapper(TUserTagMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public List<TUserTag> selectByUserId(@Param("userId") Integer uid) {
        return super.getSqlSession().getMapper(TUserTagMapper.class).selectByUserId(uid);
    }

    @Override
    public List<TUserTag> selectByQuery(UserQuery query) {
        return super.getSqlSession().getMapper(TUserTagMapper.class).selectByQuery(query);
    }
}
