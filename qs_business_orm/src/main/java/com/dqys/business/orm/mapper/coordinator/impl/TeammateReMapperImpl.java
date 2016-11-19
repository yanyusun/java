package com.dqys.business.orm.mapper.coordinator.impl;

import com.dqys.business.orm.mapper.coordinator.TeammateReMapper;
import com.dqys.business.orm.pojo.coordinator.TeammateRe;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
    public List<Integer> listObjectIdByType(@Param("objectType") Integer objectType, @Param("userId") Integer userId,
                                            @Param("type") Integer type) {
        return super.getSqlSession().getMapper(TeammateReMapper.class).listObjectIdByType(objectType, userId, type);
    }

    @Override
    public TeammateRe selectByObjectAndUser(@Param("objectType") Integer objectType, @Param("objectId") Integer objectId, @Param("userId") Integer userId) {
        return super.getSqlSession().getMapper(TeammateReMapper.class).selectByObjectAndUser(objectType, objectId, userId);
    }

    @Override
    public Integer deleteByUserTeamId(@Param("userTeamIds") List<Integer> userTeamIds) {
        return super.getSqlSession().getMapper(TeammateReMapper.class).deleteByUserTeamId(userTeamIds);
    }

    @Override
    public List<TeammateRe> selectSelectiveByUserTeam(Map teamMap) {
        return super.getSqlSession().getMapper(TeammateReMapper.class).selectSelectiveByUserTeam(teamMap);
    }

    @Override
    public List<TeammateRe> selectTeamReByStateflag(TeammateRe teammateRe) {
        return super.getSqlSession().getMapper(TeammateReMapper.class).selectTeamReByStateflag(teammateRe);
    }
}
