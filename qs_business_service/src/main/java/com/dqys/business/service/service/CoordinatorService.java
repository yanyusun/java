package com.dqys.business.service.service;

import com.dqys.auth.orm.pojo.TUserTag;
import com.dqys.business.orm.pojo.coordinator.UserTeam;
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
    Map isAccept(Integer teammateId, Integer status, Integer userId, Integer operUserId) throws BusinessLogException;

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
    Map deleteTeammatUser(Integer userId, Integer teamUserId, Integer userTeamId, Integer status, Integer substitutionUid, Integer operUserId) throws Exception;

    /**
     * 删除该公司下所有的协作器
     *
     * @param companyId
     * @return
     */
    String delCoordinator(Integer companyId, Integer objectId, Integer objectType);

    /**
     * 获取对象名称
     *
     * @param objectType
     * @param objectId
     * @return
     */
    String getObjectName(Integer objectType, Integer objectId);

    /**
     * 添加流转对象
     *
     * @param userId
     * @param objectId
     * @param objectType
     * @return
     */
    Integer insetOUReationByFlowWork(Integer userId, Integer objectId, Integer objectType);

    /**
     * 判断流转对象是否需要发送短信
     *
     * @param flowId
     * @param flowType
     * @param onStatus （0可以1不能）
     * @param type     类型(31催收32律师33中介)
     * @param modify   是否对数据修改（是true否false）
     * @return //默认true 发送短信
     */
    Boolean verdictOrganization(Integer flowId, Integer flowType, Integer onStatus, Integer type, boolean modify);

    /**
     * 业务流转接口
     *
     * @param objectId      对象id
     * @param objectType    对象类型
     * @param flowId        流转对象id
     * @param flowType      流转对象类型
     * @param operType      操作类型（参考IouEnum 或PawnEnum）
     * @param companyTeamId 分配器id
     * @return
     */
    Map businessFlow(Integer objectId, Integer objectType, Integer flowId, Integer flowType, Integer operType, Integer companyTeamId);

    /**
     * @param objectId      对象id
     * @param objectType    对象类型
     * @param flowId        业务流转对象id
     * @param flowType      业务流转对象类型
     * @param operType      流转操作
     * @param receiveUserId 接收者id（请求公司）
     * @param status        状态（0拒绝1接收）
     * @return
     */
    Map sendBusinessFlowResult(Integer objectId, Integer objectType, Integer flowId, Integer flowType, Integer operType, Integer receiveUserId, Integer status);

    /**
     * 设置委托期限
     *
     * @param objectId
     * @param objectType
     * @param dateTime   时间
     * @return
     */
    Map setDeadline(Integer objectId, Integer objectType, String dateTime);

    /**
     * 根据对象id和对象类型获取相应的录入人信息
     *
     * @param objectId
     * @param objectType
     * @return
     */
    TUserTag getUserTagByObject(Integer objectId, Integer objectType);

    /**
     * 获取当前用户的协作器团队信息
     *
     * @param userId
     * @param objectId
     * @param objectType
     * @return
     */
    List<TeamDTO> getTeamList(Integer userId, Integer objectId, Integer objectType);

    /**
     * 获取员工明信片
     *
     * @param userId
     * @return
     */
    Map getUserDetail(Integer userId);

    UserTeam getTeam(Integer objectId, Integer objectType, int userId);
}
