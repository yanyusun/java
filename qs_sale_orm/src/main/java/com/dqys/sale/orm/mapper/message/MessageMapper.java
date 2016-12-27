package com.dqys.sale.orm.mapper.message;

import com.dqys.business.orm.pojo.message.Message;
import com.dqys.business.orm.pojo.message.MessageOperinfo;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 添加消息信息
     *
     * @param message
     * @return
     */
    Integer add(Message message);

    /**
     * 查询消息记录数
     *
     * @param message
     * @return
     */
    Integer selectCount(Message message);

    Message get(Integer id);

    Integer updateOperStatus(Message message);

    Integer getMessageNo();

    Integer insertMessageNoByOperinfo(MessageOperinfo messageOperinfo);

    /**
     * @param userType
     * @param flowBusinessId
     * @param operStatus
     * @param messageBusinessType
     * @return
     */
    List<Message> selectMessageByUFO(@Param("userType") Integer userType, @Param("flowBusinessId") Integer flowBusinessId, @Param("operStatus") Integer operStatus, @Param("messageBusinessType") Integer messageBusinessType);
}
