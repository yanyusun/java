package com.dqys.business.service.service;

import com.dqys.business.service.dto.user.UserInsertDTO;
import com.dqys.business.service.dto.user.UserListDTO;
import com.dqys.business.service.query.user.UserListQuery;
import com.dqys.core.model.JsonResponse;

import java.util.List;

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
    UserInsertDTO get(Integer id);

    /**
     * 增加用户
     *
     * @param data
     * @return
     */
    Integer add(UserInsertDTO data);
}
