package com.dqys.business.service.service.sysNotice;

import com.dqys.business.orm.pojo.sysNotice.SysNotice;
import com.dqys.business.orm.query.sysNotice.SysNoticeQuery;

import java.util.List;
import java.util.Map;

/**
 *
 * Created by yan on 16-8-25.
 */
public interface SysNoticeService {
    /**
     *
     * @param sysNoticeQuery
     * @return
     */
    List<SysNotice> list(SysNoticeQuery sysNoticeQuery);

    /**
     *
     * @param sysNotice
     * @return
     */
    int insert(SysNotice sysNotice);

    /**
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(SysNotice record);

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SysNotice record);

    int queryCount(SysNoticeQuery sysNoticeQuery);


}
