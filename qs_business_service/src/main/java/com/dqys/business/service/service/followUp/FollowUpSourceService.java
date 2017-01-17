package com.dqys.business.service.service.followUp;

import com.dqys.business.orm.pojo.followUp.FollowUpSource;
import com.dqys.business.service.dto.followUp.FollowUpSourceDTO;

import java.util.List;

/**
 * Created by yan on 17-1-12.
 */
public interface FollowUpSourceService {
    void add(FollowUpSourceDTO followUpSourceDTO);

    List<FollowUpSourceDTO> listByPid(Integer pid, Integer objectType, Integer objectId);

    /**
     * 修改文件显示名称
     *
     * @param id
     * @param name 显示名
     */
    void rename(Integer id, String name);

    void del(Integer id);

    FollowUpSource getDetail(Integer id);
}
