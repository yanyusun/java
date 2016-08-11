package com.dqys.business.orm.mapper.followUp;


import com.dqys.business.orm.pojo.followUp.FollowUpReadstatus;

public interface FollowUpReadstatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FollowUpReadstatus record);

    int insertSelective(FollowUpReadstatus record);

    FollowUpReadstatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FollowUpReadstatus record);

    int updateByPrimaryKey(FollowUpReadstatus record);
}