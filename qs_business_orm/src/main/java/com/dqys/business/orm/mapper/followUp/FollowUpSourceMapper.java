package com.dqys.business.orm.mapper.followUp;


import com.dqys.business.orm.pojo.followUp.FollowUpSource;
import com.dqys.business.orm.query.followUp.FollowUpSourceQuery;

import java.util.List;

public interface FollowUpSourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FollowUpSource record);

    int insertSelective(FollowUpSource record);

    FollowUpSource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FollowUpSource record);

    int updateByPrimaryKey(FollowUpSource record);

    List<FollowUpSource> list(FollowUpSourceQuery query);
    
    //// TODO: 17-1-17  mkf 
    FollowUpSource selectDetail(Integer id);
    
}