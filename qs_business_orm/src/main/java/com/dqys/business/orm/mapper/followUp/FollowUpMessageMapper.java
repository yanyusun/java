package com.dqys.business.orm.mapper.followUp;


import com.dqys.business.orm.pojo.followUp.FollowUpMessage;
import com.dqys.business.orm.query.followUp.FollowUpMessageQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FollowUpMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FollowUpMessage record);

    int insertSelective(FollowUpMessage record);

    FollowUpMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FollowUpMessage record);

    int updateByPrimaryKey(FollowUpMessage record);

    List<FollowUpMessage> list(FollowUpMessageQuery query);

    Integer getTeamId(@Param("objectId") Integer objectId, @Param("objectType") Integer objectType, @Param("userId") Integer userId);
    /**
     * 查询跟进信息列表,级联用户与所在公司
     * @param query
     * @return
     */
    List<FollowUpMessage> getlistWithUserAndTeam(FollowUpMessageQuery query);

    /**
     * 查询所有与跟进信息相关联的信息
     * @param query
     * @return
     */
    List<FollowUpMessage> getlistWithALL(FollowUpMessageQuery query);

    void updateBySendStatus(FollowUpMessage followUpMessage);
}