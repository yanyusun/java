package com.dqys.business.controller;

import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.auth.orm.pojo.CompanyDetailInfo;
import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.query.CompanyQuery;
import com.dqys.business.orm.pojo.coordinator.CompanyRelation;
import com.dqys.business.orm.pojo.partner.Partner;
import com.dqys.business.orm.pojo.partner.PartnerQuery;
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
     * @api {post} partner/addPartner 添加合作伙伴
     * @apiName partner/addPartner
     * @apiSampleRequest partner/addPartner
     * @apiParam {int} partnerCompanyId 合作公司id
     * @apiGroup　 partner
     * @apiSuccessExample {json} Data-Response:
     */
    @RequestMapping("/addPartner")
    public JsonResponse addPartner(Integer partnerCompanyId) {
        CompanyRelation relation = new CompanyRelation();
        relation.setCompanyBId(partnerCompanyId);
        return partnerService.addPartner(relation);
    }

    /**
     * @api {post} partner/getCompanyId 条件获取合作伙伴公司id
     * @apiName partner/getCompanyId
     * @apiSampleRequest partner/getCompanyId
     * @apiParam {string} credential 营业执照号码
     * @apiParam {string} nameLike 公司名称
     * @apiGroup　 partner
     * @apiSuccessExample {json} Data-Response:
     */
    @RequestMapping("/getCompanyId")
    public JsonResponse getCompanyId(CompanyQuery query) {
        return JsonResponseTool.success(tCompanyInfoMapper.queryList(query));
    }

    /**
     * @api {post} partner/partnerList 合作伙伴列表
     * @apiName partner/partnerList
     * @apiSampleRequest partner/partnerList
     * @apiParam {int} relationStatus 合作状态
     * @apiGroup　 partner
     * @apiSuccessExample {json} Data-Response:
     */
    @RequestMapping("/partnerList")
    public JsonResponse partnerList(PartnerQuery query) {
        return JsonResponseTool.success(partnerService.partnerList(query));
    }


}
