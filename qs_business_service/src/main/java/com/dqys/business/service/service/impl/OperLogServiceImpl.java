package com.dqys.business.service.service.impl;

import com.dqys.business.orm.mapper.operLog.OperLogMapper;
import com.dqys.business.orm.pojo.operLog.OperLog;
import com.dqys.business.orm.pojo.operLog.OperLogDTO;
import com.dqys.business.service.service.OperLogService;
import com.dqys.core.base.BasePageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public Integer addOperLog(String text, Integer objectId, Integer objectType, Integer operType, Integer userId) {
        OperLog operLog=new OperLog();
        operLog.setText(text);
        operLog.setObjectId(objectId);
        operLog.setOperType(operType);
        operLog.setOperType(operType);
        operLog.setUserId(userId);
        return addByOperLog(operLog);
    }

    @Override
    public Integer editByOperLog(OperLog operLog) {
        return operLogMapper.editByOperLog(operLog);
    }

    @Override
    public List<OperLogDTO> selectByOperLog(OperLog operLog) {
         if(operLog.getTime()!=null&&operLog.getTime().indexOf(",")>0){
            String[] times=operLog.getTime().split(",");
            operLog.setStartTime(times[0]);
            operLog.setEndTime(times[1]);
        }
        return operLogMapper.selectByOperLog(operLog);
    }

    @Override
    public List<Map<String, Object>> operator() {
        return operLogMapper.operator();
    }

    @Override
    public Integer selectByCount(OperLog operLog) {
        return operLogMapper.selectByCount(operLog);
    }


}
