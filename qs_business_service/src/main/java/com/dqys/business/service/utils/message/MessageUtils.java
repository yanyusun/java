package com.dqys.business.service.utils.message;

import com.dqys.business.orm.pojo.message.Message;
import com.dqys.business.orm.pojo.message.MessageDTO;
import com.dqys.business.orm.pojo.message.MessageQuery;
import com.dqys.business.service.constant.MessageEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkfeng on 2016/7/11.
 */
public class MessageUtils {

    public static Message transToMessage(MessageQuery messageQuery) {
        Message message = new Message();
        if (messageQuery.getStatus() != null) {
            message.setStatus(messageQuery.getStatus());
        }
        if (messageQuery.getType() != null) {
            message.setType(messageQuery.getType());
        }
        if (messageQuery.getPageCount() != null && messageQuery.getPageCount() > 0) {
            message.setPageSize(messageQuery.getPageCount());
        }
        if (messageQuery.getPage() != null && messageQuery.getPage() > 0) {
            message.setStartPageNum((messageQuery.getPage() - 1) * message.getPageSize());
        }
        return message;
    }

    public static List<MessageDTO> transToMessageDTO(List<Message> messages) {
        List<MessageDTO> list = new ArrayList<>();
        for (Message m : messages) {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setTitle(m.getTitle() == null ? "" : m.getTitle());
            messageDTO.setContant(m.getContent() == null ? "" : m.getContent());
            messageDTO.setId(m.getId());
            messageDTO.setSendTime(m.getSendTime());
            messageDTO.setTypeName(MessageEnum.getEnumByValue(m.getType()));
            messageDTO.setStatus(m.getStatus());
            messageDTO.setLabel(m.getLabel()==null?"":m.getLabel());
            list.add(messageDTO);
        }
        return list;
    }


}
