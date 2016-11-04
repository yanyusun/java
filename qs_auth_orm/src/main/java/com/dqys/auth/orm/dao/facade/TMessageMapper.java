package com.dqys.auth.orm.dao.facade;


import com.dqys.auth.orm.pojo.LoginLog;
import com.dqys.auth.orm.pojo.Message;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/8.
 */
public interface TMessageMapper {
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

    /**
     * 添加登入日志
     *
     * @param log
     */
    void addLoginLog(LoginLog log);
}
