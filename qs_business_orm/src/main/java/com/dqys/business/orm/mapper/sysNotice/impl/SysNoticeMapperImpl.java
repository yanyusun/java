package com.dqys.business.orm.mapper.sysNotice.impl;

import com.dqys.business.orm.mapper.sysNotice.SysNoticeMapper;
import com.dqys.business.orm.pojo.sysNotice.SysNotice;
import com.dqys.core.base.BaseDao;

/**
 * Created by yan on 16-8-25.
 */
public class SysNoticeMapperImpl extends BaseDao implements SysNoticeMapper {
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(SysNotice record) {
        return 0;
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
        return 0;
    }

    @Override
    public int updateByPrimaryKey(SysNotice record) {
        return 0;
    }
}
