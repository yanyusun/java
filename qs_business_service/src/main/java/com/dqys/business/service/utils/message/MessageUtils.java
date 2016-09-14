package com.dqys.business.service.utils.message;

import com.dqys.business.orm.pojo.message.Message;
import com.dqys.business.orm.pojo.message.MessageDTO;
import com.dqys.business.orm.pojo.message.MessageQuery;
import com.dqys.business.service.constant.MessageEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            messageDTO.setLabel(m.getLabel() == null ? "" : m.getLabel());
            messageDTO.setBusinessType(m.getBusinessType());
            messageDTO.setOperUrl(m.getOperUrl());
            list.add(messageDTO);
        }
        return list;
    }

    public static String transMapToString(Map map, String key) {
        if(map==null){
            return  "";
        }
        return map.get(key) == null ? "" : map.get(key).toString();
    }

    public static Integer transStringToInt(Object obj) {
        try {
            return Integer.parseInt(obj.toString().trim());
        } catch (Exception e) {
            return null;
        }
    }

    public static Double transStringToDou(Object obj) {
        try {
            return Double.parseDouble(obj.toString().trim());
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer transMapToInt(Map map, String key) {
        return transStringToInt(transMapToString(map, key));
    }

    public static Double transMapToDou(Map map, String key) {
        return transStringToDou(transMapToString(map, key));
    }

}
