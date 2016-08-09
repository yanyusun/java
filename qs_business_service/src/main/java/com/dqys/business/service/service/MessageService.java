package com.dqys.business.service.service;

import com.dqys.business.orm.pojo.message.Message;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/8.
 */
public interface MessageService {
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

    /**
     * @param title      标题
     * @param content    内容
     * @param sender_id  发送者id
     * @param receive_id 接受者id
     * @param label      标签
     * @param type       消息类型(0任务1产品2安全3服务)
     * @return
     */
    Integer add(String title, String content, Integer sender_id, Integer receive_id, String label, Integer type);

    /**
     *查询消息记录数
     * @param message
     * @return
     */
    Integer selectCount(Message message);


}
