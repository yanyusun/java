package com.dqys.business.orm.mapper.sysNotice;

import com.dqys.business.orm.pojo.sysNotice.SysNotice;

public interface SysNoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysNotice record);

    int insertSelective(SysNotice record);

    SysNotice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysNotice record);

    int updateByPrimaryKey(SysNotice record);
}