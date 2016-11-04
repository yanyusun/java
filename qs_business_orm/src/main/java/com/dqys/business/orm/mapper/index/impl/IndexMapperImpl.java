package com.dqys.business.orm.mapper.index.impl;

import com.dqys.business.orm.mapper.index.IndexMapper;
import com.dqys.business.orm.pojo.index.UserMessage;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by mkfeng on 2016/8/1.
 */
@Repository
public class IndexMapperImpl extends BaseDao implements IndexMapper {

    @Override
    public Map receive(Integer userId) {
        return super.getSqlSession().getMapper(IndexMapper.class).receive(userId);
    }

    @Override
    public Map getLenderAllotByOneSelfAndCompany(@Param("repayStatus") Integer repayStatus, @Param("objectType") Integer objectType, @Param("userId") Integer userId) {
        return super.getSqlSession().getMapper(IndexMapper.class).getLenderAllotByOneSelfAndCompany(repayStatus, objectType, userId);
    }

    @Override
    public Map getLenderAllotByTeam(@Param("repayStatus") Integer repayStatus, @Param("objectType") Integer objectType, @Param("userId") Integer userId) {
        return super.getSqlSession().getMapper(IndexMapper.class).getLenderAllotByTeam(repayStatus, objectType, userId);
    }

    @Override
    public Map getJoinTask(@Param("objectType") Integer objectType, @Param("userId") Integer userId) {
        return super.getSqlSession().getMapper(IndexMapper.class).getJoinTask(objectType, userId);
    }

    @Override
    public Map getPawnAllotByTeamIsUnfinished(@Param("objectType") Integer objectType, @Param("userId") Integer userId) {
        return super.getSqlSession().getMapper(IndexMapper.class).getPawnAllotByTeamIsUnfinished(objectType, userId);
    }

    @Override
    public Map getPawnAllotByOCIsUnfinished(@Param("objectType") Integer objectType, @Param("userId") Integer userId) {
        return super.getSqlSession().getMapper(IndexMapper.class).getPawnAllotByOCIsUnfinished(objectType, userId);
    }

    @Override
    public Map getPawnTotalTask(@Param("objectType") Integer objectType, @Param("userId") Integer userId) {
        return super.getSqlSession().getMapper(IndexMapper.class).getPawnTotalTask(objectType, userId);
    }

    @Override
    public UserMessage selectByUser(Integer userId) {
        return super.getSqlSession().getMapper(IndexMapper.class).selectByUser(userId);
    }

    @Override
    public Integer getLoginByTime(@Param("time") String time, @Param("companyId") Integer companyId) {
        return super.getSqlSession().getMapper(IndexMapper.class).getLoginByTime(time, companyId);
    }

    @Override
    public Integer getAbsent(Integer companyId) {
        return super.getSqlSession().getMapper(IndexMapper.class).getAbsent(companyId);
    }
}
