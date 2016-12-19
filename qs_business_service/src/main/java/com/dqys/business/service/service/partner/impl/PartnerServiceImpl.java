package com.dqys.business.service.service.partner.impl;

import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.pojo.TCompanyInfoDTO;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.orm.pojo.UserDetail;
import com.dqys.auth.orm.query.CompanyQuery;
import com.dqys.business.orm.constant.partner.PartnerEnum;
import com.dqys.business.orm.mapper.company.CompanyRelationMapper;
import com.dqys.business.orm.mapper.partner.PartnerMapper;
import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.orm.pojo.coordinator.CompanyRelation;
import com.dqys.business.orm.pojo.partner.ModulPartner;
import com.dqys.business.orm.pojo.partner.Partner;
import com.dqys.business.orm.pojo.partner.PartnerDTO;
import com.dqys.business.orm.pojo.partner.PartnerQuery;
import com.dqys.business.service.constant.MessageEnum;
import com.dqys.business.service.dto.common.UserDTO;
import com.dqys.business.service.service.MessageService;
import com.dqys.business.service.service.partner.PartnerService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.constant.MessageBTEnum;
import com.dqys.core.constant.SmsEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.AreaTool;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private TCompanyInfoMapper tCompanyInfoMapper;

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
            sendSmsByRelation(userId, relation.getCompanyBId(), relation, PartnerEnum.relation_status_wait.getValue());
            return JsonResponseTool.success(null);
        } else if (companyRelation != null && (companyRelation.getRelationStatus() == PartnerEnum.relation_status_refush.getValue() ||
                companyRelation.getRelationStatus() == PartnerEnum.relation_status_over.getValue())) {
            //发送通知短信
            if (userId == companyRelation.getCompanyAId().intValue()) {
                sendSmsByRelation(userId, companyRelation.getCompanyBId(), companyRelation, PartnerEnum.relation_status_wait.getValue());
            } else {
                sendSmsByRelation(userId, companyRelation.getCompanyAId(), companyRelation, PartnerEnum.relation_status_wait.getValue());
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
     * @param senderId  发送者用户id
     * @param companyId 接收者公司id
     * @param relation  关系id
     * @param status    操作状态
     */
    private void sendSmsByRelation(Integer senderId, Integer companyId, CompanyRelation relation, Integer status) {
        Integer receiveId = 0;
        UserDetail detail = getUserByCompanyAdmin(companyId);
        if (detail != null) {
            receiveId = detail.getId();
        }
        UserDetail operC = tUserInfoMapper.getUserDetail(senderId);//发送者
        UserDetail userC = tUserInfoMapper.getUserDetail(receiveId);//接收者
        SmsUtil smsUtil = new SmsUtil();
        if (relation.getRelationStatus() == PartnerEnum.relation_status_wait.getValue().intValue() && PartnerEnum.relation_status_wait.getValue().intValue() == status) {
            String content = smsUtil.sendSms(SmsEnum.ADD_COMPANY_RELATION.getValue(), userC.getMobile(),
                    userC.getRealName(),
                    operC.getEmail(),
                    operC.getCompanyName(),
                    "请求",
                    operC.getMobile());
            String operUrl = MessageUtils.setOperUrl("/parter/audit?status=1&companyRelationId=" + relation.getId(), null,
                    "/parter/audit?status=2&companyRelationId=" + relation.getId(), null, null);
            messageService.add(operC.getRealName() + "申请加您为合作伙伴", content, senderId, receiveId, "合作伙伴添加", MessageEnum.TASK.getValue(), MessageBTEnum.RELATION_PARTNER.getValue(), operUrl);
        } else if (relation.getRelationStatus() == PartnerEnum.relation_status_over.getValue().intValue() && PartnerEnum.relation_status_wait.getValue().intValue() == status) {
            String content = smsUtil.sendSms(SmsEnum.ADD_COMPANY_RELATION.getValue(), userC.getMobile(),
                    userC.getRealName(),
                    operC.getEmail(),
                    operC.getCompanyName(),
                    "重启了",
                    operC.getMobile());
            String operUrl = MessageUtils.setOperUrl("/parter/audit?status=1&companyRelationId=" + relation.getId(), null,
                    "/parter/audit?status=2&companyRelationId=" + relation.getId(), null, null);
            messageService.add(operC.getRealName() + "重启加您为合作伙伴", content, senderId, receiveId, "合作伙伴重启", MessageEnum.TASK.getValue(), MessageBTEnum.RELATION_PARTNER.getValue(), operUrl);
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
            query.setStartPage((query.getPage() - 1) * query.getPageCount());
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
            companyRelation = companyRelationMapper.get(companyRelationId);
            if (userId == companyRelation.getCompanyAId().intValue()) {
                sendSmsByRelation(userId, companyRelation.getCompanyBId(), companyRelation, status);
            } else {
                sendSmsByRelation(userId, companyRelation.getCompanyAId(), companyRelation, status);
            }
            return JsonResponseTool.success(null);
        }
        return null;
    }

    @Override
    public void getCompanyList(ModulPartner modulPartner, Map map) {
        List<UserDTO> userList = new ArrayList<>();
        List<TCompanyInfo> companyList = new ArrayList<>();
        if ((modulPartner.getAccount() != null && !modulPartner.getAccount().equals("")) || modulPartner.getUserId() != null) {
            List<TUserInfo> list = tUserInfoMapper.queryLikeAccount(modulPartner.getAccount(), modulPartner.getUserId());
            List<Integer> companyIds = new ArrayList<>();
            for (TUserInfo userInfo : list) {
                UserDTO dto = new UserDTO();
                dto.setId(userInfo.getId());
                dto.setName(userInfo.getAccount());
                userList.add(dto);
                companyIds.add(userInfo.getCompanyId());
            }
            if (userList.size() == 1) {
                modulPartner.setQuery(new CompanyQuery());
                modulPartner.getQuery().setCompanyIds(companyIds);
                companyList = tCompanyInfoMapper.queryList(modulPartner.getQuery());
            }
        } else {
            companyList = tCompanyInfoMapper.queryList(modulPartner.getQuery());
            map.put("companyList", companyList);
            if (companyList != null & companyList.size() == 1) {
                TCompanyInfo info = companyList.get(0);
                Integer userId = tUserInfoMapper.getUserByCompanyAdmin(info.getId());
                if (userId != null) {
                    UserDetail detail = tUserInfoMapper.getUserDetail(userId);
                    if (detail != null) {
                        UserDTO dto = new UserDTO();
                        dto.setId(detail.getId());
                        dto.setName(detail.getAccount());
                        userList.add(dto);
                    }
                }
            }
        }
        List<TCompanyInfoDTO> list = new ArrayList<>();
        if (companyList != null) {
            for (TCompanyInfo info : companyList) {
                TCompanyInfoDTO dto = new TCompanyInfoDTO();
                dto.setProvinceName(AreaTool.getAreaById(info.getProvince()).getLabel());
                dto.setCityName(AreaTool.getAreaById(info.getCity()).getLabel());
                dto.setAreaName(AreaTool.getAreaById(info.getArea()).getLabel());
                dto.setType(info.getType());
                dto.setId(info.getId());
                dto.setAddress(info.getAddress());
                dto.setBusinessType(info.getBusinessType());
                dto.setCompanyAccount(info.getCompanyAccount());
                dto.setCompanyName(info.getCompanyName());
                dto.setRemark(info.getRemark());
                dto.setLegalPerson(info.getLegalPerson());
                dto.setIsAuth(info.getIsAuth());
                dto.setLicence(info.getLicence());
                dto.setCredential(info.getCredential());
                list.add(dto);
            }
        }
        map.put("companyList", list);
        map.put("userList", userList);
    }


}
