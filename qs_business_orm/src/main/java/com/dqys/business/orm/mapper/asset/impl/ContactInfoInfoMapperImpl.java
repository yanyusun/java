package com.dqys.business.orm.mapper.asset.impl;


import com.dqys.business.orm.mapper.asset.ContactInfoMapper;
import com.dqys.business.orm.pojo.asset.ContactInfo;
import com.dqys.business.orm.query.asset.LenderQuery;
import com.dqys.core.base.BaseDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yvan on 16/6/8.
 */
@Repository
@Primary
public class ContactInfoInfoMapperImpl extends BaseDao implements ContactInfoMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(ContactInfoMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(ContactInfo record) {
        return super.getSqlSession().getMapper(ContactInfoMapper.class).insert(record);
    }

    @Override
    public ContactInfo get(Integer id) {
        return super.getSqlSession().getMapper(ContactInfoMapper.class).get(id);
    }

    @Override
    public List<ContactInfo> queryList(LenderQuery lenderQuery) {
        return super.getSqlSession().getMapper(ContactInfoMapper.class).queryList(lenderQuery);
    }

    @Override
    public Integer update(ContactInfo record) {
        return super.getSqlSession().getMapper(ContactInfoMapper.class).update(record);
    }

}
