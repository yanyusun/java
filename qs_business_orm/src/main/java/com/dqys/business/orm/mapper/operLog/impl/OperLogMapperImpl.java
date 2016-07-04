package com.dqys.business.orm.mapper.operLog.impl;

import com.dqys.business.orm.mapper.operLog.OperLogMapper;
import com.dqys.business.orm.pojo.operLog.OperLog;
import com.dqys.business.orm.pojo.operLog.OperLogDTO;
import com.dqys.core.base.BaseDao;
import com.dqys.core.base.BasePageDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/6/28.
 */
@Repository
public class OperLogMapperImpl extends BaseDao implements OperLogMapper {
    @Override
    public Integer addByOperLog(OperLog operLog) {
        return super.getSqlSession().getMapper(OperLogMapper.class).addByOperLog(operLog);
    }

    @Override
    public Integer editByOperLog(OperLog operLog) {
        return super.getSqlSession().getMapper(OperLogMapper.class).editByOperLog(operLog);
    }

    @Override
    public List<OperLogDTO> selectByOperLog(OperLog operLog) {
        return super.getSqlSession().getMapper(OperLogMapper.class).selectByOperLog(operLog);
    }

    @Override
    public Integer selectByCount(OperLog operLog) {
        return super.getSqlSession().getMapper(OperLogMapper.class).selectByCount(operLog);
    }

    @Override
    public List<Map<String, Object>> operator() {
        return  super.getSqlSession().getMapper(OperLogMapper.class).operator();
    }
}
