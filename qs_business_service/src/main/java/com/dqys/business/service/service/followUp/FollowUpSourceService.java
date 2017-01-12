package com.dqys.business.service.service.followUp;

import com.dqys.business.service.dto.followUp.FollowUpSourceDTO;

import java.util.List;

/**
 * Created by yan on 17-1-12.
 */
public interface FollowUpSourceService {
    void add(FollowUpSourceDTO followUpSourceDTO);
    List<FollowUpSourceDTO> list(Integer pid);
}
