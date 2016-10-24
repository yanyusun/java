package com.dqys.auth.orm.dao.impl;

import com.dqys.auth.orm.dao.facade.TMessageMapper;
import com.dqys.auth.orm.pojo.Message;
import com.dqys.core.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/8.
 */
@Repository
public class TMessageMapperImpl extends BaseDao implements TMessageMapper {


    @Override
    public List<Message> selectByMessage(Message message) {
        return super.getSqlSession().getMapper(TMessageMapper.class).selectByMessage(message);
    }

    @Override
    public Integer readMessage(Integer[] ids) {
        return super.getSqlSession().getMapper(TMessageMapper.class).readMessage(ids);
    }

    @Override
    public Integer del(Integer[] ids) {
        return super.getSqlSession().getMapper(TMessageMapper.class).del(ids);
    }

    @Override
    public Integer add(Message message) {
        return super.getSqlSession().getMapper(TMessageMapper.class).add(message);
    }

    @Override
    public Integer selectCount(Message message) {
        return super.getSqlSession().getMapper(TMessageMapper.class).selectCount(message);
    }

    @Override
    public Message get(Integer id) {
        return super.getSqlSession().getMapper(TMessageMapper.class).get(id);
    }

    @Override
    public Integer updateOperStatus(Message message) {
        return super.getSqlSession().getMapper(TMessageMapper.class).updateOperStatus(message);
    }

}
