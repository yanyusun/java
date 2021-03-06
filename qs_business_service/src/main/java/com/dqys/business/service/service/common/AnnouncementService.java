package com.dqys.business.service.service.common;

import com.dqys.business.orm.pojo.common.Announcement;
import com.dqys.core.model.JsonResponse;

import java.util.List;

/**
 * Created by Yvan on 16/9/9.
 */
public interface AnnouncementService {

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    JsonResponse delete(Integer id);

    /**
     * 新增
     * @param record
     * @return
     */
    JsonResponse insert(Announcement record);

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    Announcement get(Integer id);

    /**
     * 修改
     * @param record
     * @return
     */
    JsonResponse update(Announcement record);

    /**
     * 根据用户信息查询通告
     * @param userId
     * @return
     */
    List<Announcement> listByUser(Integer userId);
}
