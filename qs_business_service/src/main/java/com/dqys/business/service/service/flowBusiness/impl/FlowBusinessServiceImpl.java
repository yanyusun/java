package com.dqys.business.service.service.flowBusiness.impl;

import com.dqys.business.orm.mapper.flowBusiness.FlowBusinessMapper;
import com.dqys.business.orm.pojo.flowBusiness.FlowBusiness;
import com.dqys.business.service.service.flowBusiness.FlowBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mkfeng on 2016/12/2.
 */
@Service
public class FlowBusinessServiceImpl implements FlowBusinessService {
    @Autowired
    private FlowBusinessMapper flowBusinessMapper;


    @Override
    public FlowBusiness get(Integer id) {
        return flowBusinessMapper.get(id);
    }

    @Override
    public List<FlowBusiness> selectByCondition(FlowBusiness flowBusiness) {
        return flowBusinessMapper.selectByCondition(flowBusiness);
    }

    @Override
    public Integer add(FlowBusiness flowBusiness) {
        return flowBusinessMapper.add(flowBusiness);
    }

    @Override
    public Integer updateById(FlowBusiness flowBusiness) {
        return flowBusinessMapper.updateById(flowBusiness);
    }
}
