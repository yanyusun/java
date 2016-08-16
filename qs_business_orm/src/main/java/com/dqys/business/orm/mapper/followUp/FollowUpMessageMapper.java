package com.dqys.business.orm.mapper.followUp;


import com.dqys.business.orm.pojo.followUp.FollowUpMessage;
import com.dqys.business.orm.query.followUp.FollowUpMessageQuery;

import java.util.List;

public interface FollowUpMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FollowUpMessage record);

    int insertSelective(FollowUpMessage record);

    FollowUpMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FollowUpMessage record);

    int updateByPrimaryKey(FollowUpMessage record);

    List<FollowUpMessage> list(FollowUpMessageQuery query);

}