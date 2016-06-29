package com.dqys.business.service.service.impl;

import com.dqys.business.orm.mapper.operLog.OperLogMapper;
import com.dqys.business.orm.pojo.operLog.OperLog;
import com.dqys.business.service.service.OperLogService;
import com.dqys.core.base.BasePageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mkfeng on 2016/6/28.
 */
@Service
public class OperLogServiceImpl implements OperLogService {

    @Autowired
    private OperLogMapper operLogMapper;

    @Override
    public Integer addByOperLog(OperLog operLog) {
        return operLogMapper.addByOperLog(operLog);
    }

    @Override
    public Integer editByOperLog(OperLog operLog) {
        return operLogMapper.editByOperLog(operLog);
    }

    @Override
    public List<OperLog> selectByOperLog(BasePageDTO<OperLog> page) {
        return operLogMapper.selectByOperLog(page);
    }

    @Override
    public Integer selectByCount(OperLog operLog) {
        return operLogMapper.selectByCount(operLog);
    }


}
