package com.dqys.business.orm.mapper.coordinator;

import com.dqys.business.orm.pojo.asset.AssetInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.coordinator.UserDetail;
import com.dqys.business.orm.pojo.coordinator.team.TeamDTO;
import com.dqys.business.orm.pojo.zcy.dto.ZcyPawnDTO;
import com.dqys.business.orm.query.coordinator.ZcyListQuery;
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
     * @return id:用户id，realName:真实姓名，mobile:手机号，userName:用户名
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
     *
     * @param objectType
     * @param objectId
     * @return
     */
    Map selectByBusinessId(@Param("objectType") Integer objectType, @Param("objectId") Integer objectId);

    /**
     * 获取借款人
     *
     * @param lenderInfo
     * @return
     */
    List<LenderInfo> selectByLender(LenderInfo lenderInfo);

    /**
     * 获取借款人信息
     *
     * @param objectId
     * @return
     */
    LenderInfo getLenderInfo(Integer objectId);

    /**
     * 获取资产包信息
     *
     * @param objectId
     * @return
     */
    AssetInfo getAssetInfo(Integer objectId);

    /**
     * 修改借款人信息
     *
     * @param lenderInfo
     * @return
     */
    Integer updateLender(LenderInfo lenderInfo);

    /**
     * 根据借款人获取操作员id
     *
     * @param objectType
     * @param objectId
     * @return
     */
    List<Map<String, Object>> getUserIdByObjUserRelToLender(@Param("objectType") Integer objectType, @Param("objectId") Integer objectId);

    /**
     * 根据资产包获取操作员id
     *
     * @param objectType
     * @param objectId
     * @return
     */
    List<Map<String, Object>> getUserIdByObjUserRelToAsset(@Param("objectType") Integer objectType, @Param("objectId") Integer objectId);

    /**
     * 根据用户id获取相应的用户信息和公司信息
     * 返回Map  参数（userId 用户id，mobile 手机号，email 邮箱，realName 真实姓名，rold 角色ID，userType 用户类型，companyName 公司名称，companyType 公司类型）
     */
    Map<String, Object> getUserAndCompanyByUserId(Integer userId);

    /**
     * 根据对象类型，用户id，状态 获取对应的状态的对象id
     *
     * @param objectType
     * @param userId
     * @param status
     * @return
     */
    List<Integer> getObjectIdList(@Param("objectType") Integer objectType, @Param("userId") Integer userId, @Param("status") Integer status);

    /**
     * 通过协作器的对象类型和对象id和用户类型 获取姓名和用户id
     *
     * @param objectType 对象类型
     * @param objectId   对像id
     * @param type       人员类别（管理者0|所属人1|参与者2）
     * @return （user_id用户id，real_name真实姓名）
     */
    Map<String, Object> getRealName(@Param("objectType") Integer objectType, @Param("objectId") Integer objectId, @Param("type") Integer type);

    /**
     * 资产源信息
     *
     * @return
     */
    List<ZcyPawnDTO> selectByZCYListPage(@Param("zcyListQuery") ZcyListQuery zcyListQuery);

    Integer selectByZCYListPageCount(@Param("zcyListQuery") ZcyListQuery zcyListQuery);

    /**
     * 获取资产信息标签
     *
     * @param estatesId
     * @return
     */
    List<Map<String, Object>> findLabel(Integer estatesId);

    /**
     * 资产源待分配查询对象id
     *
     * @param userId
     * @param objectType
     * @return
     */
    List<Integer> findObjectIdByOURelation(@Param("userId") Integer userId, @Param("objectType") Integer objectType);

    /**
     * 资产源正在处置查询对象id
     *
     * @param userId
     * @param objectType
     * @return
     */
    List<Integer> findObjectIdByOURelationAndBusiness(@Param("userId") Integer userId, @Param("objectType") Integer objectType);

    /**
     * 根据对象类型和对象id获取分配器中的公司管理员
     *
     * @param objectId
     * @param objectType
     * @return
     */
    Map getCompanyAndUser(@Param("objectId") Integer objectId, @Param("objectType") Integer objectType, @Param("userType") Integer userType);

    /**
     * 根据对象id和对象类型获取分配器成员
     *
     * @param objectId
     * @param objectType
     * @return
     */
    List<Map> findUserTypeByCompanyTeam(@Param("objectId") Integer objectId, @Param("objectType") Integer objectType);

    /**
     * 获取资产源全部
     *
     * @param userId
     * @param objectType
     * @return
     */
    List<Integer> findObjectIdByAssetSourceAll(@Param("userId") Integer userId, @Param("objectType") Integer objectType);

    /**
     * 获取员工相关信息
     *
     * @param userId
     * @return
     */
    UserDetail getUserDetail(Integer userId);

    /**
     * 获取资料实堪的资产源数量
     */
    Integer getShiKanCount(Integer estatesId);
}
