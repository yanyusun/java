package com.dqys.sale.orm.mapper.message.impl;

import com.dqys.core.base.BaseDao;
import com.dqys.core.base.SaleBaseDao;
import com.dqys.sale.orm.mapper.message.MessageMapper;
import com.dqys.sale.orm.pojo.message.Message;
import com.dqys.sale.orm.pojo.message.MessageOperinfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/8.
 */
@Repository
public class MessageMapperImpl extends SaleBaseDao implements MessageMapper {


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

    @Override
    public List<Message> selectMessageByUFO(@Param("userType") Integer userType, @Param("flowBusinessId") Integer flowBusinessId, @Param("operStatus") Integer operStatus, @Param("messageBusinessType") Integer messageBusinessType) {
        return super.getSqlSession().getMapper(MessageMapper.class).selectMessageByUFO(userType, flowBusinessId, operStatus, messageBusinessType);
    }

}
