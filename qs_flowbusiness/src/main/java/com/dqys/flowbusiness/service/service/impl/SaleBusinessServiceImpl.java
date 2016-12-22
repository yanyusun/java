package com.dqys.flowbusiness.service.service.impl;

import com.dqys.flowbusiness.orm.mapper.PublicBusinessMapper;
import com.dqys.flowbusiness.orm.pojo.Business;
import com.dqys.flowbusiness.service.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Created by pan on 16-12-22.
 */
@Service("saleBusinessService")
@Primary
public class SaleBusinessServiceImpl  implements BusinessService {
    @Autowired //@Qualifier("saleBusinessMapper")
    private PublicBusinessMapper businessMapper;

    @Override
    public Business getBusiness(Integer id) {
        return businessMapper.get(id);
    }
}
