package com.dqys.business.service.service.partner;

import com.dqys.auth.orm.query.CompanyQuery;
import com.dqys.business.orm.pojo.coordinator.CompanyDTO;
import com.dqys.business.orm.pojo.coordinator.CompanyRelation;
import com.dqys.business.orm.pojo.partner.Partner;
import com.dqys.business.orm.pojo.partner.PartnerDTO;
import com.dqys.business.orm.pojo.partner.PartnerQuery;
import com.dqys.core.model.JsonResponse;

import java.util.List;

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


    List<PartnerDTO> partnerList(PartnerQuery query);

    Integer partnerListCount(PartnerQuery query);
}
