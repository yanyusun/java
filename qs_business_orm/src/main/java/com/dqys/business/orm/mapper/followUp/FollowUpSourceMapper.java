package com.dqys.business.orm.mapper.followUp;


import com.dqys.business.orm.pojo.followUp.FollowUpSource;

public interface FollowUpSourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FollowUpSource record);

    int insertSelective(FollowUpSource record);

    FollowUpSource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FollowUpSource record);

    int updateByPrimaryKey(FollowUpSource record);
}