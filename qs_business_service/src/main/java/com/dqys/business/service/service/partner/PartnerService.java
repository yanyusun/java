package com.dqys.business.service.service.partner;

import com.dqys.business.orm.pojo.partner.Partner;
import com.dqys.core.model.JsonResponse;

/**
 * Created by mkfeng on 2016/12/14.
 */
public interface PartnerService {

    /**
     * 添加合作伙伴
     *
     * @param partner
     * @return
     */
    JsonResponse addPartner(Partner partner);
}
