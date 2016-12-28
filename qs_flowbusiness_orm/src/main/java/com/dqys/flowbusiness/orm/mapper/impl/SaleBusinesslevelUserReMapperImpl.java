package com.dqys.flowbusiness.orm.mapper.impl;

import com.dqys.core.base.SaleBaseDao;
import com.dqys.flowbusiness.orm.mapper.BusinesslevelUserReMapper;
import com.dqys.flowbusiness.orm.pojo.BusinesslevelUserRe;
import com.dqys.flowbusiness.orm.query.BusinesslevelUserReQuery;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yan on 16-12-28.
 */
@Repository("saleBusinesslevelUserReMapper")
@Primary
public class SaleBusinesslevelUserReMapperImpl extends SaleBaseDao implements BusinesslevelUserReMapper {
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(BusinesslevelUserRe record) {
        return 0;
    }

    @Override
    public int insertSelective(BusinesslevelUserRe record) {
        return 0;
    }

    @Override
    public BusinesslevelUserRe selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(BusinesslevelUserRe record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(BusinesslevelUserRe record) {
        return 0;
    }

    @Override
    public List<BusinesslevelUserRe> list(BusinesslevelUserReQuery query) {
        return super.getSqlSession().getMapper(BusinesslevelUserReMapper.class).list(query);
    }
}
