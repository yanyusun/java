package com.dqys.business.service.service.impl;

import com.dqys.business.orm.mapper.businessLog.BusinessLogMapper;
import com.dqys.business.orm.pojo.businessLog.BusinessLog;
import com.dqys.business.orm.query.businessLog.BusinessLogQuery;
import com.dqys.business.service.service.BusinessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yan on 16-7-13.
 * 业务日志ser
 */
@Service
@Primary
public class BusinessLogServiceImp implements BusinessLogService{
    @Autowired
    private BusinessLogMapper businessLogMapper;

    @Override
    public List<BusinessLog> list(BusinessLogQuery query) {
        System.out.println("1111");

        return businessLogMapper.list(query);



    }
}
