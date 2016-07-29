package com.dqys.business.orm.mapper.asset.impl;


import com.dqys.business.orm.mapper.asset.ContactInfoMapper;
import com.dqys.business.orm.pojo.asset.ContactInfo;
import com.dqys.business.orm.query.asset.ContactQuery;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;
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
    public Integer deleteByMode(@Param("mode") String mode, @Param("id") Integer id) {
        return super.getSqlSession().getMapper(ContactInfoMapper.class).deleteByMode(mode, id);
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
    public List<ContactInfo> queryList(ContactQuery contactQuery) {
        return super.getSqlSession().getMapper(ContactInfoMapper.class).queryList(contactQuery);
    }

    @Override
    public Integer update(ContactInfo record) {
        return super.getSqlSession().getMapper(ContactInfoMapper.class).update(record);
    }

    @Override
    public ContactInfo getByModel(@Param("model") String model, @Param("type") Integer type, @Param("id") Integer id) {
        return super.getSqlSession().getMapper(ContactInfoMapper.class).getByModel(model, type, id);
    }

    @Override
    public List<ContactInfo> listByMode(@Param("model") String model, @Param("id") Integer id) {
        return super.getSqlSession().getMapper(ContactInfoMapper.class).listByMode(model, id);
    }
}
