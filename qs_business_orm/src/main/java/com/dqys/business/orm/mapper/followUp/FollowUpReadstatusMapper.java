package com.dqys.business.orm.mapper.followUp;


import com.dqys.business.orm.pojo.followUp.FollowUpReadstatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FollowUpReadstatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FollowUpReadstatus record);

    int insertSelective(FollowUpReadstatus record);

    FollowUpReadstatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FollowUpReadstatus record);

    int updateByPrimaryKey(FollowUpReadstatus record);

    /**
     * 获取表object_user_relation 的userid
     *
     * @return
     */
    List<Integer> selectByUseridList();

    /**
     * 条件删除表t_follow_up_readstatus的记录
     *
     * @param objectId
     * @param objectType
     * @param liquidateStage
     */
    void deleteByOOL(@Param("objectId") int objectId, @Param("objectType") int objectType, @Param("liquidateStage") int liquidateStage);

    Map<String, String> getCountMap(@Param("objectId") int objectId, @Param("objectType") int objectType, @Param("userId") int userId);
}