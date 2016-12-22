package com.dqys.flowbusiness.orm.mapper.impl;


import com.dqys.core.base.SaleBaseDao;
import com.dqys.flowbusiness.orm.mapper.PublicBusinessMapper;
import com.dqys.flowbusiness.orm.pojo.Business;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * Created by yan on 16/12/15.
 */
@Repository
@Primary
public class PublicBusinessMapperImpl extends SaleBaseDao implements PublicBusinessMapper {


    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(PublicBusinessMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(Business record) {
        return super.getSqlSession().getMapper(PublicBusinessMapper.class).insert(record);
    }

    @Override
    public Business get(Integer id) {
        return super.getSqlSession().getMapper(PublicBusinessMapper.class).get(id);
    }

    @Override
    public Integer update(Business record) {
        return super.getSqlSession().getMapper(PublicBusinessMapper.class).update(record);
    }
}
