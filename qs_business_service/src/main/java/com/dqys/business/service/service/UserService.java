package com.dqys.business.service.service;

import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.business.orm.pojo.coordinator.UserDetail;
import com.dqys.business.service.dto.user.UserInsertDTO;
import com.dqys.business.service.query.user.UserListQuery;
import com.dqys.core.model.JsonResponse;

import java.util.List;
import java.util.Map;

/**
 * Created by Yvan on 16/6/29.
 */
public interface UserService {

    /**
     * 带条件遍历公司旗下所有成员
     *
     * @param query
     * @return
     */
    JsonResponse queryList(UserListQuery query);

    /**
     * 获取单条用户信息
     *
     * @param id
     * @return
     */
    JsonResponse get(Integer id);

    /**
     * 增加用户
     *
     * @param data
     * @return
     */
    JsonResponse add(UserInsertDTO data);

    /**
     * 修改用户信息
     *
     * @param userInsertDTO
     * @return
     */
    JsonResponse update(UserInsertDTO userInsertDTO);

    /**
     * 删除用户
     *
     * @param uId
     * @return
     */
    JsonResponse delete(Integer uId);

    /**
     * 批量分配
     *
     * @param ids
     * @param id
     * @return
     */
    JsonResponse assignedBatch(String ids, Integer id);

    /**
     * 批量修改状态
     *
     * @param ids
     * @param id
     * @return
     */
    JsonResponse statusBatch(String ids, Integer id);

    /**
     * 发送激活提醒信息(暂时不用)
     *
     * @param ids
     * @return
     */
    JsonResponse sendMsg(List<Integer> ids);

    /**
     * 批量重置密码(需要管理员权限)
     *
     * @param ids
     * @return
     */
    JsonResponse setPwdBatch(List<Integer> ids);

    /**
     * 重置密码(需要管理员权限)
     *
     * @param id
     * @param pwd
     * @return
     */
    JsonResponse setPwd(Integer id, String pwd);

    /**
     * excel导入用户数据
     *
     * @param file
     * @param sendSmsStatus 值为1表示发送短信通知
     * @param accountStatus 值为1表示不验证库里的帐号唯一性
     * @return
     */
    JsonResponse excelImport_tx(String file, Integer sendSmsStatus, Integer accountStatus) throws Exception;

    /**
     * 根据当前用户查出公司
     *
     * @param id
     * @return
     */
    TCompanyInfo getCompanyByUserId(Integer id);

    /**
     * 留言操作
     *
     * @param userId
     * @param content
     * @return
     */
    Map leaveWord(Integer userId, String content);

    /**
     * 注册用户的审核
     *
     * @param userId
     * @param status
     * @return
     */
    Map registerAudit(Integer userId, Integer status);

    /**
     * 帐号激活提醒
     *
     * @param userIds
     * @return
     */
    Map activateReminder(List<Integer> userIds);

    /**
     * 设置帐号使用状态
     *
     * @param userIds
     * @param useStatus
     * @return
     */
    Map updateAccountUse(List<Integer> userIds, Integer useStatus);

    //人员角色
    String getRoleNameToString(UserDetail user);

    //参与方类型
    String getCompayTypeToString(UserDetail user);
}
