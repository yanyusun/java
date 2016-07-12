package com.dqys.business.service.service.impl;

import com.dqys.business.orm.mapper.coordinator.CoordinatorMapper;
import com.dqys.business.service.service.CoordinatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mkfeng on 2016/7/12.
 */
@Service
public class CoordinatorServiceImpl implements CoordinatorService {

    @Autowired
    private CoordinatorMapper coordinatorMapper;

}
