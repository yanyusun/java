package com.dqys.business.orm.mapper.coordinator;

import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.coordinator.team.TeamDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/7/12.
 */
public interface CoordinatorMapper {
    /**
     * 获取借款人或是资产包团队信息
     *
     * @param companyId
     * @param objectId
     * @param objectType
     * @return
     */
    List<TeamDTO> getLenderOrAsset(@Param("companyId") Integer companyId, @Param("objectId") Integer objectId, @Param("objectType") Integer objectType);

    /**
     * 获取当前进行的任务总数
     *
     * @param companyId
     * @param userId
     * @return(ongoing)
     */
    Map<String, Object> getTaskOngoing(@Param("companyId") Integer companyId, @Param("userId") Integer userId, @Param("objectType") Integer objectType);

    /**
     * 获取业绩比例
     *
     * @param companyId
     * @param userId
     * @return(finish,total)
     */
    Map<String, Object> getTaskRatio(@Param("companyId") Integer companyId, @Param("userId") Integer userId, @Param("objectType") Integer objectType);

    /**
     * 获取团队人数
     *
     * @param companyId
     * @param objectId
     * @param objectType
     * @return（type,peopleNum）
     */
    List<Map<String, Object>> getPeopleNum(@Param("companyId") Integer companyId, @Param("objectId") Integer objectId, @Param("objectType") Integer objectType);

    /**
     * 获取相关公司名称
     *
     * @return
     */
    List<Map<String, Object>> companyList(@Param("objectId") Integer objectId, @Param("objectType") Integer objectType);

    /**
     * 根据公司id获取公司的管理员信息
     *
     * @param companyId
     * @return
     */
    Map<String, Object> getAdminUser(Integer companyId);

    /**
     * 获取员工公司下的所有员工
     *
     * @param realName
     * @param userId
     * @param companyId
     */
    List<Map<String, Object>> getCompanyUserList(@Param("realName") String realName, @Param("userId") Integer userId, @Param("companyId") Integer companyId);

    /**
     * 查询团队中的协作器信息
     *
     * @param teammateId
     * @return
     */
    Map selectByUserTeamAndMateRe(Integer teammateId);

    /**
     * 获取最后留言时间
     *
     * @param userId
     * @return
     */
    Map getLastLeaveWord(Integer userId);

    /**
     * 获取业务号id
     * @param objectType
     * @param objectId
     * @return
     */
    Map selectByBusinessId(@Param("objectType")Integer objectType, @Param("objectId")Integer objectId);

    /**
     * 获取借款人
     * @param lenderInfo
     * @return
     */
    List<LenderInfo> selectByLender(LenderInfo lenderInfo);
}
