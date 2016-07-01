package com.dqys.business.service.service;

import com.dqys.business.service.dto.user.UserInsertDTO;
import com.dqys.business.service.query.user.UserListQuery;
import com.dqys.core.model.JsonResponse;

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
     * @param ids
     * @param id
     * @return
     */
    JsonResponse assignedBatch(String ids, Integer id);

    /**
     * 批量修改状态
     * @param ids
     * @param id
     * @return
     */
    JsonResponse statusBatch(String ids, Integer id);

}
