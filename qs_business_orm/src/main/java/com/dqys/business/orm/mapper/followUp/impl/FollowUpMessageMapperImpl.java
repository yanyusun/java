package com.dqys.business.orm.mapper.followUp.impl;

import com.dqys.business.orm.mapper.followUp.FollowUpMessageMapper;
import com.dqys.business.orm.pojo.followUp.FollowUpMessage;
import com.dqys.business.orm.query.followUp.FollowUpMessageQuery;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yan on 16-8-12.
 */
@Repository
public class FollowUpMessageMapperImpl extends BaseDao implements FollowUpMessageMapper {
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(FollowUpMessageMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public int insert(FollowUpMessage record) {
        return super.getSqlSession().getMapper(FollowUpMessageMapper.class).insert(record);
    }

    @Override
    public List<FollowUpMessage> list(FollowUpMessageQuery query) {
        return super.getSqlSession().getMapper(FollowUpMessageMapper.class).list(query);
    }

    @Override
    public Integer getTeamId(@Param("objectId") Integer objectId, @Param("objectType") Integer objectType, @Param("userId") Integer userId) {
        return super.getSqlSession().getMapper(FollowUpMessageMapper.class).getTeamId(objectId, objectType, userId);
    }

    @Override
    public int insertSelective(FollowUpMessage record) {
        return super.getSqlSession().getMapper(FollowUpMessageMapper.class).insertSelective(record);
    }

    @Override
    public FollowUpMessage selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(FollowUpMessageMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(FollowUpMessage record) {
        return super.getSqlSession().getMapper(FollowUpMessageMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(FollowUpMessage record) {
        return super.getSqlSession().getMapper(FollowUpMessageMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public List<FollowUpMessage> getlistWithUserAndTeam(FollowUpMessageQuery query) {
        return super.getSqlSession().getMapper(FollowUpMessageMapper.class).getlistWithUserAndTeam(query);
    }

    @Override
    public void updateBySendStatus(FollowUpMessage followUpMessage) {
        super.getSqlSession().getMapper(FollowUpMessageMapper.class).updateBySendStatus(followUpMessage);
    }

    @Override
    public List<FollowUpMessage> objectList(FollowUpMessageQuery query) {
        return super.getSqlSession().getMapper(FollowUpMessageMapper.class).objectList(query);
    }

    @Override
    public Integer objectListCount(FollowUpMessageQuery query) {
        return super.getSqlSession().getMapper(FollowUpMessageMapper.class).objectListCount(query);
    }




    @Override
    public List<FollowUpMessage> getlistWithALL(FollowUpMessageQuery query) {
        return super.getSqlSession().getMapper(FollowUpMessageMapper.class).getlistWithALL(query);
    }
}
