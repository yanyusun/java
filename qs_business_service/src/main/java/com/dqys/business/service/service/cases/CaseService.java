package com.dqys.business.service.service.cases;

import com.dqys.business.orm.pojo.cases.CaseInfo;
import org.springframework.stereotype.Service;

/**
 * Created by Yvan on 16/7/26.
 */
@Service
public interface CaseService {
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
