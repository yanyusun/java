package com.dqys.business.controller;

import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.CompanyDetailInfo;
import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.orm.query.CompanyQuery;
import com.dqys.business.orm.pojo.coordinator.CompanyRelation;
import com.dqys.business.orm.pojo.partner.ModulPartner;
import com.dqys.business.orm.pojo.partner.Partner;
import com.dqys.business.orm.pojo.partner.PartnerDTO;
import com.dqys.business.orm.pojo.partner.PartnerQuery;
import com.dqys.business.service.dto.common.UserDTO;
import com.dqys.business.service.service.partner.PartnerService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.JsonResponseTool;
import com.sun.tracing.dtrace.Attributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 合作伙伴管理
 * Created by mkfeng on 2016/12/14.
 */
@Controller
@RequestMapping("/parter")
public class PartnerController {
    @Autowired
    private PartnerService partnerService;
    @Autowired
    private TCompanyInfoMapper tCompanyInfoMapper;
    @Autowired
    private TUserInfoMapper tUserInfoMapper;

    /**
     * @api {post} parter/addPartner 添加合作伙伴
     * @apiName parter/addPartner
     * @apiSampleRequest parter/addPartner
     * @apiParam {int} partnerCompanyId 合作公司id
     * @apiGroup　 partner
     */
    @RequestMapping("/addPartner")
    @ResponseBody
    public JsonResponse addPartner(@ModelAttribute Integer partnerCompanyId) {
        CompanyRelation relation = new CompanyRelation();
        relation.setCompanyBId(partnerCompanyId);
        return partnerService.addPartner(relation);
    }

    /**
     * @api {post} parter/getCompanyId 条件获取公司信息
     * @apiName parter/getCompanyList
     * @apiSampleRequest parter/getCompanyList
     * @apiParam {string} credential 营业执照号码
     * @apiParam {string} nameLike 公司名称
     * @apiParam {string} account 清搜帐号
     * @apiGroup　 partner
     */
    @RequestMapping("/getCompanyList")
    @ResponseBody
    public JsonResponse getCompanyList(@ModelAttribute ModulPartner modulPartner) {
        Map map = new HashMap<>();
        if (modulPartner.getAccount() != null && !modulPartner.getAccount().equals("")) {
            List<TUserInfo> list = tUserInfoMapper.queryLikeAccount(modulPartner.getAccount(), modulPartner.getUserId());
            List<Integer> companyIds = new ArrayList<>();
            List<UserDTO> userList = new ArrayList<>();
            for (TUserInfo userInfo : list) {
                UserDTO dto = new UserDTO();
                dto.setId(userInfo.getId());
                dto.setName(userInfo.getAccount());
                userList.add(dto);
                companyIds.add(userInfo.getCompanyId());
            }
            map.put("userList", userList);
            if (userList.size() == 1) {
                modulPartner.getQuery().setCompanyIds(companyIds);
                map.put("companyList", tCompanyInfoMapper.queryList(modulPartner.getQuery()));
            }
        } else {
            map.put("companyList", tCompanyInfoMapper.queryList(modulPartner.getQuery()));
        }
        return JsonResponseTool.success(map);
    }

    /**
     * @api {post} partner/partnerList 合作伙伴列表
     * @apiName partner/partnerList
     * @apiSampleRequest partner/partnerList
     * @apiParam {int} relationStatus 合作状态
     * @apiGroup　 partner
     */
    @RequestMapping("/partnerList")
    @ResponseBody
    public JsonResponse partnerList(@ModelAttribute PartnerQuery query) {
        List<PartnerDTO> list = partnerService.partnerList(query);
        Map map = new HashMap<>();
        map.put("count", partnerService.partnerListCount(query));
        map.put("list", list);
        map.put("userInfo", tUserInfoMapper.getUserPart(UserSession.getCurrent().getUserId()));
        return JsonResponseTool.success(map);
    }

    /**
     * @api {post} parter/audit 合作伙伴被邀请用户的同意或拒绝或终止
     * @apiName parter/audit
     * @apiSampleRequest parter/audit
     * @apiParam {int} companyRelationId 关系表id
     * @apiParam {int} status 状态（1同意2拒绝3终止）
     * @apiGroup　 partner
     */
    @RequestMapping("/audit")
    @ResponseBody
    public JsonResponse audit(@ModelAttribute Integer status, @ModelAttribute Integer companyRelationId) {
        return partnerService.audit(status, companyRelationId);
    }


}
