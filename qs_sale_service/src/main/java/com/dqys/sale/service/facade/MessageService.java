package com.dqys.sale.service.facade;


import com.dqys.sale.orm.pojo.message.Message;
import com.dqys.sale.orm.pojo.message.MessageOperinfo;

import java.util.List;
import java.util.Map;

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
     * @param title        标题
     * @param content      内容
     * @param sender_id    发送者id
     * @param receive_id   接受者id
     * @param label        标签
     * @param type         消息类型(0任务1产品2安全3服务)
     * @param businessType 业务类型(0任务1产品2安全3服务)
     * @param operUrl      操作地址参数
     * @return
     */
    Integer add(String title, String content, Integer sender_id, Integer receive_id, String label, Integer type, Integer businessType, String operUrl);

    /**
     * @param title          标题
     * @param content        内容
     * @param sender_id      发送者id
     * @param receive_id     接受者id
     * @param label          标签
     * @param type           消息类型(0任务1产品2安全3服务)
     * @param businessType   业务类型(0任务1产品2安全3服务)
     * @param operUrl        操作地址参数
     * @param flowBusinessId 流转业务状态表id
     * @return
     */
    Integer add(String title, String content, Integer sender_id, Integer receive_id, String label, Integer type, Integer businessType, String operUrl, Integer flowBusinessId);

    /**
     * 查询消息记录数
     *
     * @param message
     * @return
     */
    Integer selectCount(Message message);

    /**
     * 发送短信
     *
     * @param receiveUserId 接受短信人的用户id(当手机传入为NUll时，根据用户id查询对应手机号发送)
     * @param mobilePhone   接收人的手机号
     * @param content       短信内容
     * @return
     */
    Integer sendSMS(Integer receiveUserId, String mobilePhone, String content);

    /**
     * 设置消息的操作状态
     *
     * @param id
     * @param status
     * @return
     */
    Map setOper(Integer id, Integer status);

    Integer getMessageNo();

    Integer insertMessageNoByOperinfo(MessageOperinfo messageOperinfo);

    Integer addMessageAndSendSMS(Integer sendUserId, Integer receiveUserId, Integer operType);

}
