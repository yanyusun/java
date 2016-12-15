package com.dqys.business.service.service.partner.impl;

import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.UserDetail;
import com.dqys.business.orm.constant.partner.PartnerEnum;
import com.dqys.business.orm.mapper.partner.PartnerMapper;
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

    @Override
    public JsonResponse addPartner(Partner partner) {
        Integer userId = UserSession.getCurrent().getUserId();
        UserDetail detail = tUserInfoMapper.getUserDetail(userId);
        if (detail == null || detail.getCompanyId() == null) {
            return JsonResponseTool.failure("当前用户无权限操作");
        }
        partner.setUserId(userId);
        partner.setCompanyId(detail.getCompanyId());
        partner.setRelationStatus(PartnerEnum.relation_status_wait.getValue());
        if (partnerMapper.insertByCondition(partner) > 0) {
            return JsonResponseTool.success(null);
        } else {
            return JsonResponseTool.failure("添加合作伙伴失败");
        }
    }
}
