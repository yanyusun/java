package com.dqys.business.orm.mapper.zcy.impl;

import com.dqys.business.orm.mapper.zcy.ZcyOwnerContactsMapper;
import com.dqys.business.orm.pojo.zcy.ZcyOwnerContacts;
import com.dqys.core.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/27.
 */
@Repository
public class ZcyOwnerContactsMapperImpl extends BaseDao implements ZcyOwnerContactsMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(ZcyOwnerContactsMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(ZcyOwnerContacts record) {
        return super.getSqlSession().getMapper(ZcyOwnerContactsMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(ZcyOwnerContacts record) {
        return super.getSqlSession().getMapper(ZcyOwnerContactsMapper.class).insertSelective(record);
    }

    @Override
    public ZcyOwnerContacts selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(ZcyOwnerContactsMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(ZcyOwnerContacts record) {
        return super.getSqlSession().getMapper(ZcyOwnerContactsMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKey(ZcyOwnerContacts record) {
        return super.getSqlSession().getMapper(ZcyOwnerContactsMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public List<ZcyOwnerContacts> selectBySelective(ZcyOwnerContacts record) {
        return super.getSqlSession().getMapper(ZcyOwnerContactsMapper.class).selectBySelective(record);
    }
}
