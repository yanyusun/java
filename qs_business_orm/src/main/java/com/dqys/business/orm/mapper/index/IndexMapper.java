package com.dqys.business.orm.mapper.index;

import com.dqys.business.orm.pojo.index.UserMessage;
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
     * 只有在协作器中是参与者身份的
     *
     * @param objectType
     * @param userId
     * @return
     */
    Map getJoinTask(@Param("objectType") Integer objectType, @Param("userId") Integer userId);

    /**
     * 获取抵押物团队分配的未完成任务
     */
    Map getPawnAllotByTeamIsUnfinished(@Param("objectType") Integer objectType, @Param("userId") Integer userId);

    /**
     * 获取抵押物自己分配和公司分配的未完成任务
     */
    Map getPawnAllotByOCIsUnfinished(@Param("repayStatus") Integer repayStatus, @Param("objectType") Integer objectType, @Param("userId") Integer userId);

    Map getPawnTotalTask(@Param("objectType") Integer objectType, @Param("userId") Integer userId);

    /**
     * 获取用户的相关信息
     *
     * @param userId
     * @return
     */
    UserMessage selectByUser(Integer userId);

    /**
     * 获取公司下某天登入的人数
     *
     * @param time
     * @param companyId
     * @return
     */
    Integer getLoginByTime(@Param("time") String time, @Param("companyId") Integer companyId);

    /**
     * 获取公司下未分配部门的员工人数
     *
     * @param companyId
     * @return
     */
    Integer getAbsent(Integer companyId);
}
