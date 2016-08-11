package com.dqys.business.orm.mapper.followUp;


import com.dqys.business.orm.pojo.followUp.FollowUpMessage;

public interface FollowUpMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FollowUpMessage record);

    int insertSelective(FollowUpMessage record);

    FollowUpMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FollowUpMessage record);

    int updateByPrimaryKey(FollowUpMessage record);
}