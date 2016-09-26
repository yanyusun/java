package com.dqys.business.orm.mapper.common;

import com.dqys.business.orm.pojo.common.Announcement;

import java.util.List;

public interface AnnouncementMapper {
    
    Integer delete(Integer id);

    Integer insert(Announcement record);

    Announcement get(Integer id);

    Integer update(Announcement record);

    List<Announcement> list();

    List<Announcement> listByUser(Integer id);

}