package com.dqys.business.controller;

import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.CompanyDetailInfo;
import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.orm.query.CompanyQuery;
import com.dqys.business.orm.pojo.coordinator.CompanyRelation;
import com.dqys.business.orm.pojo.partner.Partner;
import com.dqys.business.orm.pojo.partner.PartnerDTO;
import com.dqys.business.orm.pojo.partner.PartnerQuery;
import com.dqys.business.service.dto.common.UserDTO;
import com.dqys.business.service.service.partner.PartnerService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private TUserInfoMapper tUserInfoMapper;

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
     * @api {post} partner/getCompanyId 条件获取公司信息
     * @apiName partner/getCompanyList
     * @apiSampleRequest partner/getCompanyList
     * @apiParam {string} credential 营业执照号码
     * @apiParam {string} nameLike 公司名称
     * @apiParam {string} account 清搜帐号
     * @apiGroup　 partner
     * @apiSuccessExample {json} Data-Response:
     */
    @RequestMapping("/getCompanyList")
    public JsonResponse getCompanyList(CompanyQuery query, String account) {
        Map map = new HashMap<>();
        if (account != null && !account.equals("")) {
            List<TUserInfo> list = tUserInfoMapper.queryLikeAccount(account);
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
                query.setCompanyIds(companyIds);
                map.put("companyList", tCompanyInfoMapper.queryList(query));
            }
        } else {
            map.put("companyList", tCompanyInfoMapper.queryList(query));
        }
        return JsonResponseTool.success(map);
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
        List<PartnerDTO> list = partnerService.partnerList(query);
        Map map = new HashMap<>();
        map.put("count", partnerService.partnerListCount(query));
        map.put("list", list);
        map.put("userInfo", tUserInfoMapper.getUserPart(UserSession.getCurrent().getUserId()));
        return JsonResponseTool.success(map);
    }


}
