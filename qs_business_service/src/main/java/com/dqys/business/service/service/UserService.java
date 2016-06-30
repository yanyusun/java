package com.dqys.business.service.service;

import com.dqys.business.service.dto.company.UserListDTO;
import com.dqys.business.service.query.user.UserListQuery;
import com.dqys.core.model.JsonResponse;

import java.util.List;

/**
 * Created by Yvan on 16/6/29.
 */
public interface UserService {
    /**
     * 带条件遍历公司旗下所有成员
     * @param query
     * @param companyId
     * @return
     */
    List<UserListDTO> queryList(UserListQuery query, Integer companyId);

    /**
     * 获取单条用户信息
     * @param id
     * @return
     */
    UserListDTO get(Integer id);
}
