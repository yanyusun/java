package com.dqys.business.orm.mapper.coordinator.impl;

import com.dqys.business.orm.mapper.coordinator.CoordinatorMapper;
import com.dqys.business.orm.pojo.coordinator.team.TeamDTO;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/7/12.
 */
@Repository
public class CoordinatorMapperImpl extends BaseDao implements CoordinatorMapper {
    @Override
    public List<TeamDTO> getLenderOrAsset(Integer companyId, Integer objectId, Integer objectType) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).getLenderOrAsset(companyId, objectId, objectType);
    }

    @Override
    public Map<String, Object> getTaskOngoing(@Param("companyId") Integer companyId, @Param("userId") Integer userId,@Param("objectType") Integer objectType) {
        return  super.getSqlSession().getMapper(CoordinatorMapper.class).getTaskOngoing(companyId,userId,objectType);
    }

    @Override
    public Map<String, Object> getTaskRatio(@Param("companyId") Integer companyId, @Param("userId") Integer userId,@Param("objectType") Integer objectType) {
        return  super.getSqlSession().getMapper(CoordinatorMapper.class).getTaskRatio(companyId,userId,objectType);
    }

    @Override
    public List<Map<String, Object>> getPeopleNum(@Param("companyId") Integer companyId, @Param("objectId") Integer objectId, @Param("objectType") Integer objectType) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).getPeopleNum(companyId,objectId,objectType);
    }
}
