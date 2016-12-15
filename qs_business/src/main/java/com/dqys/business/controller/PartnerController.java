package com.dqys.business.controller;

import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.auth.orm.pojo.CompanyDetailInfo;
import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.query.CompanyQuery;
import com.dqys.business.orm.pojo.coordinator.CompanyRelation;
import com.dqys.business.orm.pojo.partner.Partner;
import com.dqys.business.service.service.partner.PartnerService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 合作伙伴管理
 * Created by mkfeng on 2016/12/14.
 */
@Controller
@RequestMapping("/partner")
public class PartnerController {
    @Autowired
    private PartnerService partnerService;
    @Autowired
    private TCompanyInfoMapper tCompanyInfoMapper;

    /**
     * 添加合作伙伴
     *
     * @param partnerCompanyId
     * @return
     */
    @RequestMapping("/addPartner")
    public JsonResponse addPartner(Integer partnerCompanyId) {
        CompanyRelation relation = new CompanyRelation();
        relation.setCompanyBId(partnerCompanyId);
        return partnerService.addPartner(relation);
    }

    /**
     * 条件获取合作伙伴公司id
     *
     * @return
     */
    @RequestMapping("/getCompanyId")
    public JsonResponse getCompanyId(CompanyQuery query) {
        return JsonResponseTool.success(tCompanyInfoMapper.queryList(query));
    }



}
