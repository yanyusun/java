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
     *
     * @param teammateId
     * @param status
     * @return
     */
    Map isAccept(Integer teammateId, Integer status, Integer userId) throws BusinessLogException;

    /**
     * 主动加入案组
     *
     * @param userTeammateId
     * @param userId
     * @return
     */
    Map addTeammate(Integer userTeammateId, Integer userId) throws BusinessLogException;

    /**
     * 平台业务审核
     *
     * @param map
     * @param objectId
     * @param objectType
     * @param status
     */
    void auditBusiness(Map map, Integer userId, Integer objectId, Integer objectType, Integer status) throws BusinessLogException;

    /**
     * 借款人或资产包暂停操作
     *
     * @param map
     * @param objectId
     * @param objectType
     * @param status
     */
    void isPause(Map map, Integer objectId, Integer objectType, Integer status, Integer userId) throws BusinessLogException;

    /**
     * 获取借款人或是资产包的团队信息
     *
     * @param companyId
     * @param objectId
     * @param objectType
     * @return
     */
    List<TeamDTO> getLenderOrAsset(Integer companyId, Integer objectId, Integer objectType);

    /**
     * 获取任务业绩比例和进行任务数
     *
     * @param companyId
     * @param userId
     * @param objectType
     * @return (进行任务数：ongoing 完成数：finish 总数：total)
     */
    Map<String, Object> getTaskCount(Integer companyId, Integer userId, Integer objectType);

    /**
     * 获取对像属性
     *
     * @param objectId
     * @param objectType
     * @return (对象编号 objectNo)
     */
    Map<String, Object> getObjectProperty(Integer objectId, Integer objectType);

    /**
     * 获取消息列表标题（规则：对象类型+对象名称+业务类型）
     */
    public String getMessageTitle(Integer objectId, Integer objectType, Integer businessType);

    /**
     * 删除协作器人员
     *
     * @param userId
     * @param teamUserId
     * @param userTeamId
     * @param status
     * @param substitutionUid
     * @return
     */
    Map deleteTeammatUser(Integer userId, Integer teamUserId, Integer userTeamId, Integer status, Integer substitutionUid) throws Exception;

    /**
     * 删除该公司下所有的协作器
     *
     * @param companyId
     * @return
     */
    String delCoordinator(Integer companyId,Integer objectId,Integer objectType);

    /**
     * 获取对象名称
     *
     * @param objectType
     * @param objectId
     * @return
     */
    String getObjectName(Integer objectType, Integer objectId);
}
