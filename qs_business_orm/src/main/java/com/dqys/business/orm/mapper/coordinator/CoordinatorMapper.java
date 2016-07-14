package com.dqys.business.orm.mapper.coordinator;

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
    Map<String, Object> getTaskOngoing(@Param("companyId") Integer companyId, @Param("userId") Integer userId,@Param("objectType") Integer objectType);

    /**
     * 获取业绩比例
     *
     * @param companyId
     * @param userId
     * @return(finish,total)
     */
    Map<String, Object> getTaskRatio(@Param("companyId") Integer companyId, @Param("userId") Integer userId,@Param("objectType") Integer objectType);

    /**
     * 获取团队人数
     * @param companyId
     * @param objectId
     * @param objectType
     * @return（type,peopleNum）
     */
    List<Map<String,Object>> getPeopleNum(@Param("companyId") Integer companyId, @Param("objectId") Integer objectId,@Param("objectType") Integer objectType);
}
