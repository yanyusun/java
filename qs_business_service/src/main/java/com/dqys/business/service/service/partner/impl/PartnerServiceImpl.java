package com.dqys.business.service.service.partner.impl;

import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.UserDetail;
import com.dqys.business.orm.constant.partner.PartnerEnum;
import com.dqys.business.orm.mapper.company.CompanyRelationMapper;
import com.dqys.business.orm.mapper.partner.PartnerMapper;
import com.dqys.business.orm.pojo.coordinator.CompanyRelation;
import com.dqys.business.orm.pojo.partner.Partner;
import com.dqys.business.service.service.partner.PartnerService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mkfeng on 2016/12/14.
 */
@Service
public class PartnerServiceImpl implements PartnerService {
    @Autowired
    private PartnerMapper partnerMapper;
    @Autowired
    private TUserInfoMapper tUserInfoMapper;
    @Autowired
    private CompanyRelationMapper companyRelationMapper;

    @Override
    public JsonResponse addPartner(CompanyRelation relation) {
        Integer userId = UserSession.getCurrent().getUserId();
        UserDetail detail = tUserInfoMapper.getUserDetail(userId);
        if (detail == null || detail.getCompanyId() == null) {
            return JsonResponseTool.failure("当前用户无权限操作");
        }
        relation.setCompanyAId(detail.getCompanyId());
        relation.setRelationStatus(PartnerEnum.relation_status_wait.getValue());
        CompanyRelation companyRelation = companyRelationMapper.getByCompanyId(relation.getCompanyAId(), relation.getCompanyBId());
        if (companyRelation == null && companyRelationMapper.insert(relation) > 0) {
            //发送通知短信
            return JsonResponseTool.success(null);
        } else if (companyRelation != null && (companyRelation.getRelationStatus() == PartnerEnum.relation_status_refush.getValue() ||
                companyRelation.getRelationStatus() == PartnerEnum.relation_status_over.getValue())) {
            //发送通知短信
            companyRelation.setRelationStatus(PartnerEnum.relation_status_wait.getValue());
            companyRelationMapper.update(companyRelation);
            return JsonResponseTool.success(null);
        } else if (companyRelation != null && companyRelation.getRelationStatus() == PartnerEnum.relation_status_agree.getValue()) {
            return JsonResponseTool.failure("已经存在该合作伙伴");
        } else {
            return JsonResponseTool.failure("添加合作伙伴失败");
        }
    }


}
