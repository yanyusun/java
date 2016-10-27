package com.dqys.business.orm.mapper.common.impl;

import com.dqys.business.orm.mapper.common.AnnouncementMapper;
import com.dqys.business.orm.pojo.common.Announcement;
import com.dqys.core.base.BaseDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yan on 16-10-27.
 */
@Repository
@Primary
public class NavUnviewCompanyMapperImpl extends BaseDao implements AnnouncementMapper {
    @Override
    public Integer delete(Integer id) {
        return null;
    }

    @Override
    public Integer insert(Announcement record) {
        return null;
    }

    @Override
    public Announcement get(Integer id) {
        return null;
    }

    @Override
    public Integer update(Announcement record) {
        return null;
    }

    @Override
    public List<Announcement> list() {
        return null;
    }

    @Override
    public List<Announcement> listByUser(Integer id) {
        return null;
    }
}
