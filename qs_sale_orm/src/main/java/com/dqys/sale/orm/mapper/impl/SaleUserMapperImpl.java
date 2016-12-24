package com.dqys.sale.orm.mapper.impl;

import com.dqys.core.base.SaleBaseDao;
import com.dqys.sale.orm.dto.UserDetailDTO;
import com.dqys.sale.orm.mapper.SaleUserMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/12/23.
 */
@Repository
public class SaleUserMapperImpl extends SaleBaseDao implements SaleUserMapper {
    @Override
    public UserDetailDTO getUserDetail(Integer userId) {
        return super.getSqlSession().getMapper(SaleUserMapper.class).getUserDetail(userId);
    }
}
