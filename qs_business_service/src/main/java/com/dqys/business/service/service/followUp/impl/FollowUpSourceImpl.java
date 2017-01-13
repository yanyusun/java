package com.dqys.business.service.service.followUp.impl;

import com.dqys.business.orm.mapper.followUp.FollowUpSourceMapper;
import com.dqys.business.orm.pojo.followUp.FollowUpSource;
import com.dqys.business.service.dto.followUp.FollowUpSourceDTO;
import com.dqys.business.service.service.followUp.FollowUpSourceService;
import com.dqys.business.service.utils.followUp.FollowUpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yan on 17-1-12.
 */
@Service
@Primary
public class FollowUpSourceImpl implements FollowUpSourceService{

    @Autowired
    private FollowUpSourceMapper followUpSourceMapper;


    @Override
    public void add(FollowUpSourceDTO followUpSourceDTO) {
        FollowUpSource followUpSource=FollowUpUtil.toFollowUpSource(followUpSourceDTO);
        followUpSourceMapper.insertSelective(followUpSource);
    }

    @Override
    public List<FollowUpSourceDTO> listByPid(Integer pid, Integer objectType, Integer objectId) {
        //更进录入+c端录入


        return null;
    }
}
