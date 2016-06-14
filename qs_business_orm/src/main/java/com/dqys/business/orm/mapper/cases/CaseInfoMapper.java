package com.dqys.business.orm.mapper.cases;

import com.dqys.business.orm.pojo.cases.CaseInfo;

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