package com.dqys.business.service.service.impl;

import com.dqys.auth.orm.constant.CompanyTypeEnum;
import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserTagMapper;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.orm.pojo.TUserTag;
import com.dqys.auth.orm.query.TUserTagQuery;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.coordinator.CoordinatorMapper;
import com.dqys.business.orm.mapper.message.MessageMapper;
import com.dqys.business.orm.pojo.coordinator.UserTeam;
import com.dqys.business.orm.pojo.message.Message;
import com.dqys.business.service.constant.MessageBTEnum;
import com.dqys.business.service.constant.MessageEnum;
import com.dqys.business.service.service.CoordinatorService;
import com.dqys.business.service.service.MessageService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.RoleTypeEnum;
import com.dqys.core.constant.SmsEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.utils.FormatValidateTool;
import com.dqys.core.utils.RabbitMQProducerTool;
import com.dqys.core.utils.SmsUtil;
import com.dqys.core.utils.SysPropertyTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/7/8.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private TUserInfoMapper tUserInfoMapper;
    @Autowired
    private CoordinatorMapper coordinatorMapper;
    @Autowired
    private TUserTagMapper tUserTagMapper;
    @Autowired
    private CoordinatorService coordinatorService;

    @Override
    public List<Message> selectByMessage(Message message) {
        return messageMapper.selectByMessage(message);
    }

    @Override
    public Integer readMessage(Integer[] ids) {
        if (ids.length > 0) {
            return messageMapper.readMessage(ids);
        } else {
            return 0;
        }
    }

    @Override
    public Integer del(Integer[] ids) {
        if (ids.length > 0) {
            return messageMapper.del(ids);
        } else {
            return 0;
        }
    }

    @Override
    public Integer add(String title, String content, Integer sender_id, Integer receive_id, String label, Integer type, Integer businessType, String operUrl) {
        if (title.equals("") || sender_id == null || receive_id == null || type == null) {
            return 0;
        } else {
            Message message = new Message();
            message.setContent(content);
            message.setLabel(label);
            message.setReceiveId(receive_id);
            message.setSenderId(sender_id);
            message.setStatus(0);
            message.setTitle(title);
            message.setType(type);
            message.setBusinessType(businessType);
            message.setOperUrl(operUrl);
            return messageMapper.add(message);
        }

    }

    @Override
    public Integer selectCount(Message message) {
        return messageMapper.selectCount(message);
    }

    @Override
    public Integer sendSMS(Integer receiveUserId, String mobilePhone, String content) {
        if (mobilePhone == null) {
            TUserInfo tUserInfo = tUserInfoMapper.selectByPrimaryKey(receiveUserId);
            if (tUserInfo != null) {
                mobilePhone = tUserInfo.getMobile();
            }
        }
        if (mobilePhone != null && FormatValidateTool.checkMobile(mobilePhone.trim())) {
            //发送短信接口
            RabbitMQProducerTool.addToSMSSendQueue(mobilePhone.toString(), content);//加入短信队列
        }
        return null;
    }

    @Override
    public void sendSmsByTeammate(UserTeam userTeam, Map<String, Object> map, Integer uid, String remark) {
        Map user = coordinatorMapper.getUserAndCompanyByUserId(uid);
        String objectName = "";
        String objectType = "";
        if (userTeam != null) {
            objectType = userTeam.getObjectType() == null ? "" : userTeam.getObjectType().toString();
            ObjectTypeEnum objectTypeEnum = ObjectTypeEnum.getObjectTypeEnum(userTeam.getObjectType());
            if (objectTypeEnum != null) {
                objectName = objectTypeEnum.getName();
            }
        }
        String mobilePhone = MessageUtils.transMapToString(user, "mobile");//接收者手机号
        String realName = MessageUtils.transMapToString(user, "realName");//接收者真实姓名
//以下发送者信息
        String typeSend = "";//发送者角色类型
        Integer role = MessageUtils.transMapToInt(user, "rold");
        if (role.toString().equals(SysPropertyTool.getProperty(SysPropertyTypeEnum.ROLE, KeyEnum.ROLE_ADMINISTRATOR_KEY).getPropertyValue())) {
            typeSend = "管理员";
        } else if (role.toString().equals(SysPropertyTool.getProperty(SysPropertyTypeEnum.ROLE, KeyEnum.ROLE_MANAGER_KEY).getPropertyValue())) {
            typeSend = "管理者";
        } else if (role.toString().equals(SysPropertyTool.getProperty(SysPropertyTypeEnum.ROLE, KeyEnum.ROLE_EMPLOYEE).getPropertyValue())) {
            typeSend = "普通员工";
        }
        String realNameSend = MessageUtils.transMapToString(map, "realName");//发送者真实姓名
        String companyNameSend = MessageUtils.transMapToString(map, "companyName");//发送者公司名称
        String companyTypeSend = "";//发送者公司类型
        CompanyTypeEnum companyTypeEnum = CompanyTypeEnum.getCompanyTypeEnum(MessageUtils.transMapToInt(map, "companyType"));
        if (companyTypeEnum != null) {
            companyTypeSend = companyTypeEnum.getName();
        }
        if (MessageUtils.transMapToString(user, "companyName").equals(MessageUtils.transMapToString(map, "companyName"))) {
            sendSms(SmsEnum.INVITE_COORDINATOR.getValue(), mobilePhone, realName, companyNameSend, companyTypeSend, realNameSend, objectType, objectName, remark);
        } else {
            sendSms(SmsEnum.INVITE_DISTRIBUTOR.getValue(), mobilePhone, realName, typeSend, realNameSend, objectType, objectName, remark);
        }
    }

    @Override
    public String businessFlow(Integer objectId, Integer objectType, Integer flowId, Integer flowType, String operation, Integer userId, String operUrl) {
        SmsUtil smsUtil = new SmsUtil();
        TUserTag tUserTag = getAdmin();
        if (tUserTag != null) {
            TUserInfo tuserInfo = tUserInfoMapper.selectByPrimaryKey(tUserTag.getUserId());
            if (tuserInfo != null) {
                Map userC = coordinatorMapper.getUserAndCompanyByUserId(userId);//发送者
                String content = smsUtil.sendSms(SmsEnum.FLOW.getValue(), tuserInfo.getMobile(), tuserInfo.getRealName(),
                        MessageUtils.transMapToString(userC, "companyName"), MessageUtils.transMapToString(userC, "realName"),
                        ObjectTypeEnum.getObjectTypeEnum(objectType).getName(), coordinatorService.getObjectName(objectType, objectId),
                        ObjectTypeEnum.getObjectTypeEnum(flowType).getName(), coordinatorService.getObjectName(flowType, flowId), operation);
                String title = coordinatorService.getMessageTitle(objectId, objectType, MessageBTEnum.FLOW.getValue());
                add(title, content, userId, tuserInfo.getId(), "", MessageEnum.TASK.getValue(), MessageBTEnum.FLOW.getValue(), operUrl);
                return "yes";
            }
        }
        return "no";
    }

    /**
     * 获取平台管理员账户
     *
     * @return
     */
    private TUserTag getAdmin() {
        TUserTagQuery tUserTagQuery = new TUserTagQuery();
        tUserTagQuery.setUserType(1);
        tUserTagQuery.setRole(RoleTypeEnum.ADMIN.getValue());
        List<TUserTag> list = tUserTagMapper.selectByQuery(tUserTagQuery);//查询平台管理员
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public String businessFlowResult(Integer objectId, Integer objectType, Integer flowId, Integer flowType, String operation, Integer sendUserId, Integer receiveUserId, Integer status) {
        SmsUtil smsUtil = new SmsUtil();
        Map userC = coordinatorMapper.getUserAndCompanyByUserId(receiveUserId);//接收者
        if (userC != null) {
            String content = "";
            if (status == 1) {
                content = smsUtil.sendSms(SmsEnum.FLOW_RESULT_YES.getValue(), MessageUtils.transMapToString(userC, "mobile"), MessageUtils.transMapToString(userC, "realName"),
                        ObjectTypeEnum.getObjectTypeEnum(objectType).getName(), coordinatorService.getObjectName(objectType, objectId),
                        ObjectTypeEnum.getObjectTypeEnum(flowType).getName(), coordinatorService.getObjectName(flowType, flowId), operation);
            } else {
                content = smsUtil.sendSms(SmsEnum.FLOW_RESULT_NO.getValue(), MessageUtils.transMapToString(userC, "mobile"), MessageUtils.transMapToString(userC, "realName"),
                        ObjectTypeEnum.getObjectTypeEnum(objectType).getName(), coordinatorService.getObjectName(objectType, objectId),
                        ObjectTypeEnum.getObjectTypeEnum(flowType).getName(), coordinatorService.getObjectName(flowType, flowId), operation);
            }
            String title = coordinatorService.getMessageTitle(objectId, objectType, MessageBTEnum.FLOW_RESULT.getValue());
            add(title, content, sendUserId, receiveUserId, "", MessageEnum.SERVE.getValue(), MessageBTEnum.FLOW_RESULT.getValue(), "");
            return "yes";
        } else {
            return "no";
        }
    }

    @Override
    public String respondInvite(Integer objectId, Integer objectType, Integer flowId, Integer flowType, Integer sendUserId, Integer receiveUserId, Integer status) {
        SmsUtil smsUtil = new SmsUtil();
        TUserTag tUserTag = getAdmin();
        if (tUserTag != null) {
            Map userC = coordinatorMapper.getUserAndCompanyByUserId(receiveUserId);//接收者
            Map oper = coordinatorMapper.getUserAndCompanyByUserId(sendUserId);//发送者
            TUserInfo tuserInfo = tUserInfoMapper.selectByPrimaryKey(tUserTag.getUserId());
            if (userC != null && oper != null && tuserInfo != null) {
                String content = "";//请求公司短信内容
                String adminContent = "";//平台管理员短信内容
                String title = coordinatorService.getMessageTitle(objectId, objectType, MessageBTEnum.INVITE_RESULT.getValue());
                if (status == 1) {
                    content = smsUtil.sendSms(SmsEnum.RESPOND_INVITE_RESULT_YES.getValue(), MessageUtils.transMapToString(userC, "mobile"), MessageUtils.transMapToString(userC, "realName"),
                            MessageUtils.transMapToString(oper, "companyType"), MessageUtils.transMapToString(oper, "companyName"), MessageUtils.transMapToString(oper, "realName"),
                            ObjectTypeEnum.getObjectTypeEnum(objectType).getName(), coordinatorService.getObjectName(objectType, objectId),
                            ObjectTypeEnum.getObjectTypeEnum(flowType).getName(), coordinatorService.getObjectName(flowType, flowId));

                    adminContent = smsUtil.sendSms(SmsEnum.ADMIN_INVITE_RESULT_YES.getValue(), tuserInfo.getMobile(), tuserInfo.getRealName(),
                            MessageUtils.transMapToString(oper, "companyType"), MessageUtils.transMapToString(oper, "companyName"), MessageUtils.transMapToString(oper, "realName"),
                            MessageUtils.transMapToString(userC, "companyType"), MessageUtils.transMapToString(userC, "companyName"), MessageUtils.transMapToString(userC, "realName"),
                            ObjectTypeEnum.getObjectTypeEnum(objectType).getName(), coordinatorService.getObjectName(objectType, objectId),
                            ObjectTypeEnum.getObjectTypeEnum(flowType).getName(), coordinatorService.getObjectName(flowType, flowId));
                } else {
                    content = smsUtil.sendSms(SmsEnum.RESPOND_INVITE_RESULT_NO.getValue(), MessageUtils.transMapToString(userC, "mobile"), MessageUtils.transMapToString(userC, "realName"),
                            MessageUtils.transMapToString(oper, "companyType"), MessageUtils.transMapToString(oper, "companyName"), MessageUtils.transMapToString(oper, "realName"),
                            ObjectTypeEnum.getObjectTypeEnum(objectType).getName(), coordinatorService.getObjectName(objectType, objectId),
                            ObjectTypeEnum.getObjectTypeEnum(flowType).getName(), coordinatorService.getObjectName(flowType, flowId));

                    adminContent = smsUtil.sendSms(SmsEnum.ADMIN_INVITE_RESULT_NO.getValue(), tuserInfo.getMobile(), tuserInfo.getRealName(),
                            MessageUtils.transMapToString(oper, "companyType"), MessageUtils.transMapToString(oper, "companyName"), MessageUtils.transMapToString(oper, "realName"),
                            MessageUtils.transMapToString(userC, "companyType"), MessageUtils.transMapToString(userC, "companyName"), MessageUtils.transMapToString(userC, "realName"),
                            ObjectTypeEnum.getObjectTypeEnum(objectType).getName(), coordinatorService.getObjectName(objectType, objectId),
                            ObjectTypeEnum.getObjectTypeEnum(flowType).getName(), coordinatorService.getObjectName(flowType, flowId));
                }
                add(title, content, sendUserId, receiveUserId, "", MessageEnum.SERVE.getValue(), MessageBTEnum.INVITE_RESULT.getValue(), "");

                add(title, adminContent, sendUserId, tuserInfo.getId(), "", MessageEnum.SERVE.getValue(), MessageBTEnum.INVITE_RESULT.getValue(), "");
                return "yes";
            }
        }
        return "no";
    }

    private String sendSms(Integer code, String mobilePhone, String... content) {
        if (!FormatValidateTool.checkMobile(mobilePhone)) {
            return "error_mobile";
        } else {
            String msg = new SmsUtil().getKeyValue(code);
            if (msg == null && msg == "") {
                return "error_msg";
            }
            for (int i = 0; i < content.length; i++) {
                msg = msg.replace("{" + i + "}", content[i]);
            }
            sendSMS(null, mobilePhone, msg);
            return "yes";
        }
    }


}
