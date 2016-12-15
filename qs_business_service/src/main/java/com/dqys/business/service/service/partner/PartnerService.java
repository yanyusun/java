package com.dqys.business.service.service.partner;

import com.dqys.auth.orm.query.CompanyQuery;
import com.dqys.business.orm.pojo.coordinator.CompanyRelation;
import com.dqys.core.model.JsonResponse;

/**
 * Created by mkfeng on 2016/12/14.
 */
public interface PartnerService {

    /**
     * 添加合作伙伴
     *
     * @param relation
     * @return
     */
    JsonResponse addPartner(CompanyRelation relation);


}
