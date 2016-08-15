package com.dqys.business.orm.mapper.followUp.impl;

import com.dqys.business.orm.mapper.followUp.FollowUpMessageMapper;
import com.dqys.business.orm.pojo.followUp.FollowUpMessage;
import com.dqys.core.base.BaseDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * Created by yan on 16-8-12.
 */
@Repository
@Primary
public class FollowUpMessageMapperImpl extends BaseDao implements FollowUpMessageMapper{
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(FollowUpMessage record) {
        return super.getSqlSession().getMapper(FollowUpMessageMapper.class).insert(record);
    }

    @Override
    public int insertSelective(FollowUpMessage record) {
        return 0;
    }

    @Override
    public FollowUpMessage selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(FollowUpMessage record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(FollowUpMessage record) {
        return 0;
    }
}
