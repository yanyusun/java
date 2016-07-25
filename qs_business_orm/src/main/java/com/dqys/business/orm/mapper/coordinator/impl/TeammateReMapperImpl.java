package com.dqys.business.orm.mapper.coordinator.impl;

import com.dqys.business.orm.mapper.coordinator.TeammateReMapper;
import com.dqys.business.orm.pojo.coordinator.TeammateRe;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/12.
 */
@Repository
public class TeammateReMapperImpl extends BaseDao implements TeammateReMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(TeammateReMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(TeammateRe record) {
        return super.getSqlSession().getMapper(TeammateReMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(TeammateRe record) {
        return super.getSqlSession().getMapper(TeammateReMapper.class).insertSelective(record);
    }

    @Override
    public TeammateRe selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(TeammateReMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(TeammateRe record) {
        return super.getSqlSession().getMapper(TeammateReMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKey(TeammateRe record) {
        return super.getSqlSession().getMapper(TeammateReMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public List<TeammateRe> selectSelective(TeammateRe teammateRe) {
        return super.getSqlSession().getMapper(TeammateReMapper.class).selectSelective(teammateRe);
    }

    @Override
    public List<Integer> listObjectIdByJoinType(@Param("type") Integer type, @Param("userId") Integer userId,
                                                @Param("joinType") Integer joinType) {
        return super.getSqlSession().getMapper(TeammateReMapper.class).listObjectIdByJoinType(type, userId, joinType);
    }
}
