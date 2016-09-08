package com.dqys.business.service.service.sysNotice.impl;

import com.dqys.business.orm.mapper.sysNotice.SysNoticeMapper;
import com.dqys.business.orm.pojo.sysNotice.SysNotice;
import com.dqys.business.orm.query.sysNotice.SysNoticeQuery;
import com.dqys.business.service.service.sysNotice.SysNoticeService;
import com.dqys.core.model.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yan on 16-8-25.
 */
@Service
@Primary
public class SysNoticeServiceImpl implements SysNoticeService{
    @Autowired
    private SysNoticeMapper mapper;

    /**
     *
     * @param sysNoticeQuery
     * @return
     */
    @Cacheable(value="sys_notice_cache",key="#sysNoticeQuery.getStartPageNum()+'_'+#sysNoticeQuery.getPageSize()")
    @Override
    public List<SysNotice> list(SysNoticeQuery sysNoticeQuery) {
        return mapper.list(sysNoticeQuery);
    }

    @CacheEvict(value="sys_notice_cache",allEntries=true)
    @Override
    public int insert(SysNotice sysNotice) {
        //sysNotice.setUserId(UserSession.getCurrent().getUserId());
        sysNotice.setUserId(1);
        return mapper.insert(sysNotice);
    }

    @CacheEvict(value="sys_notice_cache",allEntries=true)
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value="sys_notice_cache",allEntries=true)
    @Override
    public int updateByPrimaryKey(SysNotice record) {
        return mapper.updateByPrimaryKey(record);
    }

    @CacheEvict(value="sys_notice_cache",allEntries=true)
    @Override
    public int updateByPrimaryKeySelective(SysNotice record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Cacheable(value="sys_notice_cache",key="sys_notice_cache_query_count")
    @Override
    public int queryCount(SysNoticeQuery sysNoticeQuery) {
        return mapper.queryCount(sysNoticeQuery);
    }


}
