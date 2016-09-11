package com.dqys.business.orm.mapper.common.impl;

import com.dqys.business.orm.mapper.common.AnnouncementMapper;
import com.dqys.business.orm.pojo.common.Announcement;
import com.dqys.core.base.BaseDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yvan on 16/8/31.
 */
@Repository
@Primary
public class AnnouncementMapperImpl extends BaseDao implements AnnouncementMapper {

    @Override
    public Integer delete(Integer id) {
        return super.getSqlSession().getMapper(AnnouncementMapper.class).delete(id);
    }

    @Override
    public Integer insert(Announcement record) {
        return super.getSqlSession().getMapper(AnnouncementMapper.class).insert(record);
    }

    @Override
    public Announcement get(Integer id) {
        return super.getSqlSession().getMapper(AnnouncementMapper.class).get(id);
    }

    @Override
    public Integer update(Announcement record) {
        return super.getSqlSession().getMapper(AnnouncementMapper.class).update(record);
    }

    @Override
    public List<Announcement> list() {
        return super.getSqlSession().getMapper(AnnouncementMapper.class).list();
    }
}
