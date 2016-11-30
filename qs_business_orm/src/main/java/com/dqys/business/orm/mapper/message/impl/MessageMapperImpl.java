package com.dqys.business.orm.mapper.message.impl;

import com.dqys.business.orm.mapper.message.MessageMapper;
import com.dqys.business.orm.pojo.message.Message;
import com.dqys.business.orm.pojo.message.MessageOperinfo;
import com.dqys.core.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/8.
 */
@Repository
public class MessageMapperImpl extends BaseDao implements MessageMapper {


    @Override
    public List<Message> selectByMessage(Message message) {
        return super.getSqlSession().getMapper(MessageMapper.class).selectByMessage(message);
    }

    @Override
    public Integer readMessage(Integer[] ids) {
        return super.getSqlSession().getMapper(MessageMapper.class).readMessage(ids);
    }

    @Override
    public Integer del(Integer[] ids) {
        return super.getSqlSession().getMapper(MessageMapper.class).del(ids);
    }

    @Override
    public Integer add(Message message) {
        return super.getSqlSession().getMapper(MessageMapper.class).add(message);
    }

    @Override
    public Integer selectCount(Message message) {
        return super.getSqlSession().getMapper(MessageMapper.class).selectCount(message);
    }

    @Override
    public Message get(Integer id) {
        return super.getSqlSession().getMapper(MessageMapper.class).get(id);
    }

    @Override
    public Integer updateOperStatus(Message message) {
        return super.getSqlSession().getMapper(MessageMapper.class).updateOperStatus(message);
    }

    @Override
    public Integer getMessageNo() {
        return super.getSqlSession().getMapper(MessageMapper.class).getMessageNo();
    }

    @Override
    public Integer insertMessageNoByOperinfo(MessageOperinfo messageOperinfo) {
        return super.getSqlSession().getMapper(MessageMapper.class).insertMessageNoByOperinfo(messageOperinfo);
    }

}
