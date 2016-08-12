package com.dqys.business.service.service.followUp.impl;

import com.dqys.business.orm.mapper.followUp.FollowUpMessageMapper;
import com.dqys.business.orm.pojo.followUp.FollowUpMessage;
import com.dqys.business.service.service.followUp.FollowUpMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Created by yan on 16-8-12.
 */
@Service
@Primary
public class FollowUpMessageServiceImpl implements FollowUpMessageService{
    @Autowired
    private FollowUpMessageMapper followUpMessageMapper;

    @Override
    public int insert(FollowUpMessage record) {
        return followUpMessageMapper.insert(record);
    }
}
