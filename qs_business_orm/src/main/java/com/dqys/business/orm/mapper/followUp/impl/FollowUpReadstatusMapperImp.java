package com.dqys.business.orm.mapper.followUp.impl;

import com.dqys.business.orm.mapper.followUp.FollowUpReadstatusMapper;
import com.dqys.business.orm.pojo.followUp.FollowUpReadstatus;
import com.dqys.business.orm.query.followUp.FollowUpReadstatusQuery;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yan on 16-8-15.
 */
@Repository
public class FollowUpReadstatusMapperImp extends BaseDao implements FollowUpReadstatusMapper {
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(FollowUpReadstatusMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public int insert(FollowUpReadstatus record) {
        return super.getSqlSession().getMapper(FollowUpReadstatusMapper.class).insert(record);
    }

    @Override
    public int insertSelective(FollowUpReadstatus record) {
        return super.getSqlSession().getMapper(FollowUpReadstatusMapper.class).insertSelective(record);
    }

    @Override
    public FollowUpReadstatus selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(FollowUpReadstatusMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(FollowUpReadstatus record) {
        return super.getSqlSession().getMapper(FollowUpReadstatusMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(FollowUpReadstatus record) {
        return super.getSqlSession().getMapper(FollowUpReadstatusMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public List<Integer> selectByUseridList(@Param("objectId") int objectId, @Param("objectType") int objectType) {
        return super.getSqlSession().getMapper(FollowUpReadstatusMapper.class).selectByUseridList(objectId,objectType);
    }

    @Override
    public void deleteByOOL(@Param("objectId") int objectId, @Param("objectType") int objectType, @Param("liquidateStage") int liquidateStage, @Param("userId") int userId) {
        super.getSqlSession().getMapper(FollowUpReadstatusMapper.class).deleteByOOL(objectId, objectType, liquidateStage,userId);
    }

    @Override
    public List<Map<String, String>>getCountMap(@Param("objectId") int objectId, @Param("objectType") int objectType, @Param("userId") int userId) {
        return  super.getSqlSession().getMapper(FollowUpReadstatusMapper.class).getCountMap(objectId, objectType, userId);
    }

    @Override
    public Integer countByTypeIdUser(@Param("objectId") Integer objectId, @Param("objectType") Integer objectType,
                                     @Param("userId") Integer userId) {
        return super.getSqlSession().getMapper(FollowUpReadstatusMapper.class)
                .countByTypeIdUser(objectId, objectType, userId);
    }

    @Override
    public int queryCount(FollowUpReadstatusQuery query) {
        return super.getSqlSession().getMapper(FollowUpReadstatusMapper.class).queryCount(query);
    }
}
