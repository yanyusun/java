package com.dqys.business.orm.mapper.followUp.impl;

import com.dqys.business.orm.mapper.followUp.FollowUpReadstatusMapper;
import com.dqys.business.orm.pojo.followUp.FollowUpReadstatus;
import com.dqys.core.base.BaseDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by yan on 16-8-15.
 */
@Repository
public class FollowUpReadstatusMapperImp extends BaseDao implements FollowUpReadstatusMapper{
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(FollowUpReadstatus record) {
        return 0;
    }

    @Override
    public int insertSelective(FollowUpReadstatus record) {
        return 0;
    }

    @Override
    public FollowUpReadstatus selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(FollowUpReadstatus record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(FollowUpReadstatus record) {
        return 0;
    }
}
