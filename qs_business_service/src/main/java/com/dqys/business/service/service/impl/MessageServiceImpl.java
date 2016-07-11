package com.dqys.business.service.service.impl;

import com.dqys.business.orm.mapper.message.MessageMapper;
import com.dqys.business.orm.pojo.message.Message;
import com.dqys.business.service.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/8.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

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
    public Integer add(String title, String content, Integer sender_id, Integer receive_id, String label, Integer type) {
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
            return messageMapper.add(message);
        }

    }

    @Override
    public Integer selectCount(Message message) {
        return messageMapper.selectCount(message);
    }
}
