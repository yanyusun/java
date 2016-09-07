package com.dqys.business.orm.mapper.sysNotice.impl;

import com.dqys.business.orm.mapper.followUp.FollowUpReadstatusMapper;
import com.dqys.business.orm.mapper.sysNotice.SysNoticeMapper;
import com.dqys.business.orm.pojo.sysNotice.SysNotice;
import com.dqys.business.orm.query.sysNotice.SysNoticeQuery;
import com.dqys.core.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yan on 16-8-25.
 */
@Repository
public class SysNoticeMapperImpl extends BaseDao implements SysNoticeMapper {
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(SysNoticeMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysNotice record) {
        return super.getSqlSession().getMapper(SysNoticeMapper.class).insert(record);
    }

    @Override
    public int insertSelective(SysNotice record) {
        return 0;
    }

    @Override
    public SysNotice selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(SysNotice record) {

        return super.getSqlSession().getMapper(SysNoticeMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysNotice record) {
        return super.getSqlSession().getMapper(SysNoticeMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public List<SysNotice> list(SysNoticeQuery sysNoticeQuery) {
        return super.getSqlSession().getMapper(SysNoticeMapper.class).list(sysNoticeQuery);
    }

    @Override
    public int queryCount(SysNoticeQuery sysNoticeQuery) {
        return super.getSqlSession().getMapper(SysNoticeMapper.class).queryCount(sysNoticeQuery);
    }
}
