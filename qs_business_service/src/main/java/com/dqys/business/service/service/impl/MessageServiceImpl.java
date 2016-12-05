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
import com.dqys.business.orm.pojo.coordinator.TeammateRe;
import com.dqys.business.orm.pojo.coordinator.UserDetail;
import com.dqys.business.orm.pojo.coordinator.UserTeam;
import com.dqys.business.orm.pojo.message.Message;
import com.dqys.business.orm.pojo.message.MessageOperinfo;
import com.dqys.business.service.constant.ObjectEnum.IouEnum;
import com.dqys.business.service.constant.ObjectEnum.PawnEnum;
import com.dqys.business.service.service.UserService;
import com.dqys.core.constant.MessageBTEnum;
import com.dqys.business.service.constant.MessageEnum;
import com.dqys.core.constant.UserInfoEnum;
import com.dqys.business.service.service.CoordinatorService;
import com.dqys.business.service.service.MessageService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.RoleTypeEnum;
import com.dqys.core.constant.SmsEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.FormatValidateTool;
import com.dqys.core.utils.RabbitMQProducerTool;
import com.dqys.core.utils.SmsUtil;
import com.dqys.core.utils.SysPropertyTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    @Autowired
    private UserService userService;

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
    public void sendSmsByTeammate(UserTeam userTeam, TeammateRe teammateRe, Map<String, Object> map, Integer uid, String remark) {
        UserDetail user = coordinatorMapper.getUserDetail(uid);//接受者
        SmsUtil smsUtil = new SmsUtil();
        String content = smsUtil.sendSms(SmsEnum.INVITE_COORDINATOR.getValue(), user.getMobile(),
                user.getRealName(),
                userService.getRoleNameToString(coordinatorMapper.getUserDetail(MessageUtils.transMapToInt(map, "userId"))),
                MessageUtils.transMapToString(map, "realName"),
                ObjectTypeEnum.getObjectTypeEnum(userTeam.getObjectType()).getName(),
                coordinatorService.getObjectName(userTeam.getObjectType(), userTeam.getObjectId()));
        String title = coordinatorService.getMessageTitle(userTeam.getObjectId(), userTeam.getObjectType(), MessageBTEnum.INSIDE.getValue());
        Integer result = add(title, content, MessageUtils.transMapToInt(map, "userId"), uid, MessageBTEnum.INSIDE.getName(), MessageEnum.TASK.getValue(), MessageBTEnum.INSIDE.getValue(),
                MessageUtils.setOperUrl("/coordinator/isAccept?status=1&teammateId=" + teammateRe.getId() + "&operUserId=" + MessageUtils.transMapToInt(map, "userId"), null,
                        "/coordinator/isAccept?status=2&teammateId=" + teammateRe.getId() + "&operUserId=" + MessageUtils.transMapToInt(map, "userId"), null, null));//添加消息记录
    }

    @Override
    public String businessFlow(Integer objectId, Integer objectType, Integer flowId, Integer flowType, String operation, Integer userId, String operUrl) {
        SmsUtil smsUtil = new SmsUtil();
        TUserTag tUserTag = getAdmin();
        if (tUserTag != null) {
            TUserInfo tuserInfo = tUserInfoMapper.selectByPrimaryKey(tUserTag.getUserId());
            if (tuserInfo != null) {
                UserDetail operC = coordinatorMapper.getUserDetail(userId);//发送者
                String content = smsUtil.sendSms(SmsEnum.FLOW.getValue(), tuserInfo.getMobile(),
                        tuserInfo.getRealName(),
                        userService.getCompayTypeToString(operC),
                        operC.getCompanyName(),
                        userService.getRoleNameToString(operC),
                        operC.getRealName(),
                        ObjectTypeEnum.getObjectTypeEnum(objectType).getName(),
                        coordinatorService.getObjectName(objectType, objectId),
                        ObjectTypeEnum.getObjectTypeEnum(flowType).getName(),
                        coordinatorService.getObjectName(flowType, flowId),
                        operation);
                String title = coordinatorService.getMessageTitle(objectId, objectType, MessageBTEnum.FLOW.getValue());
                add(title, content, userId, tuserInfo.getId(), MessageBTEnum.FLOW.getName(), MessageEnum.TASK.getValue(), MessageBTEnum.FLOW.getValue(), operUrl);
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
    @Override
    public TUserTag getAdmin() {
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
    public String businessFlowResult(Integer objectId, Integer objectType, Integer flowId, Integer flowType, String operation, Integer sendUserId, Integer receiveUserId, Integer status, Integer inviteUserId) {
        SmsUtil smsUtil = new SmsUtil();
        UserDetail userC = coordinatorMapper.getUserDetail(receiveUserId);//接收者
        if (userC != null) {
            String content = "";
            if (status == 1) {
                content = smsUtil.sendSms(SmsEnum.FLOW_RESULT_YES.getValue(), userC.getMobile(),
                        userC.getRealName(),
                        ObjectTypeEnum.getObjectTypeEnum(objectType).getName(),
                        coordinatorService.getObjectName(objectType, objectId),
                        ObjectTypeEnum.getObjectTypeEnum(flowType).getName(),
                        coordinatorService.getObjectName(flowType, flowId),
                        operation,
                        userService.getCompayTypeToString(coordinatorMapper.getUserDetail(inviteUserId)),
                        coordinatorMapper.getUserDetail(inviteUserId).getCompanyName());
            } else {
                content = smsUtil.sendSms(SmsEnum.FLOW_RESULT_NO.getValue(), userC.getMobile(),
                        userC.getRealName(),
                        ObjectTypeEnum.getObjectTypeEnum(objectType).getName(),
                        coordinatorService.getObjectName(objectType, objectId),
                        ObjectTypeEnum.getObjectTypeEnum(flowType).getName(),
                        coordinatorService.getObjectName(flowType, flowId),
                        operation);
            }
            String title = coordinatorService.getMessageTitle(objectId, objectType, MessageBTEnum.FLOW_RESULT.getValue());
            add(title, content, sendUserId, receiveUserId, MessageBTEnum.FLOW_RESULT.getName(), MessageEnum.SERVE.getValue(), MessageBTEnum.FLOW_RESULT.getValue(), "");
            return "yes";
        } else {
            return "no";
        }
    }

    @Override
    public String respondInvite(Integer objectId, Integer objectType, Integer flowId, Integer flowType, Integer sendUserId, Integer receiveUserId, Integer status, Integer operType) {
        SmsUtil smsUtil = new SmsUtil();
        TUserTag tUserTag = getAdmin();
        if (tUserTag != null) {
            UserDetail userC = coordinatorMapper.getUserDetail(receiveUserId);//接收者
            UserDetail oper = coordinatorMapper.getUserDetail(sendUserId);//发送者
            TUserInfo tuserInfo = tUserInfoMapper.selectByPrimaryKey(tUserTag.getUserId());
            if (userC != null && oper != null && tuserInfo != null) {
                String content = "";//请求公司短信内容
                String adminContent = "";//平台管理员短信内容
                String title = coordinatorService.getMessageTitle(objectId, objectType, MessageBTEnum.INVITE_RESULT.getValue());
                Integer code = 0;
                Integer adminCode = 0;
                if (status == 1) {
                    code = SmsEnum.RESPOND_INVITE_RESULT_YES.getValue();
                    adminCode = SmsEnum.ADMIN_INVITE_RESULT_YES.getValue();
                } else {
                    code = SmsEnum.RESPOND_INVITE_RESULT_NO.getValue();
                    adminCode = SmsEnum.ADMIN_INVITE_RESULT_NO.getValue();
                }
                content = smsUtil.sendSms(code, userC.getMobile(),
                        userC.getRealName(),
                        userService.getCompayTypeToString(oper),
                        oper.getCompanyName(),
                        userService.getRoleNameToString(oper),
                        oper.getRealName(),
                        ObjectTypeEnum.getObjectTypeEnum(objectType).getName(),
                        coordinatorService.getObjectName(objectType, objectId),
                        ObjectTypeEnum.getObjectTypeEnum(flowType).getName(),
                        coordinatorService.getObjectName(flowType, flowId));

                adminContent = smsUtil.sendSms(adminCode, tuserInfo.getMobile(),
                        tuserInfo.getRealName(),
                        userService.getCompayTypeToString(oper),
                        oper.getCompanyName(),
                        userService.getRoleNameToString(oper),
                        oper.getRealName(),
                        userService.getCompayTypeToString(userC),
                        userC.getCompanyName(),
                        userService.getRoleNameToString(userC),
                        userC.getRealName(),
                        ObjectTypeEnum.getObjectTypeEnum(objectType).getName(),
                        coordinatorService.getObjectName(objectType, objectId),
                        ObjectTypeEnum.getObjectTypeEnum(flowType).getName(),
                        coordinatorService.getObjectName(flowType, flowId),
                        getOperTypeName(flowType, operType));
                add(title, content, sendUserId, receiveUserId, "", MessageEnum.SERVE.getValue(), MessageBTEnum.INVITE_RESULT.getValue(), "");

                add(title, adminContent, sendUserId, tuserInfo.getId(), "", MessageEnum.SERVE.getValue(), MessageBTEnum.INVITE_RESULT.getValue(), "");
                return "yes";
            }
        }
        return "no";
    }

    private String getOperTypeName(Integer ojectType, Integer operType) {
        if (ojectType == ObjectTypeEnum.PAWN.getValue().intValue()) {
            return PawnEnum.getPawnEnum(operType).getName();
        } else if (ojectType == ObjectTypeEnum.IOU.getValue().intValue()) {
            return IouEnum.getIouEnum(operType).getName();
        }
        return "";
    }

    @Override
    public String judicature(Integer objectId, Integer objectType, Integer flowId, Integer flowType, Integer userId, String operation, Integer onStatus, boolean modify) {
        if (setJiGou(objectId, objectType, flowId, flowType, userId, operation, onStatus, UserInfoEnum.USER_TYPE_JUDICIARY.getValue(), modify)) {
            return "yes";
        }
        return "no";
    }

    @Override
    public String intermediary(Integer objectId, Integer objectType, Integer flowId, Integer flowType, Integer userId, String operation, Integer onStatus, boolean modify) {
        if (setJiGou(objectId, objectType, flowId, flowType, userId, operation, onStatus, UserInfoEnum.USER_TYPE_INTERMEDIARY.getValue(), modify)) {
            return "yes";
        }
        return "no";
    }

    @Override
    public String collectiones(Integer objectId, Integer objectType, Integer flowId, Integer flowType, Integer userId, String operation, Integer onStatus, boolean modify) {
        if (setJiGou(objectId, objectType, flowId, flowType, userId, operation, onStatus, UserInfoEnum.USER_TYPE_COLLECTION.getValue(), modify)) {
            return "yes";
        }
        return "no";
    }

    @Override
    public Map setOper(Integer id, Integer status) {
        Map map = new HashMap<>();
        map.put("result", "no");
        Message message = messageMapper.get(id);
        if (message == null) {
            map.put("msg", "查询消息记录有误");
        } else {
            if (message.getMessageNo() != null) {//如果存在编号，就把统一编号的消息操作状态全改了
                message.setId(null);
                MessageOperinfo messageOperinfo = new MessageOperinfo();
                messageOperinfo.setMessageMo(message.getMessageNo());
                messageOperinfo.setUserId(UserSession.getCurrent().getUserId());
                messageOperinfo.setOperStatus(status);
                messageMapper.insertMessageNoByOperinfo(messageOperinfo);
            }
            message.setOperStatus(status);
            messageMapper.updateOperStatus(message);
            map.put("result", "yes");
        }
        return map;
    }

    @Override
    public Integer getMessageNo() {
        return messageMapper.getMessageNo();
    }

    @Override
    public Integer insertMessageNoByOperinfo(MessageOperinfo messageOperinfo) {
        return messageMapper.insertMessageNoByOperinfo(messageOperinfo);
    }

    private boolean setJiGou(Integer objectId, Integer objectType, Integer flowId, Integer flowType, Integer userId, String operation, Integer onStatus, Integer userType, boolean modify) {
        boolean flag = coordinatorService.verdictOrganization(flowId, flowType, onStatus, userType, modify);
        if (flag) {
            SmsUtil smsUtil = new SmsUtil();
            //根据对象类型和对象id获取分配器中的公司管理员
            Map map = coordinatorMapper.getCompanyAndUser(objectId, objectType, userType);
            UserDetail userC = coordinatorMapper.getUserDetail(MessageUtils.transMapToInt(map, "userId"));//接收者
            UserDetail oper = coordinatorMapper.getUserDetail(userId);//发送者
            if (userC != null && !userId.equals(MessageUtils.transMapToInt(map, "userId"))) {//需要发送者与接收者不是同一个人
                String content = smsUtil.sendSms(SmsEnum.FlOW_OPER.getValue(), userC.getMobile(),
                        userC.getRealName(),
                        userService.getCompayTypeToString(oper),
                        oper.getCompanyName(),
                        userService.getRoleNameToString(oper),
                        oper.getRealName(),
                        ObjectTypeEnum.getObjectTypeEnum(objectType).getName(),
                        coordinatorService.getObjectName(objectType, objectId),
                        ObjectTypeEnum.getObjectTypeEnum(flowType).getName(),
                        coordinatorService.getObjectName(flowType, flowId),
                        operation,
                        onStatus == 0 ? "加入" : "移出");
                String title = coordinatorService.getMessageTitle(objectId, objectType, MessageBTEnum.FLOW_RESULT.getValue());
                add(title, content, userId, MessageUtils.transMapToInt(map, "userId"), MessageBTEnum.FLOW_RESULT.getName(), MessageEnum.SERVE.getValue(), MessageBTEnum.FLOW_RESULT.getValue(), "");
                return true;
            }
        }
        return false;
    }


}
