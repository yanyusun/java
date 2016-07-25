package com.dqys.business.service.service;

import com.dqys.business.orm.pojo.coordinator.team.TeamDTO;
import com.dqys.business.service.exception.bean.BusinessLogException;

import java.util.List;

import java.util.Map;

/**
 * Created by mkfeng on 2016/7/12.
 */
public interface CoordinatorService {

    /**
     * 借款人信息或资产包信息和团队列表
     *
     * @param map
     * @param companyId
     * @param objectId
     * @param objectType
     */
    void readByLenderOrAsset(Map<String, Object> map, Integer companyId, Integer objectId, Integer objectType, Integer userid);

    /**
     * 查询公司下所有的员工
     *
     * @param realName
     * @return
     */
    Map<String, Object> getCompanyUserList(String realName, Integer userId, Integer companyId);

    /**
     * 添加邀请人
     */
    Map addTeammate(Integer userTeamId, Integer userId, String remark, Integer[] userIds) throws BusinessLogException;

    /**
     * 是否同意邀请
     * @param teammateId
     * @param status
     * @return
     */
    Map isAccept(Integer teammateId, Integer status) throws BusinessLogException;

    /**
     * 主动加入案组
     * @param userTeammateId
     * @param userId
     * @return
     */
    Map addTeammate(Integer userTeammateId, Integer userId) throws BusinessLogException;

    List<TeamDTO> getLenderOrAsset(Integer companyId, Integer objectId, Integer objectType);
}
