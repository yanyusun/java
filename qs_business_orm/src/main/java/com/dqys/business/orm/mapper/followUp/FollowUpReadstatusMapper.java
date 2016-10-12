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
    List<Integer> selectByUseridList(@Param("objectId") int objectId, @Param("objectType") int objectType);

    /**
     * 条件删除表t_follow_up_readstatus的记录
     *
     * @param objectId
     * @param objectType
     * @param liquidateStage
     */
    void deleteByOOL(@Param("objectId") int objectId, @Param("objectType") int objectType, @Param("liquidateStage") int liquidateStage,@Param("userId") int userId);

    List<Map<String, String>> getCountMap(@Param("objectId") int objectId, @Param("objectType") int objectType, @Param("userId") int userId);

    /**
     * 获取表object_user_relation 的userid
     *
     * @return
     */
    Integer countByTypeIdUser(@Param("objectId")Integer objectId, @Param("objectType")Integer objectType,
                              @Param("userId")Integer userId);
}