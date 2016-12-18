package com.dqys.business.service.service.partner.impl;

import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.UserDetail;
import com.dqys.auth.orm.query.CompanyQuery;
import com.dqys.business.orm.constant.partner.PartnerEnum;
import com.dqys.business.orm.mapper.company.CompanyRelationMapper;
import com.dqys.business.orm.mapper.partner.PartnerMapper;
import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.orm.pojo.coordinator.CompanyRelation;
import com.dqys.business.orm.pojo.partner.Partner;
import com.dqys.business.orm.pojo.partner.PartnerDTO;
import com.dqys.business.orm.pojo.partner.PartnerQuery;
import com.dqys.business.service.constant.MessageEnum;
import com.dqys.business.service.service.MessageService;
import com.dqys.business.service.service.partner.PartnerService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.constant.MessageBTEnum;
import com.dqys.core.constant.SmsEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private MessageService messageService;

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
            sendSmsByRelation(userId, relation.getCompanyBId(), relation.getId(), PartnerEnum.relation_status_wait.getValue());
            return JsonResponseTool.success(null);
        } else if (companyRelation != null && (companyRelation.getRelationStatus() == PartnerEnum.relation_status_refush.getValue() ||
                companyRelation.getRelationStatus() == PartnerEnum.relation_status_over.getValue())) {
            //发送通知短信
            if (userId == companyRelation.getCompanyAId().intValue()) {
                sendSmsByRelation(userId, companyRelation.getCompanyBId(), companyRelation.getId(), PartnerEnum.relation_status_wait.getValue());
            } else {
                sendSmsByRelation(userId, companyRelation.getCompanyAId(), companyRelation.getId(), PartnerEnum.relation_status_wait.getValue());
            }
            companyRelation.setRelationStatus(PartnerEnum.relation_status_wait.getValue());
            companyRelationMapper.update(companyRelation);
            return JsonResponseTool.success(null);
        } else if (companyRelation != null && companyRelation.getRelationStatus() == PartnerEnum.relation_status_agree.getValue()) {
            return JsonResponseTool.failure("已经存在该合作伙伴");
        } else {
            return JsonResponseTool.failure("添加合作伙伴失败");
        }
    }

    /**
     * 合作伙伴发送短信通知
     *
     * @param senderId          发送者用户id
     * @param companyId         接收者公司id
     * @param companyRelationId 关系id
     * @param status            操作状态
     */
    private void sendSmsByRelation(Integer senderId, Integer companyId, Integer companyRelationId, Integer status) {
        Integer receiveId = 0;
        UserDetail detail = getUserByCompanyAdmin(companyId);
        if (detail != null) {
            receiveId = detail.getId();
        }
        UserDetail operC = tUserInfoMapper.getUserDetail(senderId);//发送者
        UserDetail userC = tUserInfoMapper.getUserDetail(receiveId);//接收者
        SmsUtil smsUtil = new SmsUtil();
        if (PartnerEnum.relation_status_wait.getValue().intValue() == status) {
            String content = smsUtil.sendSms(SmsEnum.ADD_COMPANY_RELATION.getValue(), userC.getMobile(),
                    userC.getMobile(),
                    operC.getEmail(),
                    operC.getCompanyName(),
                    operC.getMobile());
            String operUrl = MessageUtils.setOperUrl("/parter/audit?status=1&companyRelationId=" + companyRelationId, null,
                    "/parter/audit?status=2&companyRelationId=" + companyRelationId, null, null);
            messageService.add(operC.getRealName() + "申请加您为合作伙伴", content, senderId, receiveId, "合作伙伴添加", MessageEnum.TASK.getValue(), MessageBTEnum.RELATION_PARTNER.getValue(), operUrl);
        } else if (PartnerEnum.relation_status_agree.getValue().intValue() == status) {
            String content = smsUtil.sendSms(SmsEnum.COMPANY_RELATION_RESULT.getValue(), userC.getMobile(),
                    userC.getMobile(),
                    operC.getEmail(),
                    operC.getCompanyName(),
                    "同意",
                    operC.getMobile());
            messageService.add(operC.getRealName() + "同意成为合作伙伴", content, senderId, receiveId, "合作伙伴验证", MessageEnum.SERVE.getValue(), MessageBTEnum.INSIDE_RESULT.getValue(), "");
        } else if (PartnerEnum.relation_status_refush.getValue().intValue() == status) {
            String content = smsUtil.sendSms(SmsEnum.COMPANY_RELATION_RESULT.getValue(), userC.getMobile(),
                    userC.getMobile(),
                    operC.getEmail(),
                    operC.getCompanyName(),
                    "拒绝",
                    operC.getMobile());
            messageService.add(operC.getRealName() + "拒绝成为合作伙伴", content, senderId, receiveId, "合作伙伴验证", MessageEnum.SERVE.getValue(), MessageBTEnum.INSIDE_RESULT.getValue(), "");
        } else if (PartnerEnum.relation_status_over.getValue().intValue() == status) {
            String content = smsUtil.sendSms(SmsEnum.COMPANY_RELATION_RESULT.getValue(), userC.getMobile(),
                    userC.getMobile(),
                    operC.getEmail(),
                    operC.getCompanyName(),
                    "终止了",
                    operC.getMobile());
            messageService.add(operC.getRealName() + "终止合作伙伴", content, senderId, receiveId, "合作伙伴终止", MessageEnum.SERVE.getValue(), MessageBTEnum.INSIDE_RESULT.getValue(), "");
        }

    }

    /**
     * 根据公司id获取公司管理员信息
     *
     * @param companyId
     * @return
     */
    private UserDetail getUserByCompanyAdmin(Integer companyId) {
        if (companyId != null) {
            return tUserInfoMapper.getUserDetail(tUserInfoMapper.getUserByCompanyAdmin(companyId));
        }
        return null;
    }

    @Override
    public List<PartnerDTO> partnerList(PartnerQuery query) {
        query.setUserId(UserSession.getCurrent().getUserId());
        if (query.getPage() > 0) {
            query.setStartPage(query.getPage() * query.getPageCount());
        }
        List<PartnerDTO> list = partnerMapper.partnerList(query);
        return list;
    }

    @Override
    public Integer partnerListCount(PartnerQuery query) {
        query.setUserId(UserSession.getCurrent().getUserId());
        query.setIsPage(1);
        List<PartnerDTO> list = partnerMapper.partnerList(query);
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override
    public JsonResponse audit(Integer status, Integer companyRelationId) {
        Integer userId = UserSession.getCurrent().getUserId();
        CompanyRelation relation = companyRelationMapper.get(companyRelationId);
        if (relation == null) {
            return JsonResponseTool.failure("关系已不存在，操作失败");
        }
        //操作状态变为待合作是不可行，关系已经是同意或拒绝或终止了的操作是不可行
        if ((status == PartnerEnum.relation_status_wait.getValue()) ||
                (relation.getRelationStatus() == PartnerEnum.relation_status_agree.getValue() && status != PartnerEnum.relation_status_over.getValue()) ||
                (relation.getRelationStatus() == PartnerEnum.relation_status_refush.getValue()) ||
                (relation.getRelationStatus() == PartnerEnum.relation_status_over.getValue())
                ) {
            return JsonResponseTool.failure("此操作不可行，无效");
        }
        CompanyRelation companyRelation = new CompanyRelation();
        companyRelation.setId(companyRelationId);
        companyRelation.setRelationStatus(status);
        if (companyRelationMapper.update(companyRelation) > 0) {
            if (userId == companyRelation.getCompanyAId().intValue()) {
                sendSmsByRelation(userId, companyRelation.getCompanyBId(), companyRelation.getId(), status);
            } else {
                sendSmsByRelation(userId, companyRelation.getCompanyAId(), companyRelation.getId(), status);
            }
        }
        return null;
    }


}
