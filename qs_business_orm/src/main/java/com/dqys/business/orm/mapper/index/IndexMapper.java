package com.dqys.business.orm.mapper.index;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created by mkfeng on 2016/8/1.s
 */
public interface IndexMapper {
    /**
     * 待完成任务
     *
     * @param userId
     * @return
     */
    Map receive(Integer userId);

    /**
     * 获取借款人自己分配和公司分配的任务
     */
    Map getLenderAllotByOneSelfAndCompany(@Param("repayStatus") Integer repayStatus, @Param("objectType") Integer objectType, @Param("userId") Integer userId);

    /**
     * 获取借款人团队分配的任务
     */
    Map getLenderAllotByTeam(@Param("repayStatus") Integer repayStatus, @Param("objectType") Integer objectType, @Param("userId") Integer userId);

    /**
     * 获取参与的任务
     * @param objectType
     * @param userId
     * @return
     */
    Map getJoinTask(@Param("objectType") Integer objectType, @Param("userId") Integer userId);
}
