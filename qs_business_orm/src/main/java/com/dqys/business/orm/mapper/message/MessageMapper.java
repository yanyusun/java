package com.dqys.business.orm.mapper.message;

import com.dqys.business.orm.pojo.message.Message;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/8.
 */
public interface MessageMapper {
    /**
     * 消息列表
     *
     * @param message
     * @return
     */
    List<Message> selectByMessage(Message message);

    /**
     * 标记为已读（单个或是批量）
     *
     * @param ids
     * @return
     */
    Integer readMessage(Integer[] ids);

    /**
     * 删除消息（伪删除）
     *
     * @param ids
     * @return
     */
    Integer del(Integer[] ids);

    Integer add(Message message);
}
