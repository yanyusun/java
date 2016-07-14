package com.dqys.business.service.service;

import com.dqys.business.orm.pojo.businessLog.BusinessLog;
import com.dqys.business.orm.query.businessLog.BusinessLogQuery;
import com.dqys.core.model.JsonResponse;

import java.util.List;

/**
 * Created by yan on 16-7-13.
 */
public interface BusinessLogService {
    /**
     * 分页获取资产包
     *
     * @return
     */
    List<BusinessLog> list(BusinessLogQuery query);

}
