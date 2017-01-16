package com.dqys.business.service.service.followUp.impl;

import com.dqys.business.orm.mapper.followUp.FollowUpSourceMapper;
import com.dqys.business.orm.pojo.followUp.FollowUpSource;
import com.dqys.business.orm.query.followUp.FollowUpSourceQuery;
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
        FollowUpSourceQuery followUpSourceQuery = new FollowUpSourceQuery();
        followUpSourceQuery.setPid(pid);
        followUpSourceQuery.setObjectId(objectId);
        followUpSourceQuery.setObjectType(objectType);
        List<FollowUpSource> list=followUpSourceMapper.list(followUpSourceQuery);
        return FollowUpUtil.toFollowUpSourceDTOList(list);
    }

    @Override
    public void rename(Integer id, String name) {
        FollowUpSource followUpSource = new FollowUpSource();
        followUpSource.setId(id);
        followUpSource.setShowFilename(name);
        followUpSourceMapper.updateByPrimaryKeySelective(followUpSource);
    }

    @Override
    public void del(Integer id) {
        followUpSourceMapper.deleteByPrimaryKey(id);
    }
}
