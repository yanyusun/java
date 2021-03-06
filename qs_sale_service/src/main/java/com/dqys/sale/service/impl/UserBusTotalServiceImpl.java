package com.dqys.sale.service.impl;

import com.dqys.flowbusiness.service.service.BusinessService;
import com.dqys.sale.orm.mapper.UserBusTotalMapper;
import com.dqys.sale.orm.pojo.UserBusTotal;
import com.dqys.sale.orm.query.UserBusTotalQuery;
import com.dqys.sale.service.constant.UserBusTotalEnum;
import com.dqys.sale.service.exception.bean.UserBusTotalException;
import com.dqys.sale.service.facade.UserBusTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yan on 16-12-21.
 */
@Service
@Primary
public class UserBusTotalServiceImpl implements UserBusTotalService {
    @Autowired
    private UserBusTotalMapper userBusTotalMapper;
    @Autowired
    @Qualifier("saleBusinessService")
    private BusinessService businessService;


    @Override
    public List<UserBusTotal> list(UserBusTotalQuery query) {
        return userBusTotalMapper.list(query);
    }

    @Override
    public UserBusTotal getByUserId(Integer userId) throws UserBusTotalException{
        UserBusTotalQuery query = new UserBusTotalQuery();
        query.setUserId(userId);
        List<UserBusTotal> list = userBusTotalMapper.list(query);
        if(list!=null&&list.size()==1){
            return list.get(0);
        }else{
            throw new UserBusTotalException("记录查询异常",UserBusTotalException.NOT_FOUND_SINGLE);
        }
    }

    @Override
    public void update(Integer userId, UserBusTotalEnum userBusTotalEnum, Integer assetType, Integer assetId) {

    }

    @Override
    public Integer createUserBusTotal(UserBusTotal userBusTotal) {
        return userBusTotalMapper.insertSelective(userBusTotal);
    }


}
