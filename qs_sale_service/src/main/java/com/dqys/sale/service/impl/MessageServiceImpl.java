package com.dqys.sale.service.impl;

import com.dqys.auth.orm.dao.facade.SaleUserMapper;
import com.dqys.core.constant.MessageBTEnum;
import com.dqys.core.constant.RoleTypeEnum;
import com.dqys.core.constant.SmsEnum;
import com.dqys.core.constant.UserInfoEnum;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.FormatValidateTool;
import com.dqys.core.utils.RabbitMQProducerTool;
import com.dqys.core.utils.SmsUtil;
import com.dqys.sale.orm.mapper.message.MessageMapper;
import com.dqys.sale.orm.pojo.UserDetailDTO;
import com.dqys.sale.orm.pojo.message.Message;
import com.dqys.sale.orm.pojo.message.MessageOperinfo;
import com.dqys.sale.service.facade.MessageService;
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
    private SaleUserMapper saleUserMapper;

    @Override
    public List<Message> selectByMessage(Message message) {
        List<Message> messageList = messageMapper.selectByMessage(message);
        return messageList;
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
    public Integer add(String title, String content, Integer sender_id, Integer receive_id, String label, Integer type,
                       Integer businessType, String operUrl, Integer flowBusinessId) {
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
            message.setFlowBusinessId(flowBusinessId);
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
            com.dqys.auth.orm.pojo.saleUser.dto.UserDetailDTO tUserInfo = saleUserMapper.getUserDetail(receiveUserId);
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

    @Override
    public Integer addMessageAndSendSMS(Integer sendUserId, Integer receiveUserId, Integer operType) {
        return null;
    }


}
