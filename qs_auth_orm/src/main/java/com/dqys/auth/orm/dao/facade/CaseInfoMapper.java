package com.dqys.auth.orm.dao.facade;

import com.dqys.auth.orm.pojo.CaseInfo;

public interface CaseInfoMapper {
    /**
     * 删除案件
     *
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * 增加新案件
     *
     * @param caseInfo
     * @return
     */
    Integer insert(CaseInfo caseInfo);

    /**
     * 获取案件
     *
     * @param id
     * @return
     */
    CaseInfo get(Integer id);

    /**
     * 修改案件
     *
     * @param caseInfo
     * @return
     */
    Integer update(CaseInfo caseInfo);

}