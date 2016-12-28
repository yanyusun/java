package com.dqys.flowbusiness.orm.mapper.impl;

import com.dqys.core.base.SaleBaseDao;
import com.dqys.flowbusiness.orm.mapper.BusinessLevelReMapper;
import com.dqys.flowbusiness.orm.pojo.BusinessLevelRe;
import com.dqys.flowbusiness.orm.query.BusinessLevelReQuery;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yan on 16-12-28.
 */
@Repository("saleBusinessLevelReMapper")
@Primary
public class SaleBusinessLevelReMapperImpl  extends SaleBaseDao implements BusinessLevelReMapper{
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(BusinessLevelRe record) {
        return 0;
    }

    @Override
    public int insertSelective(BusinessLevelRe record) {
        return 0;
    }

    @Override
    public BusinessLevelRe selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(BusinessLevelRe record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(BusinessLevelRe record) {
        return 0;
    }

    @Override
    public List<BusinessLevelRe> list(BusinessLevelReQuery query) {
        return super.getSqlSession().getMapper(BusinessLevelReMapper.class).list(query);   }
}
