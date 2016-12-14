package com.dqys.business.controller;

import com.dqys.business.orm.pojo.partner.Partner;
import com.dqys.business.service.service.partner.PartnerService;
import com.dqys.core.model.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mkfeng on 2016/12/14.
 */
@Controller
@RequestMapping("/partner")
public class PartnerController {
    @Autowired
    private PartnerService partnerService;

    @RequestMapping("/addPartner")
    public JsonResponse addPartner(Integer partnerCompanyId, Integer partnerUserId) {
        Partner partner = new Partner();
        partner.setPartnerCompanyId(partnerCompanyId);
        partner.setPartnerUserId(partnerUserId);
        return partnerService.addPartner(partner);
    }

}
