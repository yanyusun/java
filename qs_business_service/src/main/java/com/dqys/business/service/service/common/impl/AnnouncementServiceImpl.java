package com.dqys.business.service.service.common.impl;

import com.dqys.business.orm.mapper.common.AnnouncementMapper;
import com.dqys.business.orm.pojo.common.Announcement;
import com.dqys.business.service.service.common.AnnouncementService;
import com.dqys.core.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Yvan on 16/9/9.
 */
@Primary
@Repository
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public Integer delete(Integer id) {
        return announcementMapper.delete(id);
    }

    @Override
    public Integer insert(Announcement record) {
        Integer result = announcementMapper.insert(record);
        if(!CommonUtil.checkResult(result)){
            result = record.getId();
        }
        return result;
    }

    @Override
    public Announcement get(Integer id) {
        return announcementMapper.get(id);
    }

    @Override
    public Integer update(Announcement record) {
        return announcementMapper.update(record);
    }

    @Override
    public List<Announcement> listByUser(Integer userId) {
        List<Announcement> list = announcementMapper.listByUser(userId);
        List<Announcement> result = new ArrayList<>();

        list.forEach(announcement -> {
            String[] idArr = announcement.getIds().split(",");
            for (String s : idArr) {
                if(s.equals(userId.toString())){
                    result.add(announcement);
                }
            }
        });
        Collections.sort(result, (a,b) ->a.getCreateAt().compareTo(b.getCreateAt()));
        return result;
    }
}
